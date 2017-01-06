package fi.lrkr.twos.game;

import fi.lrkr.twos.file.ScoreReader;
import fi.lrkr.twos.gui.Gui;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogicTest {

    private Logic l;
    private Board b;

    public LogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.l = new Logic();
        this.b = l.getBoard();
        b.init();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCheckIfMoveHappenedRight() {
        Piece[][] board = b.getBoard();
        board[0][0] = new Piece();
        board[3][3] = new Piece();
        int[][] preMove = b.getBoardValues();
        b.moveRight();
        assertTrue(l.checkIfMoveHappened(preMove, b.getBoardValues()));
    }

    @Test
    public void testCheckIfMoveHappenedRight2() {
        Piece[][] board = b.getBoard();
        board[0][3] = new Piece();
        board[3][3] = new Piece();
        int[][] preMove = b.getBoardValues();
        b.moveRight();
        assertFalse(l.checkIfMoveHappened(preMove, b.getBoardValues()));
    }

    @Test
    public void testCheckIfMoveHappenedLeft1() {
        Piece[][] board = b.getBoard();
        board[0][1] = new Piece();
        board[2][0] = new Piece();
        int[][] preMove = b.getBoardValues();
        b.moveLeft();
        assertTrue(l.checkIfMoveHappened(preMove, b.getBoardValues()));
    }

    @Test
    public void testCheckIfMoveHappenedLeft2() {
        Piece[][] board = b.getBoard();
        board[0][0] = new Piece();
        board[2][0] = new Piece();
        int[][] preMove = b.getBoardValues();
        b.moveLeft();
        assertFalse(l.checkIfMoveHappened(preMove, b.getBoardValues()));
    }

    @Test
    public void testLoss() {
        assertEquals(false, l.checkLoss());
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
        assertEquals(true, l.checkLoss());
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
        assertEquals(false, l.checkLoss());
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
        assertEquals(false, l.checkLoss());
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
        assertEquals(false, l.checkLoss());
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
        assertEquals(false, l.checkLoss());
    }

    @Test
    public void testLossAdjacentNoEmpty() {
        Piece[][] board = b.getBoard();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                board[y][x] = new Piece(2);
            }
        }
        assertEquals(false, l.checkLoss());
    }

    @Test
    public void startTest() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece();
        l.start();
        assertEquals(l.getHighScore(), new ScoreReader().readHighScore("score.txt"));
        int counter = 0;
        board = b.getBoard();
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
        assertEquals(0, l.getScore());
        assertEquals(0, l.getMoves());
    }

    @Test
    public void restartTest() {
        Piece[][] board = b.getBoard();
        board[1][1] = new Piece();
        l.restart();
        int counter = 0;
        board = b.getBoard();
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
        assertEquals(0, l.getScore());
        assertEquals(0, l.getMoves());
    }

    @Test
    public void testExecuteCommandD() {
        Gui gui = new Gui(l);
        l.setGui(gui);
        gui.run();
        Piece[][] board = b.getBoard();
        board[0][0] = new Piece();
        board[1][0] = new Piece();
        l.executeCommand('D');
        assertEquals(4, l.getScore());
        assertEquals(4, board[3][0].getValue());        
    }

    @Test
    public void testExecuteCommandR() {
        Piece[][] board = b.getBoard();
        board[0][0] = new Piece();
        board[1][0] = new Piece();
        Gui gui = new Gui(l);
        l.setGui(gui);
        gui.run();
        l.executeCommand('R');
        assertEquals(0, l.getScore());
        assertEquals(2, board[0][3].getValue());
        assertEquals(2, board[1][3].getValue());        
    }

    @Test
    public void testExecuteCommandU() {
        Piece[][] board = b.getBoard();
        board[0][0] = new Piece();
        board[1][0] = new Piece();
        Gui gui = new Gui(l);
        l.setGui(gui);
        gui.run();
        l.executeCommand('U');
        assertEquals(4, l.getScore());
        assertEquals(4, board[0][0].getValue());        
    }

    @Test
    public void testExecuteCommandL() {
        Piece[][] board = b.getBoard();
        board[0][0] = new Piece();
        board[1][0] = new Piece();
        Gui gui = new Gui(l);
        l.setGui(gui);
        gui.run();
        l.executeCommand('L');
        assertEquals(0, l.getScore());
        assertEquals(2, board[0][0].getValue());
        assertEquals(2, board[1][0].getValue());        
    }
}
