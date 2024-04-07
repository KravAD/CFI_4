package Analizar;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileAnalyzer {
    public void analyze(String fileName) {
        if (fileName != null) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(fileName));
                long wordCount = lines.stream()
                        .flatMap(line -> Arrays.stream(line.split("\\s+")))
                        .count();
                System.out.println("The file " + fileName + " has " + wordCount + " words.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void searchWord(String fileName, String word) {
        if (fileName != null && word != null) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(fileName));
                long wordCount = lines.stream()
                        .flatMap(line -> Arrays.stream(line.split("\\s+")))
                        .filter(w -> w.equals(word))
                        .count();
                System.out.println("The word '" + word + "' appears " + wordCount + " times in the file " + fileName + ".");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}