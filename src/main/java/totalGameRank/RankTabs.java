package totalGameRank;

import commonUtils.Jdbc;
import commonUtils.Ranking;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @description: 游戏总排行
 * @author: LaiXinFang
 * @time: 2020/10/15
 */

public class RankTabs extends JFrame {

    public void initRankTabs() {
        Jdbc jdbc = new Jdbc();
        List<Ranking> rankList = jdbc.queryTotalRanking();
        // tab组件
        JTabbedPane tabbedPane = buildJTabbedPane(rankList);
        buildFrame(tabbedPane);
    }


    /**
     * 获取对应游戏排行榜面板
     * @param rankList
     * @return
     */
    private JTabbedPane buildJTabbedPane(List<Ranking> rankList){
        // 选项卡面板
        JTabbedPane tabbedPane = new JTabbedPane();
        // 通过BorderFactory来设置边框的特性
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // 获取各类游戏 分别写入榜单
        HashSet<String> gameType = new HashSet<>();
        for(Ranking ranking : rankList){
            gameType.add(ranking.getGameName());
        }

        for(String s : gameType){
            List<Ranking> singleRankList = new ArrayList<>();
            for(Ranking ranking : rankList){
                if(ranking.getGameName().equals(s)){
                    singleRankList.add(ranking);
                }
            }

            JPanel jPanel = new JPanel();
            buildRankContent(jPanel , singleRankList);
            tabbedPane.add(s, jPanel);

        }

        return tabbedPane;
    }

    /**
     * 添加备注
     * @param jPanel
     * @param rankingList
     */
    private void buildRankContent(JPanel jPanel , List<Ranking> rankingList){
        // 添加写入内容
        JLabel jLabel = new JLabel();

        StringBuffer showText = new StringBuffer("<html><br>");
        showText.append("--  == "+ rankingList.get(0).getGameName() +"排行 == --<br> ");

        if(rankingList.size() != 0){
            showText.append("<br> ---名称- --- -分数---<br>");
            for(Ranking ranking : rankingList){
                showText.append("<br>---" + ranking.getName() +" --- " + ranking.getScores() + "---<br>");
            }
        }else{
            showText.append("<br> --暂无排行-- <br>");
            System.out.println("Hello World");
            System.out.println("Hello 个 der");
            System.out.println("Hello");
        }
        showText.append("</html>");
        jLabel.setText(showText.toString());
        jPanel.add(jLabel);
    }

    private void buildFrame(JComponent component) {
        // 窗体容器
        JFrame frame = new JFrame("排行榜");
        frame.add(component);
        //  JFrame.EXIT_ON_CLOSE  退出
        //  JFrame.HIDE_ON_CLOSE  最小化隐藏
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // 设置布局
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.CENTER, component);
        // 设置窗口最小尺寸
        frame.setMinimumSize(new Dimension(400, 560));
        // 调整此窗口的大小，以适合其子组件的首选大小和布局
        frame.pack();
        // 设置窗口相对于指定组件的位置
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // 设置窗口尺寸是否固定不变
        frame.setResizable(true);
    }







}
