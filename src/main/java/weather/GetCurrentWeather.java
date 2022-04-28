package weather;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
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
                .baseUrl("http://samples.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(OpenWeatherMapService.class); // retrofit implements the interface for us

    }
    /**
     *
     * @return the current temperature in Kelvin
     */
    public Observable<CurrentWeather> getCurrentWeather(String zip) throws IOException
    {
        Observable<CurrentWeather> observable = service.getCurrentWeather(zip);
       // return service
           //     .getCurrentWeather(zip)
                //.blockingFirst(); // this is a blocking call from Observable<CurrentWeather>
                //.execute() //from the original Call<CurrentWeather> in OpenWeatherMapService
                // blocks the main thread, we want this call done asynchronously
                //.body();
        return observable;
    }

    /*
    public double getTemperature(String zip) throws IOException
    {
        //Observable<CurrentWeather> observable = getCurrentWeather(zip);
        //observable
               // .observeOn(Schedulers.io())
               // .subscribe(this::onNext, this::onError);
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

     */

}
