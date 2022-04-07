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
        return main.tempMax;
    }

    public double getMinTemperature()
    {
        return main.tempMin;
    }

    public String getDescription()
    {
        return weather[0].description;
    }

    public String getIcon()
    {
        return "http://openweathermap.org/img/wn" + weather[0].icon +"@2x.png";
    }
}
