package guessNumber;

import commonUtils.Base.BaseBtn;

import javax.swing.*;
import java.awt.*;

public class GuessNumberUI {

    public void showUI(int times) {
        GuessNumber guessNumber = new GuessNumber(times);

        JFrame guessFrame = new JFrame();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("选项");
        JMenuItem jMenuItem = new JMenuItem("选择位数");
        menu.add(jMenuItem);
        menuBar.add(menu);
        jMenuItem.addActionListener(e -> {
            guessFrame.dispose();
            chooseLength();
        });

        guessFrame.setTitle("正在猜" + times + "位数字");
        guessFrame.setSize(300, 400);
        guessFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        guessFrame.setLocationRelativeTo(null);

        JLabel inputTips = new JLabel("输入你猜测的" + times + "位数字");
        guessFrame.add(inputTips);

        JTextField answer = new JTextField();
        Dimension dm1 = new Dimension(280, 30);
        answer.setPreferredSize(dm1);
        guessFrame.add(answer);

        FlowLayout flow = new FlowLayout();
        guessFrame.setLayout(flow);

        JButton confirm = new BaseBtn("确定");
        guessFrame.add(confirm);

        //给按钮添加动作监听器方法
        GuessListener but = new GuessListener();
        //创建一个监听器
        confirm.addActionListener(but);
        but.setJtf(answer, guessNumber, guessFrame);

        guessFrame.setJMenuBar(menuBar);
        guessFrame.setVisible(true);
    }

    public void chooseLength() {
        JFrame chooseFrame = new JFrame();
        chooseFrame.setTitle("选择位数");
        chooseFrame.setSize(300, 200);
        chooseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chooseFrame.setLocationRelativeTo(null);

        JLabel inputTips = new JLabel("输入数字位数");
        chooseFrame.add(inputTips);

        JTextField jtf = new JTextField();
        Dimension dm1 = new Dimension(280, 30);
        jtf.setPreferredSize(dm1);
        chooseFrame.add(jtf);

        FlowLayout flow = new FlowLayout();
        chooseFrame.setLayout(flow);

        JButton startBtn = new BaseBtn("开始");
        chooseFrame.add(startBtn);

        //创建一个监听器
        startBtn.addActionListener(e -> {
            GuessNumberUI sc = new GuessNumberUI();
            chooseFrame.dispose();
            sc.showUI(jtf.getText().toCharArray()[0] - 48);
        });

        //设置可见，放在代码最后一句
        chooseFrame.setVisible(true);
    }
}
