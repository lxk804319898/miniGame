import guessNumber.ButListener;
import guessNumber.Sucess;
import mineSweeper.frame.MineSweeperMain;
import snake.Snake;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame{

    private JPanel  jpanel;
    private JButton guessNumber,snake,jb3,jb4,jb5,jb6,mineSweeperGame;

    public static void main(String[] args) {
        GameMenu guessNumberTest = new GameMenu();
    }

    private GameMenu(){
        //创建组件
        jpanel = new JPanel();
        
        guessNumber = new JButton("猜数字");
        snake = new JButton("贪吃蛇");
        jb3 = new JButton("打飞机");
        jb4 = new JButton("拼图");
        jb5 = new JButton("推箱子");
        jb6 = new JButton("叠方块");
        mineSweeperGame = new JButton("扫雷");

        //布局
        //添加JPanel
         jpanel.add(guessNumber);
         jpanel.add(snake);
         jpanel.add(jb3);
         jpanel.add(jb4);
         jpanel.add(jb5);
         jpanel.add(jb6);
         jpanel.add(mineSweeperGame);

        //添加组件到边界布局BorderLayout
        this.add( jpanel,BorderLayout.CENTER);

        //窗体设置
        this.setTitle("闽侯渔业中心");
        this.setSize(300,200);
        this.setResizable(false);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addActionListener(guessNumber);
        addActionListener(snake);
        addActionListener(jb3);
        addActionListener(jb4);
        addActionListener(jb5);
        addActionListener(jb6);
        addActionListener(mineSweeperGame);
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
            }
        });
    }

}
