package weather;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetCurrentWeather
{
    /**
     *
     * @return the current temperature in Kelvin
     */
    public double getTemperature() throws IOException
    {
        URL url = new URL("https://samples.openweathermap.org/data/2.5/weather?zip=10019,us&appid=b6907d289e10d714a6e88b30761fae22");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        InputStream in = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        return 0;
    }

}
