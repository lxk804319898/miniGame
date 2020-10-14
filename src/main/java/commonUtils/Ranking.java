package commonUtils;

public class Ranking {
    private String gameName; //游戏名
    private String name;     //水B名
    private Integer scores;  //分数

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "gameName='" + gameName + '\'' +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }
}
