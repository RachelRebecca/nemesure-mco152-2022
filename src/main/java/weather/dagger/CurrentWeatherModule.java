package weather.dagger;

import dagger.Module;
import dagger.Provides;
import weather.json.OpenWeatherMapService;
import weather.json.OpenWeatherMapServiceFactory;

@Module
public class CurrentWeatherModule
{
    @Provides
    public OpenWeatherMapService providesOpenWeatherMapService(OpenWeatherMapServiceFactory factory)
    {
        return factory.getInstance();
    }
}
