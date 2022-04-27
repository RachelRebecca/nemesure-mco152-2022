package weather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import weather.json.CurrentWeather;
import weather.json.OpenWeatherMapService;


import java.io.*;

public class GetCurrentWeather
{
    /**
     *
     * @return the current temperature in Kelvin
     */
    public CurrentWeather getCurrentWeather(String zip) throws IOException
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://samples.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class); // retrofit implements the interface for us

        return service
                .getCurrentWeather(zip)
                .execute() //blocks the main thread, we want this call done asynchronously
                .body();
    }

    public double getTemperature(String zip) throws IOException
    {
        CurrentWeather currentWeather = getCurrentWeather(zip);
        return currentWeather.getTemperature();
    }

    public double getMinTemperature(String zip) throws IOException
    {
        CurrentWeather currentWeather = getCurrentWeather(zip);
        return currentWeather.getMinTemperature();
    }

    public double getMaxTemperature(String zip) throws IOException
    {
        CurrentWeather currentWeather = getCurrentWeather(zip);
        return currentWeather.getMaxTemperature();
    }

    public String getDescription(String zip) throws IOException
    {
        CurrentWeather currentWeather = getCurrentWeather(zip);
        return currentWeather.getDescription();
    }

    public String getIcon(String zip) throws IOException
    {
        CurrentWeather currentWeather = getCurrentWeather(zip);
        return currentWeather.getIcon();
    }

}
