package weather;

import org.junit.jupiter.api.Test;
import weather.json.CurrentWeather;
import weather.json.OpenWeatherMapService;
import weather.json.OpenWeatherMapServiceFactory;

import static org.junit.jupiter.api.Assertions.*;

class OpenWeatherMapServiceTest
{

    @Test
    void getCurrentWeather()
    {
        OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();
        OpenWeatherMapService service = factory.getInstance();

        CurrentWeather currentWeather = service.getCurrentWeather("10019").blockingGet();

            //.blockingFirst(); // only use blocking calls in tests - block current thread until the first entity comes into the observable, and return that entity

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