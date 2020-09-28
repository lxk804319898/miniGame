package tetris;

import tetris.block.*;

public class Tetromino {
    protected Cell[] cells = new Cell[4];
    protected State[] states;
    private int count = 100000;//数值多少都可以

    public void moveLeft() {//向左移动
        for(int i=0;i<cells.length;i++) {//for循环遍历整个"方块组"的四格方块
            Cell cell = cells[i];//四格方块都要移动
            cell.left();
        }
    }
    public void moveRight() {//向右移动
        for(Cell c:cells) {//此处使用增强for循环也可以
            c.right();
        }
    }
    public void softDrop() {//下落
        for(Cell c:cells) {
            c.drop();
        }
    }

    public void rotateRight() {//向右旋转
        //旋转有一次，计算器增长1
        count++;//100001
        State s = states[count%states.length];
        Cell c = cells[0];
        int row = c.getRow();
        int col = c.getCol();
        cells[1].setRow(row+s.row1);
        cells[1].setCol(col+s.col1);
        cells[2].setRow(row+s.row2);
        cells[2].setCol(col+s.col2);
        cells[3].setRow(row+s.row3);
        cells[3].setCol(col+s.col3);
    }

    public void rotateLeft() {//向左旋转方法有什么用呢?稍后就会就知道了.
        count--;//100001
        State s = states[count%states.length];
        Cell c = cells[0];
        int row = c.getRow();
        int col = c.getCol();
        cells[1].setRow(row+s.row1);
        cells[1].setCol(col+s.col1);
        cells[2].setRow(row+s.row2);
        cells[2].setCol(col+s.col2);
        cells[3].setRow(row+s.row3);
        cells[3].setCol(col+s.col3);
    }


    public static Tetromino randomOne() {
//随机生成方块,七种方块形状分别为O,T,I,J,L,S,Z
        Tetromino  t = null;
        int num = (int)(Math.random()*7);
        switch(num) {
            case 0:t = new O();break;
            case 1:t = new T();break;
            case 2:t = new I();break;
            case 3:t = new J();break;
            case 4:t = new L();break;
            case 5:t = new S();break;
            case 6:t = new Z();break;
        }
        return t;
    }
}
