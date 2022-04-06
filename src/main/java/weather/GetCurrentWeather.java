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
    public CurrentWeather getCurrentWeather() throws IOException
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://samples.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

        return service
                .getCurrentWeather("10019")
                .execute()
                .body();
    }

    public double getTemperature() throws IOException
    {
        CurrentWeather currentWeather = getCurrentWeather();
        return currentWeather.getTemperature();
    }

    public double getMinTemperature() throws IOException
    {
        CurrentWeather currentWeather = getCurrentWeather();
        return currentWeather.getMinTemperature();
    }

    public double getMaxTemperature() throws IOException
    {
        CurrentWeather currentWeather = getCurrentWeather();
        return currentWeather.getMaxTemperature();
    }

    public String getDescription() throws IOException
    {
        CurrentWeather currentWeather = getCurrentWeather();
        return currentWeather.getDescription();
    }

    public String getIcon() throws IOException
    {
        CurrentWeather currentWeather = getCurrentWeather();
        return currentWeather.getIcon();
    }

}
