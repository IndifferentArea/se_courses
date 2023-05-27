package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//这个类的作用就是充当“棋盘”
public class GameBoardPanel extends JPanel {

    // some redefined MAGIC number
    private static final long serialVersionUID = 1L;
    public static final int CELL_SIZE = 30;

    final int numMines;
    int flag_number, UsedTime , RevealMines, MineMagicNum;
    final int ROWS, COLS, CANVAS_WIDTH, CANVAS_HEIGHT;

    // some Icons
    public static final ImageIcon MineIcon = new ImageIcon(new ImageIcon("./src/minesweeper/icon/th.jpg").
            getImage().getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_DEFAULT));
    public static final ImageIcon FlagIcon = new ImageIcon(new ImageIcon("./src/minesweeper/icon/flag.jpg").
            getImage().getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_DEFAULT));
    Cell[][] cells;

    private void initDynaArgus(){
        flag_number = numMines;
        UsedTime = 0;
        RevealMines = 0;
    }


    public GameBoardPanel() {
        MineMagicNum = getMagic();
        numMines = MineMap.values[MineMagicNum].MINES;
        initDynaArgus();
        ROWS = MineMap.values[MineMagicNum].ROW;
        COLS = MineMap.values[MineMagicNum].COL;
        CANVAS_HEIGHT = CELL_SIZE * ROWS;
        CANVAS_WIDTH = CELL_SIZE * COLS;
        cells = new Cell[ROWS][COLS];
        super.setLayout(new GridLayout(ROWS, COLS, 1, 1));
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]);
            }
        }
        // [TODO 3] 为所有的Cell单元对象创建一个共享的鼠标事件监听器
        CellMouseListener listener = new CellMouseListener();
        // [TODO 4] 通过下面的循环，将每个Cell对象的鼠标事件监听器对象设为listener
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].addMouseListener(listener);
            }
        }
        super.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    }

    // get Magic num with temporal dialog.
    public int getMagic() {
        Object[] options = {"easy", "intermediate", "hard"};  //自定义按钮上的文字
        return JOptionPane.showOptionDialog(null, "请选择难度",
                "New Game", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
    }

    // 初始化一个新的游戏
    public void newGameOldPanel() {
        // 通过MineMap获得新游戏中的地雷数据的布局
        MineMap mineMap = new MineMap(MineMagicNum);
        initDynaArgus();
        // 根据mineMap中的数据初始化每个Cell单元对象
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].newGame(mineMap.isMined[row][col]);
            }
        }
    }

    // 获得[srcRow, srcCol]Cell单元对象周围的8个邻居的地雷总数
    private int getSurroundingMines(int srcRow, int srcCol) {
        int numMines = 0;
        for (int row = srcRow - 1; row <= srcRow + 1; ++row) {
            for (int col = srcCol - 1; col <= srcCol + 1; ++col) {
                if (row >= 0 && row < ROWS && col >= 0 && col < COLS)
                    if (cells[row][col].isMined) numMines++;
            }
        }
        return numMines;
    }

    // 对[srcRow, srcCol]Cell单元对象执行挖雷操作
    // 如果该单元格对象中的标记的雷的数量为0，那么就自动递归对其周围8个邻居执行挖雷操作
    private void revealCell(int srcRow, int srcCol) {
        if (cells[srcRow][srcCol].isFlagged) flag_number++;
        int resideMine = getSurroundingMines(srcRow, srcCol);
        cells[srcRow][srcCol].setText(resideMine + "");
        cells[srcRow][srcCol].isRevealed = true;
        cells[srcRow][srcCol].paint();
        if (resideMine == 0) {
            for (int row = srcRow - 1; row <= srcRow + 1; ++row) {
                for (int col = srcCol - 1; col <= srcCol + 1; ++col) {
                    if (row >= 0 && row < ROWS && col >= 0 && col < COLS)
                        if (!cells[row][col].isRevealed) revealCell(row, col);
                }
            }
        }
    }

    public void showAllMines() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++) if (cells[i][j].isMined) cells[i][j].setIcon(MineIcon);
    }

    // [TODO 2] 定义一个内部类，该类的作用为鼠标事件监听器
    private class CellMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            // 获得触发此次鼠标事件的Cell对象
            Cell sourceCell = (Cell) e.getSource();
            if (sourceCell.isRevealed) return;
            // 获得鼠标事件的类型，MouseEvent.BUTTON1为单击鼠标左键
            if (e.getButton() == MouseEvent.BUTTON1) {
                // [TODO 5] 如果当前Cell对象里面有地雷，则游戏结束；否则对该Cell对象执行挖雷操作
                if (sourceCell.isMined) {
                    sourceCell.setIcon(MineIcon);
                    System.out.println("Game Over");
                    sourceCell.isRevealed = true;
                    showAllMines();
                    int result_of_dialog = JOptionPane.showConfirmDialog(((Cell) e.getSource()).getRootPane(),
                            "Game Over!\n\nDo you want a new game?", "", JOptionPane.YES_NO_OPTION);
                    if (result_of_dialog == 0) newGameOldPanel();
                } else {
                    if (sourceCell.isFlagged) {
                        sourceCell.isFlagged = false;
                        flag_number++;
                    }
                    revealCell(sourceCell.row, sourceCell.col);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) { //MouseEvent.BUTTON3为单击鼠标右键
                // [TODO 6] 如果该Cell对象上插了旗子，那么就去掉旗子；否则将该Cell对象打上旗子的标记。
                if (sourceCell.isFlagged) {
                    sourceCell.setIcon(null);
                    sourceCell.isFlagged = false;
                    if (sourceCell.isMined) RevealMines--;
                    flag_number++;
                } else if (flag_number == 0) {
                    JOptionPane.showConfirmDialog(((Cell) e.getSource()).getRootPane(),
                            "ERROR!!!\n\nYou have run out of flags", "", JOptionPane.DEFAULT_OPTION);
                } else {
                    flag_number--;
                    if (sourceCell.isMined) RevealMines++;
                    sourceCell.setIcon(FlagIcon);
                    sourceCell.isFlagged = true;
                    // [TODO 7] 当对Cell单元格对象执行了挖雷操作之后判断玩家是否赢得该游戏
                    if (RevealMines == numMines) {
                        JOptionPane.showConfirmDialog(((Cell) e.getSource()).getRootPane(),
                                "WIN!!!\n\nYou have found all " + numMines + " mines in " + UsedTime + "seconds.\n" +
                                        "Do you want a new game?", "WIN", JOptionPane.DEFAULT_OPTION);
                        newGameOldPanel();
                    }
                }
            }
        }
    }
}


