package fi.lrkr.twos.gui;

import fi.lrkr.twos.game.Logic;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScorePanel extends JPanel {

    private Logic logic;
    private JLabel score;
    private JLabel highScore;
    private JLabel moves;

    /**
     * Constructor for creating a new ScorePanel.
     * 
     * @param logic The Logic object which is used for stats
     */
    public ScorePanel(Logic logic) {
        this.logic = logic;
    }
    
    /**
     * Initializes the ScorePanel by adding Moves, Score and Highscore displays.
     */
    public void init() {
        this.setLayout(new GridLayout(1, 3));
        
        moves = new JLabel();
        moves.setHorizontalAlignment(SwingConstants.LEFT);
        moves.setFont(moves.getFont().deriveFont(16.0f));
        moves.setText("placeholder because otherwise nothing will show up..");
        this.add(moves);        
        
        score = new JLabel();
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setFont(score.getFont().deriveFont(16.0f));
        this.add(score);
        
        highScore = new JLabel();
        highScore.setHorizontalAlignment(SwingConstants.RIGHT);
        highScore.setFont(highScore.getFont().deriveFont(16.0f));
        this.add(highScore);        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        score.setText("Score: " + logic.getScore());
        highScore.setText("High score: " + logic.getHighScore() + " ");
        moves.setText(" Moves: " + logic.getMoves());
    }

    /**
     * Repaints the ScorePanel.
     */
    public void reDraw() {
        repaint();
    }

}
