package guessNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sucess {

    public void showUI(int times) {
        GuessNumber guessNumber = new GuessNumber(times);

        JFrame jf = new JFrame();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("选项");
        JMenuItem jMenuItem = new JMenuItem("选择数字");
        menu.add(jMenuItem);
        menuBar.add(menu);

        jf.setTitle("正在猜数字");
        jf.setSize(300, 400);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                showNumber();
            }
        });

        but.setJtf(jtf, guessNumber, jf);

        jf.setJMenuBar(menuBar);
        jf.setVisible(true);
    }

    public void showNumber(){
        // 对话框
        JFrame jf = new JFrame();
        jf.setTitle("猜数字");
        jf.setSize(300, 400);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        //设置可见，放在代码最后一句
        jf.setVisible(true);
    }
}
