import java.util.ArrayList;

public class MergeSortWordsSorter extends WordsFilter {
    private ArrayList<String> originalWords;
    private String toFilterFile;
    private String validFile;
    private static CompareCounter compareCounter = new CompareCounter();

    @Override
    public ArrayList<String> sort(String toFilterFile, String validFile, int sortLimit) {
        if(!toFilterFile.equals(this.toFilterFile) || !validFile.equals(this.validFile)){
            //to avoid read it each time
            this.toFilterFile = toFilterFile;
            this.validFile = validFile;
            this.originalWords = this.filter(toFilterFile, validFile);
        }
        int endIndex = sortLimit > originalWords.size() ? originalWords.size() : sortLimit;

        //construct a sub list, which is a copy from original list
        String[] subList = originalWords.subList(0, endIndex).toArray(new String[0]);

        int start = 0, end = subList.length - 1;

        //merge sort
        mergeSort(subList, start, end);

        System.out.println("total compare: " + compareCounter.getCount());

        ArrayList<String> result = new ArrayList<>();
        for (String s : subList) {
            result.add(s);
        }

        compareCounter.reset();

        return result;
    }

    @Override
    public ArrayList<String> filter(String toFilterFile, String validFile) {
        if(!toFilterFile.equals(this.toFilterFile) || !validFile.equals(this.validFile)){
            //to avoid read it each time
            this.toFilterFile = toFilterFile;
            this.validFile = validFile;
            //call WordsFilter's filter();
            this.originalWords = super.filter(toFilterFile, validFile);
        }
        return this.originalWords;
    }

    public void mergeSort(String[] originalWords, int start, int end){
        if(end > start){
            //only the list has at least 2 elements to sort
            int middle = (start + end) / 2;
            //merge sort left sub list
            mergeSort(originalWords, start, middle);
            //merge sort right sub list
            mergeSort(originalWords, middle + 1, end);
            //merge the two sub list(which is sorted)
            merge(originalWords, start, end, middle);
        }

    }


    /**
     * merge current sub list
     * @param originalWords
     * @param start
     * @param end
     * @param middle
     */
    private void merge(String[] originalWords, int start, int end, int middle){
        int p1 = start, p2 = middle + 1;
        int k = start;
        //construct a temp array
        String[] temp = new String[originalWords.length];
        while (p1 <= middle && p2 <= end){
            if(originalWords[p1].compareTo(originalWords[p2]) <= 0) {
                temp[k++] = originalWords[p1];
                p1++;
                compareCounter.increment();
            }else {
                temp[k++] = originalWords[p2];
                p2++;
                compareCounter.increment();
            }
        }

        //if any of sub list has elements not being merged
        while (p1 <= middle){
            temp[k++] = originalWords[p1];
            p1++;
        }

        while (p2 <= end){
            temp[k++] = originalWords[p2];
            p2++;
        }

        //copy the sorted sub list to the original list, from the temp array
        for(int idx = start; idx <= end; idx++){
            originalWords[idx] = temp[idx];
        }

    }



    public static void main(String[] args) {
        WordsProcess mergeSort = new MergeSortWordsSorter();
        int totalWords = mergeSort.filter("an_article.txt", "google-10000-english-no-swears.txt").size();
        int totalTimes = totalWords % 100 == 0 ? totalWords / 100 : totalWords / 100 + 1;
        for(int times = 1; times <= totalTimes; times++){
            int sortLimit = times * 100;
            sortLimit = sortLimit > totalWords ? totalWords : sortLimit;
            //measure
            long start = System.currentTimeMillis();
            ArrayList<String> result = mergeSort.sort("an_article.txt", "google-10000-english-no-swears.txt", sortLimit);
//            for (String s : result) {
//                System.out.print(s + ", ");
//            }
//            System.out.println();
            System.out.println("Total cost of merging sort "+ sortLimit + " words, cost " +
                    (System.currentTimeMillis() - start) / 1000.0 + " seconds");
        }
    }
}
