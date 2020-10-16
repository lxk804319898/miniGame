package mineSweeper.arithmetic;

import commonUtils.Jdbc;
import commonUtils.consts.GameNameConsts;
import mineSweeper.bean.MineLevel;

/**
 * 扫雷排行榜分数工具
 */
public class RankScoreUtils {
    public static void uploadScore(long time,MineLevel mineLevel) {
        String gameName = "null";
        if(mineLevel.equals(MineLevel.EASY)) {
            gameName = GameNameConsts.MINESWEEPER_EASY;
        } else if(mineLevel.equals(MineLevel.NORMAL)) {
            gameName = GameNameConsts.MINESWEEPER_NORMAL;
        } else if(mineLevel.equals(MineLevel.HARD)) {
            gameName = GameNameConsts.MINESWEEPER_HARD;
        }

        int score = calculateScore(time, mineLevel);

        new Jdbc().insertScore(gameName,score);
    }

    private static int calculateScore(long time, MineLevel mineLevel){
        int basicScore = 0;
        if(mineLevel.equals(MineLevel.EASY)) {
            basicScore = 100;
        } else if(mineLevel.equals(MineLevel.NORMAL)) {
            basicScore = 500;
        } else if(mineLevel.equals(MineLevel.HARD)) {
            basicScore = 1000;
        }
        return (basicScore-time>=0? (int) (basicScore - time):0) + basicScore/10;
    }


}
