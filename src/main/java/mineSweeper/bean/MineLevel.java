package mineSweeper.bean;

public enum MineLevel {
    /**
     * 初级难度
     */
    EASY("1","9_9_10"),
    /**
     * 中级难度
     */
    NORMAL("2","16_16_40"),
    /**
     * 高级难度
     */
    HARD("3","16_30_99"),
    /**
     * 自定义难度
     */
    CUSTOMIZE("4","16_30_99");

    /**
     * 难度代码
     */
    private String levelCode;
    /**
     * 相应难度对应的地图信息 样例: 行_列_雷数
     */
    private String mineInfo;

    MineLevel(String levelCode, String mineInfo) {
        this.levelCode = levelCode;
        this.mineInfo = mineInfo;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public String getMineInfo() {
        return mineInfo;
    }

    public void setMineInfo(String mineInfo) {
        if("4".equals(levelCode)) {
            this.mineInfo = mineInfo;
        }
    }
}
