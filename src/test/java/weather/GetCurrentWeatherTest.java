package weather;

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
        CurrentWeather currentWeather = getCurrentWeather.getCurrentWeather("10019");

        // then
        assertTrue(currentWeather.getTemperature() > 0);
        assertTrue(currentWeather.getMaxTemperature() > 0);
        assertTrue(currentWeather.getMinTemperature() > 0);
        assertNotNull(currentWeather.getDescription());
        assertNotNull(currentWeather.getIcon());
    }
    @Test
    void getTemperature() throws IOException
    {
        // given
        GetCurrentWeather getCurrentWeather = new GetCurrentWeather();

        // when
        double temp = getCurrentWeather.getTemperature("10019");

        // then
        System.out.println(temp);
        assertTrue(temp > 0);
    }

    @Test
    void getMinTemperature() throws IOException
    {
        // given
        GetCurrentWeather getCurrentWeather = new GetCurrentWeather();

        // when
        double temp = getCurrentWeather.getMinTemperature("10019");

        // then
        System.out.println(temp);
        assertTrue(temp > 0);
    }

    @Test
    void getMaxTemperature() throws IOException
    {
        // given
        GetCurrentWeather getCurrentWeather = new GetCurrentWeather();

        // when
        double temp = getCurrentWeather.getMaxTemperature("10019");

        // then
        System.out.println(temp);
        assertTrue(temp > 0);
    }

    @Test
    void getDescription() throws IOException
    {
        // given
        GetCurrentWeather getCurrentWeather = new GetCurrentWeather();

        // when
        String description = getCurrentWeather.getDescription("10019");

        // then
        System.out.println(description);
        assertNotNull(description);
        assertTrue(description.length() > 0);
    }

    @Test
    void getIcon() throws IOException
    {
        // given
        GetCurrentWeather getCurrentWeather = new GetCurrentWeather();

        // when
        String icon = getCurrentWeather.getIcon("10019");

        // then
        System.out.println(icon);
        assertNotNull(icon);
        assertTrue(icon.length() > 0);
    }
}