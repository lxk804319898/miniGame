package game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JF2048 extends JFrame {

    private static final long serialVersionUID = 1L;

    private Ja2048 ja;

    public Ja2048 getJa() {
        return ja;
    }

    public void setJa(Ja2048 ja) {
        this.ja = ja;
    }

    public JButton b[] = {
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton()
    };

    public JButton back = new JButton("back");

    private ActionListener b0 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ja.cp0();
        }
    };

    private ActionListener b1 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ja.cp1();
        }
    };

    private ActionListener b2 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ja.cp2();
        }
    };

    private ActionListener b3 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ja.cp3();
        }
    };

    private ActionListener back1 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ja.back();
        }
    };

    public JLabel[][] la = {
            {new JLabel(), new JLabel(), new JLabel(), new JLabel()},
            {new JLabel(), new JLabel(), new JLabel(), new JLabel()},
            {new JLabel(), new JLabel(), new JLabel(), new JLabel()},
            {new JLabel(), new JLabel(), new JLabel(), new JLabel()},
    };


    public JF2048() {

        super("game2048");

        //this.addKeyListener(x);

        b[0].setBounds(3, 20, 16, 156);
        b[1].setBounds(178, 20, 16, 156);
        b[2].setBounds(20, 3, 156, 16);
        b[3].setBounds(20, 178, 156, 16);
        back.setBounds(3, 3, 16, 16);

        b[0].addActionListener(b0);
        b[1].addActionListener(b1);
        b[2].addActionListener(b2);
        b[3].addActionListener(b3);
        back.addActionListener(back1);


        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                la[i][j].setBounds(20 + 40 * i, 20 + 40 * j, 36, 36);
                la[i][j].setOpaque(true);
                //la[i][j].setFont(new Font("幼圆",1,24));
                la[i][j].setHorizontalAlignment(SwingConstants.CENTER);
            }

        this.setSize(217, 238);
        //this.add(b[0]);
        //this.add(b[1]);
        //this.add(b[2]);
        //this.add(b[3]);
        //this.add(back);
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                this.add(la[i][j]);
        JLabel p = new JLabel();
        p.setBackground(new Color(127, 127, 127));
        p.setOpaque(true);
        this.add(p);
        this.addKeyListener(new KeyAdapter()//键盘监听按钮
        {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        ja.cp2();
                        break;
                    case KeyEvent.VK_DOWN:
                        ja.cp3();
                        break;
                    case KeyEvent.VK_RIGHT:
                        ja.cp1();
                        break;
                    case KeyEvent.VK_LEFT:
                        ja.cp0();
                        break;
                    case KeyEvent.VK_BACK_SPACE:
                        ja.back();
                }
            }

        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JF2048 jf = new JF2048();
        jf.ja = new Ja2048(jf);
    }
}
