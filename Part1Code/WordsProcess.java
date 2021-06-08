import java.util.ArrayList;

public interface WordsProcess {
    ArrayList<String> readWords(String file);
    ArrayList<String > filter(String toFilterFile, String validFile);
    ArrayList<String> sort(String toFilterFile, String validFile, int sortLimit);
}
