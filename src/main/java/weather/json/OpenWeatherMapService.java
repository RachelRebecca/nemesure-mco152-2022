package weather.json;

import io.reactivex.Single;
//Because we're only ever going to return one Observable,
// we're going to return a Single instead
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService
{
    @GET("data/2.5/weather?appid=01d5385d516e0d4911c0a45e021e4f97&units=imperial")
    Single<CurrentWeather> getCurrentWeather(@Query("q") String zipcode);
    //represents that something is going to be returned here
}
