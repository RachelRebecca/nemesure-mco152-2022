package weather.json;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;

public class OpenWeatherMapServiceFactory
// factory has one responsibility: to make things,
// in this case OpenWeatherMapService,
// so code doesn't need GetCurrentWeather wrapper
{
    @Inject
    public OpenWeatherMapServiceFactory()
    {
        // dagger needs a blank constructor annotated with inject to function
    }
    public OpenWeatherMapService getInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(OpenWeatherMapService.class);
        // retrofit implements the interface for us
    }

}
