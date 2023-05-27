package minesweeper;

import java.util.Random;

/** 这个类主要用来存储地雷在单元格中的位置，目前这个类只是一个示意，所以地雷都是固定位置。 */
public class MineMap {

    static class ValueModel {
        int ROW;
        int COL;
        int MINES;

        public ValueModel(int r, int c, int m) {
            ROW = r;
            COL = c;
            MINES = m;
        }
    }

    public static ValueModel[] values = {
            new ValueModel(9, 9, 10),
            new ValueModel(16, 16, 40),
            new ValueModel(16, 30, 99)
    };


    int numMines;
    int row;
    int col;
    boolean[][] isMined;

    public MineMap(int MineMagicNum) {
        this.numMines = values[MineMagicNum].MINES;
        this.row = values[MineMagicNum].ROW;
        this.col = values[MineMagicNum].COL;
        isMined = new boolean[row][col];
        Randomize();
    }

    public void Randomize(){
        for(int i = 0;i<row;i++){
            for (int j = 0;j<col;j++){
                isMined[i][j] = false;
            }
        }
        Random ran = new Random();
        for (int i = 0; i < numMines; i++) {
            int j = ran.nextInt(0, row);
            int k = ran.nextInt(0, col);
            if (isMined[j][k]) i--;
            else isMined[j][k] = true;
        }
    }
}
