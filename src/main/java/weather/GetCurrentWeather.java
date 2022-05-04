package weather;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import weather.json.CurrentWeather;
import weather.json.OpenWeatherMapService;


import java.io.*;

public class GetCurrentWeather
{
    private OpenWeatherMapService service;
    public GetCurrentWeather()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(OpenWeatherMapService.class); // retrofit implements the interface for us

    }
    /**
     *
     * @return the current temperature in Fahrenheit
     */
    public Observable<CurrentWeather> getCurrentWeather(String zip) throws IOException
    {
        Observable<CurrentWeather> observable = service.getCurrentWeather(zip);
        return observable;
    }
}
