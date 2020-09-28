import guessNumber.ButListener;
import snake.Snake;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame{

    private JPanel jp1;
    private JButton jb1,jb2,jb3,jb4,jb5,jb6;

    public static void main(String[] args) {
        GameMenu guessNumberTest = new GameMenu();
    }

    private GameMenu(){
        //创建组件
        jp1 = new JPanel();
        
        jb1 = new JButton("猜数字");
        jb2 = new JButton("贪吃蛇");
        jb3 = new JButton("打飞机");
        jb4 = new JButton("拼图");
        jb5 = new JButton("推箱子");
        jb6 = new JButton("叠方块");

        //布局
        //添加JPanel
        jp1.add(jb1);
        jp1.add(jb2);
        jp1.add(jb3);
        jp1.add(jb4);
        jp1.add(jb5);
        jp1.add(jb6);

        //添加组件到边界布局BorderLayout
        this.add(jp1,BorderLayout.CENTER);

        //窗体设置
        this.setTitle("闽侯渔业中心");
        this.setSize(300,200);
        this.setResizable(false);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addActionListener(jb1);
        addActionListener(jb2);
        addActionListener(jb3);
        addActionListener(jb4);
        addActionListener(jb5);
        addActionListener(jb6);
        //显示
        this.setVisible(true);
    }

    private void addActionListener(JButton saveButton) {
        // 为按钮绑定监听器
        saveButton.addActionListener(e -> {
            if (e.getSource() == jb1){
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
            }else if (e.getSource() == jb2){
                Snake sn = new Snake();
                sn.showUI();
            }
        });
    }

}
