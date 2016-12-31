package fi.lrkr.twos.game;

import fi.lrkr.twos.game.Piece;
import fi.lrkr.twos.game.Board;
import fi.lrkr.twos.game.Logic;
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
        this.b = new Board();
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
    

}
