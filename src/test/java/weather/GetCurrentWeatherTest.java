package weather;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;
import weather.json.CurrentWeather;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GetCurrentWeatherTest
{


    @Test
    void getCurrentWeather()
    {
        GetCurrentWeather getCurrentWeather = new GetCurrentWeather();

        CurrentWeather currentWeather = getCurrentWeather.getCurrentWeather("10019")
                .blockingFirst(); // only use blocking calls in tests

        System.out.println("temperature: " + currentWeather.getTemperature() +
                "\nmax: " +  currentWeather.getMaxTemperature() + " min: " + currentWeather.getMinTemperature() +
                "\ndescription: " + currentWeather.getDescription() + " icon: " + currentWeather.getIcon());
        assertTrue(currentWeather.getTemperature() > -459.67); //-459.67 is Absolute Zero Kelvin converted to Fahrenheit
        assertTrue(currentWeather.getMaxTemperature() > -459.67);
        assertTrue(currentWeather.getMinTemperature() > -459.67);
        assertNotNull(currentWeather.getDescription());
        assertNotNull(currentWeather.getIcon());
    }
}