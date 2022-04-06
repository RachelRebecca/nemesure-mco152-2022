package weather;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
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
    public double getTemperature() throws IOException
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://samples.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);
        CurrentWeather currentWeather = service
                .getCurrentWeather("10019")
                .execute()
                .body();

        return currentWeather.getTemperature();
    }

}
