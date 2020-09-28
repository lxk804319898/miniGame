package tetris;

import java.awt.image.BufferedImage;

public class Cell {
    private int row;//行数
    private int col;//列数
    private BufferedImage image;//图片

    public void left() {
        col--;//向左移动,列数减1
    }
    public void right() {
        col++;//向右移动,列数加1
    }
    public void drop() {
        row++;//向下移动,行数+1
    }

    public Cell() {}
    public Cell(int row, int col, BufferedImage image) {
        super();
        this.row = row;
        this.col = col;
        this.image = image;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
