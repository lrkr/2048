package fi.lrkr.twos.file;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreReaderTest {
    
    private ScoreReader sr;
    private File testFile;
    
    public ScoreReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        sr = new ScoreReader();
        testFile = new File("testfile.txt");
        testFile.createNewFile();
    }
    
    @After
    public void tearDown() {
        testFile.delete();
    }
 
    @Test
    public void testReadHighScore() {
        ScoreWriter sw = new ScoreWriter();
        sw.saveHighScore(16, testFile.toString());
        assertEquals(16, sr.readHighScore(testFile.toString()));
    }
    
    @Test
    public void testReadHighScoreNoFile() {
        assertEquals(0, sr.readHighScore("1234.txt"));
    }
}
