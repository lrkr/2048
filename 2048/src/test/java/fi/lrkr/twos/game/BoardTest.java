package fi.lrkr.twos.game;

import fi.lrkr.twos.game.Piece;
import fi.lrkr.twos.game.Board;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    private Board b;

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.b = new Board();
        this.b.init();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHeightAndWidth() {
        Board b1 = new Board();
        assertEquals(4, b1.getHeight());
        assertEquals(4, b1.getWidth());
    }

    @Test
    public void testInit() {
        Board b1 = new Board();
        b1.init();
        Piece[][] board = b1.getBoard();
        for (int y = 0; y < b1.getHeight(); y++) {
            for (int x = 0; x < b1.getWidth(); x++) {
                assertNotNull(board[y][x]);
            }
        }
    }

    @Test
    public void testInitValues() {
        Board b1 = new Board();
        b1.init();
        Piece[][] board = b1.getBoard();
        for (int y = 0; y < b1.getHeight(); y++) {
            for (int x = 0; x < b1.getWidth(); x++) {
                assertEquals(0, board[y][x].getValue());
            }
        }
    }

    @Test
    public void testAddPiece() {
        b.addNew();
        int counter = 0;
        Piece[][] board = b.getBoard();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                if (board[y][x].getValue() == 0) {
                    assertEquals(0, board[y][x].getValue());
                } else {
                    counter++;
                }
            }
        }
        assertEquals(1, counter);
    }

    @Test
    public void testAddFullBoard() {
        for (int i = 0; i < b.getHeight() * b.getWidth(); i++) {
            b.addNew();
        }
        Piece[][] board = b.getBoard();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                assertThat(board[y][x].getValue(), anyOf(is(2), is(4)));
            }
        }
    }

    @Test
    public void testGetBoardValues() {
        b.addNew();
        b.addNew();
        int[][] copy = b.getBoardValues();
        Piece[][] board = b.getBoard();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                assertEquals(board[y][x].getValue(), copy[y][x]);
            }
        }
    }

    @Test
    public void testMoveLeft() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        b.moveLeft();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                if (x == 0 && y == 1) {
                    assertEquals(2, board[y][x].getValue());
                } else {
                    assertEquals(0, board[y][x].getValue());
                }
            }
        }
    }

    @Test
    public void testMoveRight() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        b.moveRight();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                if (x == b.getWidth() - 1 && y == 1) {
                    assertEquals(2, board[y][x].getValue());
                } else {
                    assertEquals(0, board[y][x].getValue());
                }
            }
        }
    }

    @Test
    public void testMoveDown() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        b.moveDown();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                if (x == 1 && y == b.getHeight() - 1) {
                    assertEquals(2, board[y][x].getValue());
                } else {
                    assertEquals(0, board[y][x].getValue());
                }
            }
        }
    }

    @Test
    public void testMoveUp() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        b.moveUp();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                if (x == 1 && y == 0) {
                    assertEquals(2, board[y][x].getValue());
                } else {
                    assertEquals(0, board[y][x].getValue());
                }
            }
        }
    }

    @Test
    public void testMultiMove() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        b.moveUp();
        b.moveLeft();
        b.moveDown();
        b.moveRight();
        assertEquals(2, board[b.getHeight() - 1][b.getWidth() - 1].getValue());
    }

    @Test
    public void testMergeSimple() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        board[1][2] = new Piece(2);
        b.moveLeft();
        assertEquals(4, board[1][0].getValue());
    }

    @Test
    public void testMergeDoubleOneSameLine() {
        Piece[][] board = b.getBoard();
        board[1][0] = new Piece(2);
        board[1][1] = new Piece(2);
        board[1][2] = new Piece(2);
        board[1][3] = new Piece(2);
        b.moveLeft();
        assertEquals(4, board[1][0].getValue());
        assertEquals(4, board[1][1].getValue());
    }

    @Test
    public void testMergeScoreLeft() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        board[1][2] = new Piece(2);
        assertEquals(4, b.moveLeft());
    }

    @Test
    public void testMergeScoreLeft2() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        board[2][2] = new Piece(2);
        assertEquals(0, b.moveLeft());
    }

    @Test
    public void testMergeScoreRight() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        board[1][2] = new Piece(2);
        assertEquals(4, b.moveRight());
    }
    
    @Test
    public void testMergeScoreRight2() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        board[2][1] = new Piece(2);
        assertEquals(0, b.moveRight());
    }

    @Test
    public void testMergeScoreUp() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        board[2][1] = new Piece(2);
        assertEquals(4, b.moveUp());
    }

    @Test
    public void testMergeScoreUp2() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        board[1][2] = new Piece(2);
        assertEquals(0, b.moveUp());
    }

    @Test
    public void testMergeScoreDown() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        board[2][1] = new Piece(2);
        assertEquals(4, b.moveDown());
    }

    @Test
    public void testMergeScoreDown2() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece(2);
        board[1][2] = new Piece(2);
        assertEquals(0, b.moveDown());
    }

    @Test
    public void testLoss() {
        assertEquals(false, b.checkLoss());
    }

    @Test
    public void testLoss2() {
        Piece[][] board = b.getBoard();
        int i = 1;
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                board[y][x] = new Piece(i);
                i++;
            }
        }
        assertEquals(true, b.checkLoss());
    }

    @Test
    public void testLossAdjacentRight() {
        Piece[][] board = b.getBoard();
        int i = 1;
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                board[y][x] = new Piece(i);
                i++;
            }
        }
        board[0][1] = new Piece(1);
        assertEquals(false, b.checkLoss());
    }

    @Test
    public void testLossAdjacentDown() {
        Piece[][] board = b.getBoard();
        int i = 1;
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                board[y][x] = new Piece(i);
                i++;
            }
        }
        board[1][0] = new Piece(1);
        assertEquals(false, b.checkLoss());
    }

    @Test
    public void testLossAdjacentLeft() {
        Piece[][] board = b.getBoard();
        int i = 1;
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                board[y][x] = new Piece(i);
                i++;
            }
        }
        board[b.getHeight() - 1][b.getWidth() - 2] = new Piece(i - 1);
        assertEquals(false, b.checkLoss());
    }

    @Test
    public void testLossAdjacentUp() {
        Piece[][] board = b.getBoard();
        int i = 1;
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                board[y][x] = new Piece(i);
                i++;
            }
        }
        board[b.getHeight() - 2][b.getWidth() - 1] = new Piece(i - 1);
        assertEquals(false, b.checkLoss());
    } 
        
    @Test
    public void testLossAdjacentNoEmpty() {
        Piece[][] board = b.getBoard();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                board[y][x] = new Piece(2);
            }
        }
        assertEquals(false, b.checkLoss());
    }

}
