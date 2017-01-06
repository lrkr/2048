package fi.lrkr.twos.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class provides the game board's functionality.
 */

public class Board {

    private Piece[][] board;
    private int height;
    private int width;
    private int startNumber;

    /**
     * Constructor for creating Board with default values.
     */
    public Board() {
        this(4, 4, 2);
    }

    /**
     * Constructor for creating Board object.
     * 
     * @param y Board's height
     * @param x Board's width
     * @param startNumber Initial Piece value
     */
    public Board(int y, int x, int startNumber) {
        this.board = new Piece[y][x];
        this.height = y;
        this.width = x;
        this.startNumber = startNumber;
    }
    
    /**
     * Initializes the board with 0 value pieces.
     */
    public void init() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                board[y][x] = new Piece(0);
            }
        }
    }
    
    /**
     * Adds a new Piece to a random empty location on the Board. The added Piece's value might be randomly doubled.
     */
    public void addNew() {
        ArrayList<Location> emptyLocations = new ArrayList<>();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (board[y][x].getValue() == 0) {
                    emptyLocations.add(new Location(y, x));
                }
            }
        }
        if (emptyLocations.isEmpty()) {
            return;
        }
        Location free = emptyLocations.get(ThreadLocalRandom.current().nextInt(0, emptyLocations.size()));
        Piece newPiece = new Piece(startNumber);
        if (ThreadLocalRandom.current().nextBoolean()) {
            newPiece.doubleValue();
        }
        board[free.getY()][free.getX()] = newPiece;
    }
    
    /**
     * Copies Board's Pieces' values to a 2d array.
     * 
     * @return 2d int array of Board's Pieces' values.
     */
    public int[][] getBoardValues() {
        int[][] values = new int[height][width];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                values[y][x] = board[y][x].getValue();
            }
        }
        return values;
    }
    
    /**
     * Moves all the Pieces on the Board to left, combines suitable Pieces and counts score for the move.
     *
     * @return Score of the move.
     */
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

    /**
     * Moves all the Pieces on the Board to right, combines suitable Pieces and counts score for the move.
     *
     * @return Score of the move.
     */
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
    
    /**
     * Moves all the Pieces on the Board to up, combines suitable Pieces and counts score for the move.
     *
     * @return Score of the move.
     */
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
    
    /**
     * Moves all the Pieces on the Board to down, combines suitable Pieces and counts score for the move.
     *
     * @return Score of the move.
     */
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
    
    /**
     * Helper method for moves that gets ArrayDeque of PIeces, goes through it 
     * and combines suitable Pieces (discards one and doubles the value of the 
     * other).
     * 
     * @param pieces ArrayDeque of Pieces
     * @return Score of the line's pieces' combinations
     */
    private int handleLine(ArrayDeque<Piece> pieces) {
        int score = 0;
        ArrayDeque<Piece> helperClone = pieces.clone();
        pieces.clear();
        while (!helperClone.isEmpty()) {
            Piece piece = helperClone.removeFirst();
            if (!helperClone.isEmpty() && helperClone.peekFirst().getValue() == piece.getValue()) {
                helperClone.removeFirst();
                piece.doubleValue();
                score += piece.getValue();
            }
            pieces.add(piece);
        }
        return score;
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