package tetris.block;

import tetris.Cell;
import tetris.State;
import tetris.Tetris;
import tetris.Tetromino;

public class T extends Tetromino {
    /**
     * 提供构造器，进行初始化
     * T型的四格方块的位置
     * */
    public T() {
        cells[0]=new Cell(0,4, Tetris.T);
        cells[1]=new Cell(0,3,Tetris.T);
        cells[2]=new Cell(0,5,Tetris.T);
        cells[3]=new Cell(1,4,Tetris.T);
        states = new State[4];
        states[0] = new State(0,0,0,-1,0,1,1,0);//状态0
        states[1] = new State(0,0,-1,0,1,0,0,-1);//状态1
        states[2] = new State(0,0,0,1,0,-1,-1,0);//状态2
        states[3] = new State(0,0,1,0,-1,0,0,1);//状态三
    }
}
