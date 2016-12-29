package fi.lrkr._2048.game;

import java.util.Scanner;

public class Logic {

    private Board board;
    private Scanner reader;
    private int moves;
    private int score;

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
        board.printBoard(moves, score);
        gameLoop();
    }

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
            board.printBoard(moves, score);
        }
    }

    //to be used later with GUI
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
        if (checkLoss()) {
            gameOver();
        }
    }

    //katastrofaalista...
    private boolean checkIfMoveHappened(int[][] boardValuesBeforeMove, int[][] boardValuesAfterMove) {
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

    public Board getBoard() {
        return this.board;
    }

}
