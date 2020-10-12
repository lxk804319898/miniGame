package snake;

import commonUtils.Jdbc;
import guessNumber.ButListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author masgak
 */
public class Snake extends JFrame {
    
    public void showUI() {

        JFrame jf = new JFrame();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("选项");
        JMenuItem jMenuItem = new JMenuItem("查看排行榜");
        menu.add(jMenuItem);
        menuBar.add(menu);

        jf.add(new Board());

        jf.setResizable(false);
        jf.pack();

        jf.setTitle("Snake");

        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                showRanking();
            }
        });

        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setJMenuBar(menuBar);
        jf.setVisible(true);
    }

    private void showRanking(){
        JFrame jFrame = new JFrame("排行榜");
        JLabel jl0 = new JLabel();

        JPanel jp = new JPanel();
        Jdbc jdbc = new Jdbc();
        jdbc.queryRanking("Snake");
        StringBuffer showText = new StringBuffer("<html><br>");
        showText.append("排行<br>排行 " +
                "</html>");
        jl0.setText(showText.toString());
        jp.add(jl0);

        jFrame.add(jp);

        jFrame.setVisible(true);
        jFrame.setLocation(300, 400);
        jFrame.setSize(330, 200);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
