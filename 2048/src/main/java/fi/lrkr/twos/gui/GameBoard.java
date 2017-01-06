package fi.lrkr.twos.gui;

import fi.lrkr.twos.game.Board;
import fi.lrkr.twos.game.Piece;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameBoard extends JPanel {

    private Board board;
    private JLayeredPane boardPane;
    private JLabel[][] labelMap;
    private Map<Integer, Color> colors;

    /**
     * Constructor for creating a new GameBoard object which handles displaying
     * current game state.
     * 
     * @param board Board which is to be displayed
     */
    public GameBoard(Board board) {
        this.board = board;
        this.labelMap = new JLabel[board.getHeight()][board.getWidth()];
        this.colors = new HashMap<>();        
    }

    /**
     * Initializes the GameBoard by creating JLabels for each of Board's cells
     * and setting correct settings for them. Also adds colors to a map for
     * easier coloring later.
     */
    public void init() {        
        this.setLayout(new BorderLayout());
        boardPane = new JLayeredPane();
        boardPane.setLayout(new GridLayout(board.getHeight(), board.getWidth()));

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                JLabel l = new JLabel();
                l.setHorizontalAlignment(SwingConstants.CENTER);
                l.setFont(l.getFont().deriveFont(64.0f));
                l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                l.setOpaque(true);
                labelMap[y][x] = l;
                boardPane.add(l);
            }
        }
        this.add(boardPane);
        
        colors.put(1, Color.YELLOW);
        colors.put(2, Color.ORANGE);
        colors.put(3, Color.PINK);
        colors.put(4, Color.RED);
        colors.put(5, Color.MAGENTA);
        colors.put(6, Color.CYAN);
        colors.put(7, Color.BLUE);
        colors.put(8, Color.GREEN);
        colors.put(9, new Color(100, 100, 125));
        colors.put(10, Color.GRAY);
        colors.put(11, new Color(75, 5, 75));
        colors.put(12, new Color(1, 25, 100));
        colors.put(13, new Color(100, 1, 25));
        colors.put(14, new Color(1, 100, 25));
        colors.put(15, new Color(5, 75, 76));
    }

    @Override
    public void paintComponent(Graphics g) {
        Piece[][] b = board.getBoard();
        super.paintComponent(g);
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (b[y][x].getValue() == 0) {
                    labelMap[y][x].setText("");
                    labelMap[y][x].setBackground(Color.WHITE);
                } else {
                    labelMap[y][x].setText("" + b[y][x].getValue());
                    Color c = colors.get(b[y][x].getExp());
                    if (c != null) {
                        labelMap[y][x].setBackground(c);
                    } else {
                        labelMap[y][x].setBackground(Color.WHITE);
                    }
                    
                }
            }
        }
    }

    /**
     * Repaints the GameBoard.
     */
    public void reDraw() {
        repaint();
    }
}
