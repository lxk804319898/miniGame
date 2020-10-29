package guessNumber;

import commonUtils.Base.BaseBtn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GuessNumberUI {

    public void showUI(int times) {
        GuessNumber guessNumber = new GuessNumber(times);

        JFrame guessFrame = new JFrame();

        JMenuBar menuBar = new JMenuBar();
        JMenu gameOptions = new JMenu("选项");

        JMenuItem chooseLength = new JMenuItem("选择位数");
        chooseLength.addActionListener(e -> {
            guessFrame.dispose();
            chooseLength();
        });
        gameOptions.add(chooseLength);

        JMenuItem rules = new JMenuItem("游戏规则");
        rules.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "有一个没有重复数字的n位数。每猜一次，根据这个数字给出几A几B。" +
                    "其中A前面的数字表示位置正确的数的个数，而B前的数字表示数字正确而位置不对的数的个数。当结果是n 0时表示猜对该数字");
        });
        gameOptions.add(rules);

        menuBar.add(gameOptions);
        guessFrame.setJMenuBar(menuBar);

        guessFrame.setTitle("正在猜" + times + "位数字");
        guessFrame.setSize(300, 400);
        guessFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        guessFrame.setLocationRelativeTo(null);

        JLabel inputTips = new JLabel("输入你猜测的" + times + "位数字");
        guessFrame.add(inputTips);

        JTextField answer = new JTextField();
        Dimension dm1 = new Dimension(280, 30);
        answer.setPreferredSize(dm1);
        answer.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER)   //按回车键执行相应操作;
                {
                    String guess = answer.getText();
                    answer.setText("");
                    String result = guessNumber.guess(guess);
                    JLabel jLabel = new JLabel();
                    Dimension resultDm = new Dimension(280, 20);
                    jLabel.setPreferredSize(resultDm);
                    guessFrame.add(jLabel);
                    jLabel.setText(result + "   " + guess);
                    if (result.charAt(4) - 48 == times) {
                        JOptionPane.showMessageDialog(null, "游戏胜利！");
                    }
                }
            }
        });
        guessFrame.add(answer);

        FlowLayout flow = new FlowLayout();
        guessFrame.setLayout(flow);

        JButton confirm = new BaseBtn("确定");
        confirm.addActionListener(e -> {
            String guess = answer.getText();
            answer.setText("");
            String result = guessNumber.guess(guess);
            JLabel jLabel = new JLabel();
            Dimension resultDm = new Dimension(280, 20);
            jLabel.setPreferredSize(resultDm);
            guessFrame.add(jLabel);
            jLabel.setText(result + "   " + guess);
            if (result.charAt(4) - 48 == times) {
                JOptionPane.showMessageDialog(null, "游戏胜利！");
            }
        });
        guessFrame.add(confirm);
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
        jtf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER)   //按回车键执行相应操作;
                {
                    GuessNumberUI sc = new GuessNumberUI();
                    chooseFrame.dispose();
                    sc.showUI(jtf.getText().toCharArray()[0] - 48);
                }
            }
        });
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
