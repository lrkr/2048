package fi.lrkr.twos.file;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreWriterTest {
    
    private File testFile;
    private ScoreWriter sw;
    
    public ScoreWriterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        sw = new ScoreWriter();
        testFile = new File("testfile.txt");
        testFile.createNewFile();
    }
    
    @After
    public void tearDown() {
        testFile.delete();
    }
    
    @Test
    public void testSaveHighScore() {
        sw.saveHighScore(1, testFile.toString());
        ScoreReader sr = new ScoreReader();
        assertEquals(1, sr.readHighScore(testFile.toString()));
    }
    
    @Test
    public void testSaveHighScore2() {
        sw.saveHighScore(123457, testFile.toString());
        ScoreReader sr = new ScoreReader();
        assertEquals(123457, sr.readHighScore(testFile.toString()));
    }
    
}
