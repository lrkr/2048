package fi.lrkr._2048.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    private Piece[][] board;
    private int height;
    private int width;
    private int startNumber;

    public Board() {
        this(4, 4, 2);
    }

    public Board(int y, int x, int startNumber) {
        this.board = new Piece[y][x];
        this.height = y;
        this.width = x;
        this.startNumber = startNumber;
    }

    public void init() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                board[y][x] = new Piece(0);
            }
        }
        /*
        board[1][0] = new Piece(2);
        board[2][0] = new Piece(2);
        board[3][0] = new Piece(4);
        */
    }

    public void addNew() {
        ArrayList<Location> emptyLocations = new ArrayList<>();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (board[y][x].getValue() == 0) {
                    emptyLocations.add(new Location(y, x));
                }
            }
        }
        //räjähtää jos emptyLocs on tyhjä. pitää fixata
        Location free = emptyLocations.get(ThreadLocalRandom.current().nextInt(0, emptyLocations.size()));
        Piece newPiece = new Piece(startNumber);
        if (ThreadLocalRandom.current().nextBoolean()) {
            newPiece.doubleValue();
        }
        board[free.getY()][free.getX()] = newPiece;
    }

    public void printBoard(int moves, int score) {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (board[y][x].getValue() == 0) {
                    System.out.print("        ");
                } else {
                    System.out.format("%8d", board[y][x].getValue());
                }
                if (x != width - 1) {
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
        System.out.println("moves: " + moves);
        System.out.println("score: " + score);
        System.out.println("");
    }

    public int[][] getBoardValues() {
        int[][] values = new int[height][width];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                values[y][x] = board[y][x].getValue();
            }
        }
        return values;
    }

    public int moveLeft() {
        int score = 0;
        for (int y = 0; y < this.height; y++) {
            ArrayDeque<Piece> pieces = new ArrayDeque<>();
            for (int x = 0; x < this.width; x++) {
                if (board[y][x].getValue() != 0) {
                    pieces.add(board[y][x]);
                }
            }
            score += handleLine(pieces);
            for (int x = 0; x < this.width; x++) {
                if (!pieces.isEmpty()) {
                    board[y][x] = pieces.removeFirst();
                } else {
                    board[y][x] = new Piece(0);
                }
            }
        }
        return score;
    }

    public int moveRight() {
        int score = 0;
        for (int y = 0; y < this.height; y++) {
            ArrayDeque<Piece> pieces = new ArrayDeque<>();
            for (int x = width - 1; x >= 0; x--) {
                if (board[y][x].getValue() != 0) {
                    pieces.add(board[y][x]);
                }
            }
            score += handleLine(pieces);
            for (int x = width - 1; x >= 0; x--) {
                if (!pieces.isEmpty()) {
                    board[y][x] = pieces.removeFirst();
                } else {
                    board[y][x] = new Piece(0);
                }
            }
        }
        return score;
    }

    public int moveUp() {
        int score = 0;
        for (int x = 0; x < this.width; x++) {
            ArrayDeque<Piece> pieces = new ArrayDeque<>();
            for (int y = 0; y < this.height; y++) {
                if (board[y][x].getValue() != 0) {
                    pieces.add(board[y][x]);
                }
            }
            score += handleLine(pieces);
            for (int y = 0; y < this.height; y++) {
                if (!pieces.isEmpty()) {
                    board[y][x] = pieces.removeFirst();
                } else {
                    board[y][x] = new Piece(0);
                }
            }
        }
        return score;
    }

    public int moveDown() {
        int score = 0;
        for (int x = 0; x < this.width; x++) {
            ArrayDeque<Piece> pieces = new ArrayDeque<>();
            for (int y = this.height - 1; y >= 0; y--) {
                if (board[y][x].getValue() != 0) {
                    pieces.add(board[y][x]);
                }
            }
            score += handleLine(pieces);
            for (int y = this.height - 1; y >= 0; y--) {
                if (!pieces.isEmpty()) {
                    board[y][x] = pieces.removeFirst();
                } else {
                    board[y][x] = new Piece(0);
                }
            }
        }
        return score;
    }

    private int handleLine(ArrayDeque<Piece> pieces) {
        int score = 0;
        ArrayDeque<Piece> helperClone = pieces.clone();
        pieces.clear();

        while (!helperClone.isEmpty()) {
            Piece piece = helperClone.removeFirst();
            if (!helperClone.isEmpty()) {
                if (helperClone.peekFirst().getValue() == piece.getValue()) {
                    helperClone.removeFirst();
                    piece.doubleValue();
                    score += piece.getValue();
                }
            }
            pieces.add(piece);
        }
        return score;
    }

    public boolean checkLoss() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (board[y][x].getValue() == 0) {
                    return false;
                } else if (checkAdjacentForPossibleMove(y, x, board[y][x].getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkAdjacentForPossibleMove(int y, int x, int value) {
        //System.out.println("checking x = " + x + "    y = " + y);
        if (x > 0) {
            if (board[y][x - 1].getValue() == value) {
                return true;
            }
        }
        if (x < this.width - 1) {
            if (board[y][x + 1].getValue() == value) {
                return true;
            }
        }
        if (y > 0) {
            if (board[y - 1][x].getValue() == value) {
                return true;
            }
        }
        if (y < height - 1) {
            if (board[y + 1][x].getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public Piece[][] getBoard() {
        return this.board;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
