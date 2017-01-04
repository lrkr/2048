package fi.lrkr.twos.game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PieceTest {

    public PieceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createNormalPiece() {
        Piece p = new Piece();
        assertEquals(2, p.getValue());
        assertEquals(1, p.getExp());
    }

    @Test
    public void testDoubleValueNormal() {
        Piece p = new Piece();
        p.doubleValue();
        assertEquals(4, p.getValue());
        assertEquals(2, p.getExp());
    }

    @Test
    public void testDoubleValueNormal2() {
        Piece p = new Piece(2);
        p.doubleValue();
        assertEquals(4, p.getValue());
        assertEquals(2, p.getExp());
    }

    @Test
    public void testDoubleValue0() {
        Piece p = new Piece(0);
        p.doubleValue();
        assertEquals(0, p.getValue());
        assertEquals(1, p.getExp());
    }

    @Test
    public void testDoubleValueBig() {
        Piece p = new Piece(2048);
        p.doubleValue();
        assertEquals(4096, p.getValue());
    }

    @Test
    public void testDoubleValueMulti() {
        Piece p = new Piece();
        for (int i = 0; i < 5; i++) {
            p.doubleValue();
        }
        assertEquals(64, p.getValue());
        assertEquals(6, p.getExp());
    }

    @Test
    public void testDoubleValueAbnormal() {
        Piece p = new Piece(3);
        p.doubleValue();
        assertEquals(6, p.getValue());
        assertEquals(2, p.getExp());
    }

}
