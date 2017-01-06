package fi.lrkr.twos.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class handles writing high scores to file.
 */
public class ScoreWriter {

    /**
     * Method saves high score to file.
     * 
     * @param score High score to be saved
     * @param file Name of the file where the high score is saved to
     */
    public void saveHighScore(int score, String file) {
        File scoreFile = new File(file);
        try {
            scoreFile.createNewFile();
            FileWriter writer = new FileWriter(scoreFile);
            writer.write(String.valueOf(score));
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            return;
        }        
    }
    
}
