package snake;

import commonUtils.Jdbc;
import commonUtils.Ranking;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author masgak
 */
public class Snake extends JFrame {
    
    public void showUI() {

        JFrame jf = new JFrame();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("选项");
        JMenuItem jMenuItem = new JMenuItem("查看排行榜");
        JMenuItem beginItem = new JMenuItem("开始");
        menu.add(beginItem);
        menu.add(jMenuItem);
        menuBar.add(menu);

        Board board = new Board();

        jf.add(board);

        jf.setResizable(false);
        jf.pack();

        jf.setTitle("Snake");

        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRanking();
            }
        });

        beginItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                showUI();
            }
        });

        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setJMenuBar(menuBar);
        jf.setVisible(true);
    }

    private void showRanking(){
        JFrame jFrame = new JFrame("Snake排行榜");
        JLabel jl0 = new JLabel();

        JPanel jp = new JPanel();
        Jdbc jdbc = new Jdbc();
        StringBuffer showText = new StringBuffer("<html><br>");
        showText.append("--  == Snake排行 == --<br> ");

        List<Ranking> rankList = jdbc.queryRanking("Snake");
        if(rankList.size() != 0){
            showText.append("<br> ---名称- --- -分数---<br>");
            for(Ranking ranking : rankList){
                showText.append("<br>---" + ranking.getName() +" --- " + ranking.getScores() + "---<br>");
            }
        }else{
            showText.append("<br> --暂无排行-- <br>");
        }
        showText.append("</html>");

        jl0.setText(showText.toString());
        jp.add(jl0);

        jFrame.add(jp);

        jFrame.setVisible(true);
        jFrame.setLocation(300, 400);
        jFrame.setSize(330, 500);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
