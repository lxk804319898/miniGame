package snake;

import commonUtils.Jdbc;
import commonUtils.User;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;
    private int mushroom_x;
    private int mushroom_y;
    private int award_x;
    private int award_y;
    private int score;
    private int apple_num;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    private boolean isMoved = false;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    private Image mushroom;
    private Image award;

    private static boolean gameState;

    Board() {
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();

        ImageIcon iim = new ImageIcon("src/resources/mushroom.png");
        mushroom = iim.getImage();

        ImageIcon iis = new ImageIcon("src/resources/award.png");
        award = iis.getImage();
    }

    private void initGame() {

        score = 0;
        apple_num = 0;
        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        
        locateApple();
        locateMushroom();
        locateAward();

        timer = new Timer(DELAY, this);
        timer.start();
        gameState = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            String msg = ""+score;
            Font small = new Font("Helvetica", Font.BOLD, 12);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (0) , 10);

            g.drawImage(apple, apple_x, apple_y, this);

            if (apple_num % 5 == 0 && apple_num !=0){
                g.drawImage(mushroom, mushroom_x, mushroom_y, this);
            }

            if (apple_num % 15 == 0 && apple_num !=0){
                g.drawImage(award, award_x, award_y, this);
            }

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                    isMoved = true;
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                    isMoved = true;
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over 总得分:"+score;
        if (User.name != null && gameState == true){
            Jdbc jdbc  = new Jdbc();
            jdbc.insertScore("Snake",score);
            gameState = false;
        }

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkMushroom(){
        if ((x[0] == mushroom_x) && (y[0] == mushroom_y)) {
            score+=5;
            dots++;
            apple_num++;
            locateMushroom();
        }
    }

    private void checkAward(){
        if ((x[0] == award_x) && (y[0] == award_y)) {
            score+=50;
            dots++;
            apple_num++;
            locateAward();
        }
    }

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            score++;
            apple_num++;
            dots++;
            locateApple();
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z-1]) && (y[0] == y[z-1])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if (!inGame) {
            timer.stop();
        }
    }

    private void locateMushroom() {
        int r = (int) (Math.random() * RAND_POS);
        mushroom_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        mushroom_y = ((r * DOT_SIZE));
        for (int i = 0; i < dots; i++) {
            if (mushroom_x == x[i] && mushroom_y == y[i]) {
                locateMushroom();
                break;
            }
        }
    }

    private void locateAward() {
        int r = (int) (Math.random() * RAND_POS);
        award_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        award_y = ((r * DOT_SIZE));
        for (int i = 0; i < dots; i++) {
            if (award_x == x[i] && award_y == y[i]) {
                locateAward();
                break;
            }
        }
    }

    private void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
        for (int i = 0; i < dots; i++) {
            if (apple_x == x[i] && apple_y == y[i] ) {
                locateApple();
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkMushroom();
            checkAward();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (isMoved) {
                if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                    leftDirection = true;
                    upDirection = false;
                    downDirection = false;
                }

                if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                    rightDirection = true;
                    upDirection = false;
                    downDirection = false;
                }

                if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                    upDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }

                if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                    downDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }
                isMoved = false;
            }
        }
    }
}
