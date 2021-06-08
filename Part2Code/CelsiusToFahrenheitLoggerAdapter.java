public class CelsiusToFahrenheitLoggerAdapter extends CelsiusLogger {
    private FahrenheitLogger fahrenheitLogger;

    public CelsiusToFahrenheitLoggerAdapter() {
        this.fahrenheitLogger = new FahrenheitLogger();
    }

    public void setTemperature(double aCelsiusTemperature){
        this.fahrenheitLogger.setTemperature(aCelsiusTemperature * 9/5 + 32);
    }


    public double getTemperature(){
        return (this.fahrenheitLogger.getTemperature() - 32)  * 5/9;
    }

}
