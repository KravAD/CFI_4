package Analizar;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class FileAnalyzer {
    public void analyze(String fileName) {
        if (fileName != null) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(fileName));
                System.out.println("The file " + fileName + " has " + lines.size() + " lines.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
