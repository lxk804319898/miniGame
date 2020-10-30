package gameCenter;

import chatRoom.ChatRooms;
import RememberCard.StartRCCardGame;
import commonUtils.Base.BaseBtn;
import commonUtils.consts.MenuParaConsts;
import countDown.CountDown;
import game2048.JF2048;
import game2048.Ja2048;
import guessNumber.GuessNumberUI;
import mineSweeper.frame.MineSweeperMain;
import pushBox.main.PushBoxMainFrame;
import snake.Snake;
import totalGameRank.RankTabs;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame {

    private JButton guessNumber = new BaseBtn("猜数字");
    private JButton snake = new BaseBtn("贪吃蛇");
    private JButton friend = new BaseBtn("同城交友");

    private JButton game2048 = new BaseBtn("2048");
    private JButton moveBox = new BaseBtn("推箱子");
    private JButton countDown = new BaseBtn("倒计时");

    private JButton mineSweeperGame = new BaseBtn("扫雷");
    private JButton rememberCardBtn = new BaseBtn("翻牌记忆");
    private JButton totalRankBtn = new BaseBtn("各游戏排行");



    public static void main(String[] args) {
        new GameMenu();
    }

    public GameMenu() {
        //创建组件
        JPanel mainMenu = new JPanel();

        JLabel menuName = new JLabel("摸鱼项目", JLabel.CENTER);
        menuName.setFont(new Font("微软雅黑", Font.BOLD, 30));

        JLabel copyRight = new JLabel("Copyright © 2020-2020 闽侯渔业中心摸鱼项目组 版权所有", JLabel.CENTER);

        //添加JPanel
        mainMenu.add(guessNumber);
        mainMenu.add(snake);
        mainMenu.add(friend);

        mainMenu.add(game2048);
        mainMenu.add(moveBox);
        mainMenu.add(countDown);

        mainMenu.add(mineSweeperGame);
        mainMenu.add(rememberCardBtn);
        mainMenu.add(totalRankBtn);

        //界面frame布局设置
        getContentPane().add("North", menuName);
        getContentPane().add("Center", mainMenu);
        getContentPane().add("South", copyRight);

        //窗体设置
        this.setTitle("闽侯渔业中心");
        this.setResizable(false);
        this.setBounds((MenuParaConsts.SCREEN_WIDTH - MenuParaConsts.UI_WIDTH) / 2, (MenuParaConsts.SCREEN_HEIGHT - MenuParaConsts.UI_HEIGHT) / 2, MenuParaConsts.UI_WIDTH, MenuParaConsts.UI_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addActionListener(guessNumber);
        addActionListener(snake);
        addActionListener(friend);
        addActionListener(game2048);
        addActionListener(moveBox);
        addActionListener(countDown);
        addActionListener(mineSweeperGame);
        addActionListener(rememberCardBtn);
        addActionListener(totalRankBtn);
        //显示
        this.setVisible(true);
    }

    private void addActionListener(JButton saveButton) {
        // 为按钮绑定监听器
        saveButton.addActionListener(e -> {
            if (e.getSource() == guessNumber) {
                //默认为5
                GuessNumberUI sc = new GuessNumberUI();
                sc.showUI(5);
            } else if (e.getSource() == snake) {
                Snake sn = new Snake();
                sn.showUI();
            } else if (e.getSource() == mineSweeperGame) {
                new MineSweeperMain();
            } else if (e.getSource() == rememberCardBtn) {
                StartRCCardGame.start();
            } else if (e.getSource() == friend) {
                new ChatRooms();
            } else if (e.getSource() == countDown) {
                CountDown countDown = new CountDown();
                countDown.initTime();
            } else if (e.getSource() == totalRankBtn) {
                RankTabs rankTabs = new RankTabs();
                rankTabs.initRankTabs();
            } else if (e.getSource() == game2048) {
                JF2048 jf = new JF2048();
                jf.setJa(new Ja2048(jf));
            } else if (e.getSource() == moveBox) {
                new PushBoxMainFrame();
            } else {
                JOptionPane.showMessageDialog(null, "尚未开发，敬请期待！");
            }
        });
    }

}
