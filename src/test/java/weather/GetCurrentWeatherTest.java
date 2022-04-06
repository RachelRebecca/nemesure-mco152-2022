package weather;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GetCurrentWeatherTest
{
    @Test
    void getTemperature() throws IOException
    {
        // given
        GetCurrentWeather getCurrentWeather = new GetCurrentWeather();

        // when
        double temp = getCurrentWeather.getTemperature();

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
        double temp = getCurrentWeather.getMinTemperature();

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
        double temp = getCurrentWeather.getMaxTemperature();

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
        String description = getCurrentWeather.getDescription();

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
        String icon = getCurrentWeather.getIcon();

        // then
        System.out.println(icon);
        assertNotNull(icon);
        assertTrue(icon.length() > 0);
    }
}