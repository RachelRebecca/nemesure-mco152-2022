package weather.json;

public class CurrentWeather
{
    Main main; // the variable name (main lowercase m) here must match variable in json file object

    public double getTemperature()
    {
        return main.temp;
    }
}
