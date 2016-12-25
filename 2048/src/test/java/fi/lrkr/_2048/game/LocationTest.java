package fi.lrkr._2048.game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationTest {

    public LocationTest() {
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
    public void locTest() {
        Location l = new Location(0, 4);
        assertEquals(0, l.getY());
        assertEquals(4, l.getX());
    }
    
    @Test
    public void locTestNeg() {
        Location l = new Location(-4, -9);
        assertEquals(-4, l.getY());
        assertEquals(-9, l.getX());
    }
    
    @Test
    public void locTestBig() {
        Location l = new Location(1267, 952);
        assertEquals(1267, l.getY());
        assertEquals(952, l.getX());
    }
}
