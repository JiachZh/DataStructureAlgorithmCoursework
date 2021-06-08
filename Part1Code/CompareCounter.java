public class CompareCounter {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment(){
        this.count++;
    }

    public void reset(){
        this.count = 0;
    }


}
