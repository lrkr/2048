package fi.lrkr.twos.game;

import fi.lrkr.twos.gui.Gui;
import java.util.Scanner;

/**
 * Class provides the core functionality of the game.
 */
public class Logic {

    private Board board;
    private Scanner reader;
    private int moves;
    private int score;
    private Gui gui;

    public Logic() {
        this(4, 4, 2);
    }

    public Logic(int height, int width, int start) {
        this.board = new Board(height, width, start);
        this.reader = new Scanner(System.in);
        this.moves = 0;
        this.score = 0;
    }

    public void start() {
        board.init();
        board.addNew();
        printBoard();
        gameLoop();
    }
    
    public void startGui() {
        board.init();
        board.addNew();
    }
    
    /**
     * Provides functionality for text based UI.
     */
    public void gameLoop() {
        while (true) {
            if (checkLoss()) {
                gameOver();
            }
            int[][] boardValuesBeforeMove = board.getBoardValues();
            int moveScore = 0;
            char command = reader.next().charAt(0);

            switch (command) {
                case 'w':
                    moveScore = board.moveUp();
                    break;
                case 'a':
                    moveScore = board.moveLeft();
                    break;
                case 's':
                    moveScore = board.moveDown();
                    break;
                case 'd':
                    moveScore = board.moveRight();
                    break;
                default:
                    System.out.println("Invalid command. Use w, a, s or d");
                    continue;
            }
            if (checkIfMoveHappened(boardValuesBeforeMove, board.getBoardValues())) {
                score += moveScore;
                board.addNew();
                moves++;
            }
            printBoard();
        }
    }
    
    /**
     * Executes a game command, checks if move happened and if it resulted in a loss.
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
        }
        if (checkIfMoveHappened(boardValuesBeforeMove, board.getBoardValues())) {
            score += moveScore;
            board.addNew();
            moves++;
        }
        gui.reDraw();
        if (checkLoss()) {
            gameOver();
        }
        
    }

    /**
     * Checks if move happened after it was executed by checking if any Piece moved.
     * 
     * @param boardValuesBeforeMove 2d array of Board's Pieces' values before move
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

    public boolean checkLoss() {
        return board.checkLoss();
    }

    public void gameOver() {
        System.exit(0);
    }
    
    public void printBoard() {
        Piece[][] b = board.getBoard();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (b[y][x].getValue() == 0) {
                    System.out.print("        ");
                } else {
                    System.out.format("%8d", b[y][x].getValue());
                }
                if (x != board.getWidth() - 1) {
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
        System.out.println("moves: " + moves);
        System.out.println("score: " + score);
        System.out.println("");
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    public void setGui(Gui gui) {
        this.gui = gui;
    }

}
