public class ExampleSingleton {

    //accessCount
    private static int accessCount;

    //create an object of ExampleTest
    private static ExampleSingleton singletonInstance = new ExampleSingleton();

    //Make the constructor private, so that the class will not be instantiated
    private ExampleSingleton(){

        System.out.println("I, the ExampleSingleton, am being created");
    }


    public static ExampleSingleton getInstance(){
        System.out.println("The sole instance of ExampleSingleton is being retrieved");
        accessCount++;
        return singletonInstance;
    }

    public static int accessCount(){
        return accessCount;
    }
}
