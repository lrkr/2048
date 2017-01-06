package fi.lrkr.twos.gui;

import fi.lrkr.twos.game.Logic;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class provides the graphical user interface for the game.
 */
public class Gui implements Runnable {

    private final Logic logic;
    private JFrame frame;
    private GameBoard gameBoard;
    private ScorePanel scorePanel;

    /**
     * Constructor for creating the Gui object.
     * 
     * @param logic Logic object which the Gui controls
     */
    public Gui(Logic logic) {
        this.logic = logic;
    }

    /**
     * Passes player's command from KeyBoardListener to Logic.
     * 
     * @param c Char representing the command
     */
    public void executeCommand(char c) {
        logic.executeCommand(c);
    }

    @Override
    public void run() {
        frame = new JFrame("2048");
        frame.setPreferredSize(new Dimension(600, 680));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);        
    }

    private void createComponents(Container content) {
        content.setLayout(new BorderLayout());
        scorePanel = new ScorePanel(logic);
        scorePanel.setPreferredSize(new Dimension(600, 40));
        scorePanel.init();
        gameBoard = new GameBoard(logic.getBoard());
        gameBoard.setPreferredSize(new Dimension(600, 600));
        gameBoard.init();        
        content.add(scorePanel, BorderLayout.NORTH);
        content.add(gameBoard, BorderLayout.SOUTH);        
        frame.setFocusable(true);
        frame.addKeyListener(new KeyboardListener(this));
    }

    /**
     * Shows an end game dialog which displays score and asks if player wants a
     * new game or not.
     * 
     * @param score Score of the just ended game
     */
    public void gameOverDialog(int score) {
        if (JOptionPane.showConfirmDialog(frame, "Game Over! Score: " + score + "\nNew game?", "Game Over", JOptionPane.YES_NO_OPTION) == 1) {
            System.exit(0);
        }
    }

    /**
     * Repaints the GameBoard and ScorePanel. Called after each move and when
     * starting a new game.
     */
    public void reDraw() {
        gameBoard.reDraw();
        scorePanel.reDraw();
    }

    public JFrame getFrame() {
        return frame;
    }
}
