package fi.lrkr.twos.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class handles reading saved high scores.
 */
public class ScoreReader {

    /**
     * Method reads an old high score from file. If file is not found or high 
     * score is malformed, high score will be set to 0.
     * 
     * @param file Name of the file in which high score is stored
     * @return Read high score or 0
     */
    public int readHighScore(String file) {
        try {
            Scanner scanner = new Scanner(new File(file));
            int score = scanner.nextInt();
            scanner.close();
            return score;
        } catch (FileNotFoundException | InputMismatchException ex) {
            return 0;
        }
    }
}
