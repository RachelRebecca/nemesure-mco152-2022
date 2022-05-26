package weather.dagger;

import dagger.Component;
import weather.CurrentWeatherFrame;

import javax.inject.Singleton;

@Component(modules = { CurrentWeatherModule.class })
@Singleton
public interface CurrentWeatherComponent
{
    // use dagger for the purpose of testing CurrentWeatherFrame
    // this enables it to have presenter as a parameter
    CurrentWeatherFrame getCurrentWeatherFrame();
}
