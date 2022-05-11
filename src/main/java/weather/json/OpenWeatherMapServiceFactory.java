package weather.json;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherMapServiceFactory // factory has one responsibility: to make things, in this case OpenWeatherMapService, so code doesn't need GetCurrentWeather wrapper
{
    public OpenWeatherMapService getInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(OpenWeatherMapService.class); // retrofit implements the interface for us
    }

}
