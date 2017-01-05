package fi.lrkr.twos.gui;

import fi.lrkr.twos.game.Logic;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Gui implements Runnable {
    
    private final Logic logic;
    private JFrame frame;
    private GameBoard gameBoard;

    public Gui(Logic logic) {
        this.logic = logic;
    }
    
    public void executeCommand(char c) {
        logic.executeCommand(c);
    }
    
    @Override
    public void run() {
        frame = new JFrame("2048");
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container contentPane) {
        gameBoard = new GameBoard(logic.getBoard());
        gameBoard.init();
        contentPane.add(gameBoard);
        frame.setFocusable(true);
        frame.addKeyListener(new KeyboardListener(this));
    }
    
    public void reDraw() {
        gameBoard.reDraw();
    }
}
