package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 挖雷游戏的主程序。
 * 单击鼠标左键对单元格执行挖雷操作。
 * 单击鼠标右键用来对单元格执行添加标记，或者移除标记，标记疑似有地雷的单元格。
 * 如果所有没有地雷的单元格都执行了挖雷操作，那么玩家赢得游戏。
 * 如果对某个有地雷的单元格执行了挖雷操作，那么玩家输。
 */
public class MineSweeperMain extends JFrame {
    private static final long serialVersionUID = 1L;
    JTextField gameTXT = new JTextField();
    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame = new JButton("New Game");
    JMenuBar GameMenu = new JMenuBar();
    JMenu MenuFile = new JMenu("File");
    JMenuItem MenuNewGame = new JMenuItem("New Game");
    JMenuItem MenuResetGame = new JMenuItem("Reset Game");
    JMenuItem MenuExit = new JMenuItem("Exit");

    public MineSweeperMain() {
        GameMenu.add(MenuFile);
        JPanel top = new JPanel();
        top.add(GameMenu, BorderLayout.WEST);
        top.add(gameTXT, BorderLayout.EAST);
        Container cp = this.getContentPane();
        gameTXT.setEditable(false);
        gameTXT.setAlignmentX(CENTER_ALIGNMENT);
        gameTXT.setAlignmentY(CENTER_ALIGNMENT);

        MenuFile.add(MenuNewGame);
        MenuFile.add(MenuResetGame);
        MenuFile.add(MenuExit);

        cp.setLayout(new BorderLayout());
        cp.add(top, BorderLayout.NORTH);
        cp.add(board, BorderLayout.CENTER);
        cp.add(btnNewGame, BorderLayout.SOUTH);
        // 使用匿名类的方式为 btnNewGame 按钮添加 Action 事件监听器
        btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                board.newGameOldPanel();
            }
        });
        MenuNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                board.newGameOldPanel();
            }
        });
        MenuExit.addActionListener(e -> System.exit(0));
        MenuResetGame.addActionListener(e -> {
            dispose();
            MineSweeperMain newMS = new MineSweeperMain();
        });

        board.newGameOldPanel();
        TimeCount tc = new TimeCount();
        tc.start();
        pack(); // Pack the UI components, instead of setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setVisible(true);
    }

    // a Thread to maintain the text field.
    private class TimeCount extends Thread {
        public void run() {
            while (true) {
                try {
                    ++board.UsedTime;
                    for (int i = 0; i < 20; ++i) {
                        gameTXT.setText("time : " + board.UsedTime + "s\t\tleft flags : " + board.flag_number);
                        Thread.sleep(50);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        // [TODO 1] 使用安全的方式启动下面的构造函数
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MineSweeperMain();
            }
        });
    }
}
