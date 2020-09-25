package guessNumber;

import javax.swing.*;
import java.awt.*;

public class Sucess {

    public void showUI(int times) {
        GuessNumber guessNumber = new GuessNumber(times);

        JFrame jf = new JFrame();
        jf.setTitle("正在猜数字");
        jf.setSize(300, 400);
        jf.setDefaultCloseOperation(3);
        jf.setLocationRelativeTo(null);

        JTextField jtf=new JTextField();
        Dimension dm1=new Dimension(280,30);
        jtf.setPreferredSize(dm1);
        jf.add(jtf);

        FlowLayout flow = new FlowLayout();
        jf.setLayout(flow);

        JButton jbu = new JButton("猜");
        Dimension dm3=new Dimension(80,30);
        jbu.setPreferredSize(dm3);
        jf.add(jbu);

        //给按钮添加动作监听器方法
        GuessListener but = new GuessListener();
        //创建一个监听器
        jbu.addActionListener(but);

        but.setJtf(jtf, guessNumber, jf);

        jf.setVisible(true);
    }
}
