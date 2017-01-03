package fi.lrkr.twos.gui;

import fi.lrkr.twos.game.Board;
import fi.lrkr.twos.game.Logic;
import fi.lrkr.twos.game.Piece;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GameBoard extends JPanel {

    private Logic logic;
    private Board board;
    private JLayeredPane boardPane;
    private JLabel[][] map;

    public GameBoard(Logic logic) {
        this.logic = logic;
        this.board = logic.getBoard();
        this.map = new JLabel[board.getHeight()][board.getWidth()];
    }
    
    public void init() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        boardPane = new JLayeredPane();
        boardPane.setLayout(new GridLayout(board.getWidth(), board.getHeight()));
        
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                JLabel l = new JLabel();
                map[y][x] = l;
                boardPane.add(l);
            }
        }
        this.add(boardPane);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Piece[][] a = board.getBoard();
        super.paintComponent(g);
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                map[y][x].setText("" + a[y][x].getValue());
            }
        }
    }

    public void reDraw() {
        repaint();
    }
}
