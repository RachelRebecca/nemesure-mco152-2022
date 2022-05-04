package weather;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;
import weather.json.CurrentWeather;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GetCurrentWeatherTest
{
    @Test void getCurrentWeather() throws IOException
    {
        // given
        GetCurrentWeather getCurrentWeather = new GetCurrentWeather();

        // when
        Observable<CurrentWeather> observable = getCurrentWeather.getCurrentWeather("10314");
        observable
               .subscribe(this::onNext, this::onError);
    }

    private void onNext(CurrentWeather currentWeather)
    {
        System.out.println("temperature: " + currentWeather.getTemperature() +
                "\nmax: " +  currentWeather.getMaxTemperature() + " min: " + currentWeather.getMinTemperature() +
                "\ndescription: " + currentWeather.getDescription() + " icon: " + currentWeather.getIcon());
        assertTrue(currentWeather.getTemperature() > 0);
        assertTrue(currentWeather.getMaxTemperature() > 0);
        assertTrue(currentWeather.getMinTemperature() > 0);
        assertNotNull(currentWeather.getDescription());
        assertNotNull(currentWeather.getIcon());
    }

    private void onError(Throwable throwable)
    {
        throwable.printStackTrace();
    }
}