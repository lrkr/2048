package fi.lrkr.twos.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreReader {

    public int readHighScore(String file) {
        try {
            Scanner scanner = new Scanner(new File(file));
            int score = scanner.nextInt();
            scanner.close();
            return score;
        } catch (FileNotFoundException ex) {
            return 0;
        }
    }
}
