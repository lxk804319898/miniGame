package commonUtils;

public class Ranking {
    private String name;
    private Integer scores;

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
                "name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }
}
