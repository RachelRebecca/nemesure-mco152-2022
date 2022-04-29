package weather.json;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService
{
    @GET("data/2.5/weather?appid=01d5385d516e0d4911c0a45e021e4f97")
    Observable<CurrentWeather> getCurrentWeather(@Query("q") String zipcode); //represents that something is going to be returned here
}
