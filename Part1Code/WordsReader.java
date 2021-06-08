import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class WordsReader implements WordsProcess {
    @Override
    public ArrayList<String> readWords(String file) {
        BufferedReader bufferedReader = null;
        ArrayList<String> words = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(WordsReader.class.getResourceAsStream(file)));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                String[] wordsInLine = line.split(" ");
                for (String s : wordsInLine) {
                    words.add(s.trim());
                }
            }
        }catch (IOException ex){

        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return words;
    }


}
