package game2048;

import java.awt.*;

public class Ja2048 {

    public static int[][] state = new int[4][4];
    public static int[][] bac = new int[4][4];

    private JF2048 linkF;


    public Ja2048(JF2048 a) {
        this.linkF = a;
        setNull(state, getRandom());
        setNull(state, getRandom());
        setState();
    }

    public void cp0() {
        boolean bool = false;
        for (int i = 1; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (state[i][j] != 0 && (state[i - 1][j] == 0 || state[i - 1][j] == state[i][j]))
                    bool = true;
        if (!bool) return;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                bac[i][j] = state[i][j];

        int[][] b = new int[4][4];
        for (int j = 0; j < 4; j++) {
            int[] a = {state[0][j], state[1][j], state[2][j], state[3][j]};
            b[j] = LierIntArr.drop(a);
        }
        setNull(b, getRandom());
        int[][] x = new int[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                x[i][j] = b[j][i];
        state = x;
        setState();
    }//向左

    public void cp1() {
        boolean bool = false;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                if (state[i][j] != 0 && (state[i + 1][j] == 0 || state[i + 1][j] == state[i][j]))
                    bool = true;
        if (!bool) return;
        bac = state;
        int[][] b = new int[4][4];
        for (int j = 0; j < 4; j++) {
            int[] a = {state[3][j], state[2][j], state[1][j], state[0][j]};
            b[j] = LierIntArr.drop(a);
        }
        setNull(b, getRandom());
        int[][] x = new int[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                x[i][j] = b[j][3 - i];
        state = x;
        setState();
    }//向右

    public void cp2() {
        boolean bool = false;
        for (int i = 0; i < 4; i++)
            for (int j = 1; j < 4; j++)
                if (state[i][j] != 0 && (state[i][j - 1] == 0 || state[i][j - 1] == state[i][j]))
                    bool = true;
        if (!bool) return;
        bac = state.clone();
        int[][] b = new int[4][4];
        for (int i = 0; i < 4; i++)
            b[i] = LierIntArr.drop(state[i]);
        setNull(b, getRandom());
        state = b.clone();
        setState();
    }//向上

    public void cp3() {
        boolean bool = false;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++)
                if (state[i][j] != 0 && (state[i][j + 1] == 0 || state[i][j + 1] == state[i][j]))
                    bool = true;
        if (!bool) return;
        bac = state.clone();
        int[][] b = new int[4][4];
        for (int i = 0; i < 4; i++) {
            int[] a = {state[i][3], state[i][2], state[i][1], state[i][0]};
            b[i] = LierIntArr.drop(a);
        }
        setNull(b, getRandom());
        int[][] x = new int[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                x[i][j] = b[i][3 - j];
        state = x;
        setState();
    }//向下

    public void back() {
        state = bac.clone();
        setState();
    }

    private void setState() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                if (state[i][j] == 0) {
                    linkF.la[i][j].setText("");
                    linkF.la[i][j].setBackground(new Color(227, 227, 227));
                    linkF.la[i][j].setForeground(new Color(0, 0, 0));
                } else if (state[i][j] == 2) {
                    linkF.la[i][j].setText("2");
                    linkF.la[i][j].setFont(new Font("幼圆", 1, 20));
                    linkF.la[i][j].setBackground(new Color(255, 255, 255));
                    linkF.la[i][j].setForeground(new Color(0, 0, 0));
                } else if (state[i][j] == 4) {
                    linkF.la[i][j].setText("4");
                    linkF.la[i][j].setFont(new Font("幼圆", 1, 20));
                    linkF.la[i][j].setBackground(new Color(127, 227, 127));
                    linkF.la[i][j].setForeground(new Color(0, 0, 0));
                } else if (state[i][j] == 8) {
                    linkF.la[i][j].setText("8");
                    linkF.la[i][j].setFont(new Font("幼圆", 1, 20));
                    linkF.la[i][j].setBackground(new Color(0, 127, 127));
                    linkF.la[i][j].setForeground(new Color(255, 255, 255));
                } else if (state[i][j] == 16) {
                    linkF.la[i][j].setText("16");
                    linkF.la[i][j].setFont(new Font("幼圆", 1, 20));
                    linkF.la[i][j].setBackground(new Color(0, 255, 0));
                    linkF.la[i][j].setForeground(new Color(255, 255, 255));
                } else if (state[i][j] == 32) {
                    linkF.la[i][j].setText("32");
                    linkF.la[i][j].setFont(new Font("幼圆", 1, 20));
                    linkF.la[i][j].setBackground(new Color(127, 127, 0));
                    linkF.la[i][j].setForeground(new Color(255, 255, 255));
                } else if (state[i][j] == 64) {
                    linkF.la[i][j].setText("64");
                    linkF.la[i][j].setFont(new Font("幼圆", 1, 20));
                    linkF.la[i][j].setBackground(new Color(255, 0, 0));
                    linkF.la[i][j].setForeground(new Color(255, 255, 255));
                } else if (state[i][j] == 128) {
                    linkF.la[i][j].setText("128");
                    linkF.la[i][j].setFont(new Font("幼圆", 1, 20));
                    linkF.la[i][j].setBackground(new Color(127, 255, 0));
                    linkF.la[i][j].setForeground(new Color(255, 255, 255));
                } else if (state[i][j] == 256) {
                    linkF.la[i][j].setText("256");
                    linkF.la[i][j].setFont(new Font("幼圆", 1, 20));
                    linkF.la[i][j].setBackground(new Color(255, 255, 0));
                    linkF.la[i][j].setForeground(new Color(0, 0, 0));
                } else if (state[i][j] == 512) {
                    linkF.la[i][j].setText("512");
                    linkF.la[i][j].setFont(new Font("幼圆", 1, 20));
                    linkF.la[i][j].setBackground(new Color(255, 255, 0));
                    linkF.la[i][j].setForeground(new Color(0, 0, 0));
                } else if (state[i][j] == 1024) {
                    linkF.la[i][j].setText("1024");
                    linkF.la[i][j].setFont(new Font("幼圆", 1, 16));
                    linkF.la[i][j].setBackground(new Color(63, 63, 63));
                    linkF.la[i][j].setForeground(new Color(255, 255, 255));
                }
            }//for循环

    }//setState方法

    private static int getRandom() {
        int a = (int) (1000 * Math.random());
        if (a % 10 < 3)
            return 4;
        else
            return 2;
    }//随机生成一个2或4，可通过调整判断条件中的数字大小来调整2和4所占的比率

    private static boolean setNull(int[][] x, int y) {
        boolean bool = false;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (x[i][j] == 0) bool = true;
        if (!bool) return false;

        int a = (int) (100 * Math.random());
        int b = (int) (6 + 10 * Math.random());
        int c = a % b;
        while (true) {
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++) {
                    if (x[i][j] == 0 && c <= 0) {
                        x[i][j] = y;
                        return true;
                    } else if (x[i][j] == 0 && c > 0)
                        c--;
                    i = (i == 4 ? 0 : i);
                    j = (j == 4 ? 0 : j);
                }
        }
    }//boolean setNull(int[][],int)方法用于在4x4二维数组中随机挑出一个值为0的元素，并将其赋值为给定整数。特殊地，若该二维数组已满，返回false。


}
