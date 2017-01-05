package fi.lrkr.twos.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreWriter {
        
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
