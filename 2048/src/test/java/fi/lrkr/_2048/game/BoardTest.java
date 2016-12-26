package fi.lrkr._2048.game;

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
}
