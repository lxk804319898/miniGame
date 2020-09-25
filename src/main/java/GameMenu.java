import guessNumber.ButListener;

import javax.swing.*;
import java.awt.*;

public class GameMenu {

    public static void main(String[] args) {
        GameMenu guessNumberTest = new GameMenu();
        guessNumberTest.showUI();
    }

    public void showUI() {
        JFrame jf = new JFrame();
        jf.setTitle("猜数字");
        jf.setSize(300, 400);
        jf.setDefaultCloseOperation(3);
        jf.setLocationRelativeTo(null);

        JTextField jtf = new JTextField();
        Dimension dm1 = new Dimension(280, 30);
        jtf.setPreferredSize(dm1);
        jf.add(jtf);

        FlowLayout flow = new FlowLayout();
        jf.setLayout(flow);

        JButton jbu = new JButton("开始");
        Dimension dm3 = new Dimension(80, 30);
        jbu.setPreferredSize(dm3);
        jf.add(jbu);

        //给按钮添加动作监听器方法
        ButListener but = new ButListener();
        //创建一个监听器
        jbu.addActionListener(but);
        but.setJt(jtf);

        jf.setVisible(true);   //设置可见，放在代码最后一句
    }

}
