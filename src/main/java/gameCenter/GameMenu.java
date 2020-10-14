package gameCenter;

import chatRoom.ChatRooms;
import RememberCard.StartRCCardGame;
import countDown.CountDown;
import guessNumber.Sucess;
import mineSweeper.frame.MineSweeperMain;
import snake.Snake;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame{

    private JButton guessNumber;
    private JButton snake;
    private JButton friend;
    private JButton countDown;
    private JButton mineSweeperGame;
    private JButton rememberCardBtn;

    public static void main(String[] args) {
        GameMenu gameMenu = new GameMenu();
    }

    public GameMenu(){
        //创建组件
        JPanel jpanel = new JPanel();

        guessNumber = new JButton("猜数字");
        snake = new JButton("贪吃蛇");
        friend = new JButton("同城交友");
        JButton jb4 = new JButton("拼图");
        JButton jb5 = new JButton("推箱子");
        countDown = new JButton("倒计时");
        mineSweeperGame = new JButton("扫雷");
        rememberCardBtn = new JButton("翻牌记忆");

        //添加JPanel
         jpanel.add(guessNumber);
         jpanel.add(snake);
         jpanel.add(friend);
         jpanel.add(jb4);
         jpanel.add(jb5);
         jpanel.add(countDown);
         jpanel.add(mineSweeperGame);
         jpanel.add(rememberCardBtn);

        //添加组件到边界布局BorderLayout
        this.add(jpanel,BorderLayout.CENTER);

        //窗体设置
        this.setTitle("闽侯渔业中心");
        this.setSize(300,200);
        this.setResizable(false);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addActionListener(guessNumber);
        addActionListener(snake);
        addActionListener(friend);
        addActionListener(jb4);
        addActionListener(jb5);
        addActionListener(countDown);
        addActionListener(mineSweeperGame);
        addActionListener(rememberCardBtn);
        //显示
        this.setVisible(true);
    }

    private void addActionListener(JButton saveButton) {
        // 为按钮绑定监听器
        saveButton.addActionListener(e -> {
            if (e.getSource() == guessNumber){
                //默认为5
                Sucess sc = new Sucess();
                sc.showUI(5);
            }else if (e.getSource() == snake){
                Snake sn = new Snake();
                sn.showUI();
            } else if (e.getSource() == mineSweeperGame){
                new MineSweeperMain();
            } else if (e.getSource() == rememberCardBtn){
                StartRCCardGame.start();
            } else if (e.getSource() == friend){
                new ChatRooms();
            } else if (e.getSource() == countDown){
                CountDown countDown = new CountDown();
                countDown.initTime();
            }
        });
    }

}
