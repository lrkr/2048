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
                score += handlePiece(board[y][x], pieces);
            }
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
                score += handlePiece(board[y][x], pieces);
            }
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
                score += handlePiece(board[y][x], pieces);
            }
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
                //score += handlePiece(board[y][x], pieces);
                //pitäis laskee score jotenkin handeLinen kanssa
                if (board[y][x].getValue() != 0) {
                    pieces.add(board[y][x]);
                }
            }
            pieces = handleLine(pieces);
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

    //fixaa 0-2-2-4 bugin kait, mutta pitää fixaa scoret
    //tää on vaan downis atm
    private ArrayDeque<Piece> handleLine(ArrayDeque<Piece> pieces) {
        int score = 0;
        ArrayDeque<Piece> added = new ArrayDeque<>();

        while (!pieces.isEmpty()) {
            Piece piece = pieces.removeFirst();
            //enää ei pitäis olla 0 paloja tässä
            while (!pieces.isEmpty() && pieces.peekFirst().getValue() == 0) {
                pieces.removeFirst();
            }
            if (!pieces.isEmpty()) {
                if (pieces.peekFirst().getValue() == piece.getValue()) {
                    pieces.removeFirst();
                    piece.doubleValue();
                    score += piece.getValue();
                }
            }
            added.add(piece);
        }
        return added;
    }

    //palojen yhdistäminen pitää korjata. varmaan rivi/sarake kerrallaan eikä pala kerrallaan...
    private int handlePiece(Piece current, ArrayDeque<Piece> pieces) {
        int score = 0;
        if (current.getValue() != 0) {
            if (!pieces.isEmpty() && pieces.peekLast().getValue() == current.getValue()) {
                //System.out.println("Merged: " + pieces.peekLast().getValue());
                pieces.peekLast().doubleValue();
                score = pieces.peekLast().getValue();
            } else {
                pieces.add(current);
            }
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
