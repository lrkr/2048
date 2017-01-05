package fi.lrkr.twos.game;

import fi.lrkr.twos.file.ScoreReader;
import fi.lrkr.twos.file.ScoreWriter;
import fi.lrkr.twos.gui.Gui;

/**
 * Class provides the core functionality of the game.
 */
public class Logic {

    private Board board;
    private int moves;
    private int score;
    private int highScore;
    private Gui gui;

    public Logic() {
        this(4, 4, 2);
    }

    public Logic(int height, int width, int start) {
        this.board = new Board(height, width, start);
        this.moves = 0;
        this.score = 0;
        this.highScore = 0;
    }

    public void start() {
        ScoreReader fr = new ScoreReader();
        this.highScore = fr.readHighScore("score.txt");
        board.init();
        board.addNew();
    }

    public void restart() {
        this.moves = 0;
        this.score = 0;
        board.init();
        board.addNew();
        gui.reDraw();
    }

    /**
     * Executes a game command, checks if move happened and if it resulted in a
     * loss.
     *
     * @param c Char representing the command
     */
    public void executeCommand(char c) {
        int[][] boardValuesBeforeMove = board.getBoardValues();
        int moveScore = 0;

        switch (c) {
            case 'U':
                moveScore = board.moveUp();
                break;
            case 'L':
                moveScore = board.moveLeft();
                break;
            case 'D':
                moveScore = board.moveDown();
                break;
            case 'R':
                moveScore = board.moveRight();
                break;
            case 'X':
                gameOver();
                return;
        }
        if (checkIfMoveHappened(boardValuesBeforeMove, board.getBoardValues())) {
            score += moveScore;
            if (score > highScore) {
                highScore = score;
            }
            board.addNew();
            moves++;
        }
        gui.reDraw();
        if (checkLoss()) {
            gameOver();
        }
    }

    /**
     * Checks if move happened after it was executed by checking if any Piece
     * moved.
     *
     * @param boardValuesBeforeMove 2d array of Board's Pieces' values before
     * move
     * @param boardValuesAfterMove 2d array of Board's Pieces' values after move
     * @return True if at least one Piece moved, false otherwise
     */
    public boolean checkIfMoveHappened(int[][] boardValuesBeforeMove, int[][] boardValuesAfterMove) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (boardValuesBeforeMove[i][j] != boardValuesAfterMove[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void gameOver() {
        if (highScore == score) {
            ScoreWriter sw = new ScoreWriter();
            sw.saveHighScore(score, "score.txt");
        }
        gui.gameOverDialog(score);
        restart();
    }

    public Board getBoard() {
        return this.board;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getMoves() {
        return moves;
    }

    /**
     * Helper method for checkLoss() which checks if there is a piece with the
     * same value next to given location in cardinal directions.
     *
     * @param y Piece's Y coordinate
     * @param x Piece's X coordinate
     * @param value Piece's value
     * @return true if there is a possible move, false otherwise
     */
    private boolean checkAdjacentForPossibleMove(int y, int x, int value) {
        Piece[][] board1 = board.getBoard();
        if (x > 0 && board1[y][x - 1].getValue() == value) {
            return true;
        }
        if (x < board.getWidth() - 1 && board1[y][x + 1].getValue() == value) {
            return true;
        }
        if (y > 0 && board1[y - 1][x].getValue() == value) {
            return true;
        }
        if (y < board.getHeight() - 1 && board1[y + 1][x].getValue() == value) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the game is lost by checking if there are any possible moves
     * left.
     *
     * @return false if there are possible moves, true if not (lost game)
     */
    public boolean checkLoss() {
        Piece[][] board1 = board.getBoard();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (board1[y][x].getValue() == 0 || checkAdjacentForPossibleMove(y, x, board1[y][x].getValue())) {
                    return false;
                }
            }
        }
        return true;
    }
}
