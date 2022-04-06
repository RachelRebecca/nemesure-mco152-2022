package weather.json;

public class CurrentWeather
{
    Main main; // the variable name (main lowercase m) here must match variable in json file object

    Weather[] weather;

    public double getTemperature()
    {
        return main.temp;
    }

    public double getMaxTemperature()
    {
        return main.temp_max;
    }

    public double getMinTemperature()
    {
        return main.temp_min;
    }

    public String getDescription()
    {
        return weather[0].description;
    }

    public String getIcon()
    {
        return weather[0].icon;
    }
}
