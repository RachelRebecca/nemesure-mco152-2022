package weather;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import weather.json.CurrentWeather;
import weather.json.OpenWeatherMapService;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class CurrentWeatherPresenter
{
    private final Provider<CurrentWeatherFrame> viewProvider;
    private final OpenWeatherMapService model;
    private Disposable disposable;

    @Inject
    public CurrentWeatherPresenter(Provider<CurrentWeatherFrame> view, OpenWeatherMapService model)
    {
        this.viewProvider = view;
        this.model = model;
    }

    public void loadWeatherFromZipcode(String zipcode)
    {
        // UI runs on main thread. getCurrentWeather's execute is a blocking call
        // so can run this on a separate thread so submit button doesn't stay depressed for a while

        // disposable is used to cancel the request
        // observable = observe result
        //Observable<CurrentWeather> observable = getCurrentWeather.getCurrentWeather(zipcode.getText());

        Single<CurrentWeather> observable = model.getCurrentWeather(zipcode);
        disposable = observable
                .subscribeOn(Schedulers.io()) // do this request in the background
                .observeOn(Schedulers.newThread())   // run onNext in a new thread
                .subscribe(this::onNext, this::onError);
    }

    public void cancel() // good for if app goes in the background
    {
        disposable.dispose();
    }

    public void onNext(CurrentWeather currentWeather)
    {
        double fahrenheit = currentWeather.getTemperature();
        viewProvider.get().setTemperature(fahrenheit);
        // .get() creates a NEW frame, so add @Singleton - forces it to only have 1

    }

    public void onError(Throwable throwable)
    {
        throwable.printStackTrace();
        viewProvider.get().showError();
    }

}
