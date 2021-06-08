import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordsFilter extends WordsReader {
    @Override
    public ArrayList<String> sort(String toFilterFile, String validFile, int sortLimit) {
        //No need to implement it in a words filter
        return null;
    }

    @Override
    public ArrayList<String> filter(String toFilterFile, String validFile) {
        List<String> toFilterWords = this.readWords(toFilterFile);
        Set<String> validWords = new HashSet<>(this.readWords(validFile));

        ArrayList<String> filteredWords = new ArrayList<>();
        for (String toFilterWord : toFilterWords) {
            if(validWords.contains(toFilterWord)){
                filteredWords.add(toFilterWord);
            }
        }

        return filteredWords;
    }


    public static void main(String[] args) {
        WordsFilter filter = new WordsFilter();

        //get parameter from command line,like `java WordsFilter an_article.txt google-10000-english-no-swears.txt'
        String toFilterFile = null, validFile = null;
        if(args.length == 2){
            toFilterFile = args[1];
            validFile = args[1];
        }


        //validate user input
        if(toFilterFile == null || toFilterFile.trim().isEmpty()){
            //assign default
            toFilterFile = "an_article.txt";
        }

        //validate user input
        if(validFile == null || validFile.trim().isEmpty()){
            validFile = "google-10000-english-no-swears.txt";
        }

        ArrayList<String> result = filter.filter(toFilterFile, validFile);
        System.out.println("total words after extracting: " + result.size());
        System.out.println(String.format("the valid words in file '%s' are :", toFilterFile));
        int count = 0;
        for (String s : result) {
            //every 300 words show in a line
            if(++count % 300 == 0){
                System.out.println();
            }
            System.out.print(s + " ");
        }

    }
}
