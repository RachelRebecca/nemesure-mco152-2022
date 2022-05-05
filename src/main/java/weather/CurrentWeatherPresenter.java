package weather;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import weather.json.CurrentWeather;

public class CurrentWeatherPresenter
{
    private final CurrentWeatherFrame view;
    private final GetCurrentWeather model;
    private Disposable disposable;

    public CurrentWeatherPresenter(CurrentWeatherFrame view, GetCurrentWeather model)
    {
        this.view = view;
        this.model = model;
    }

    public void loadWeatherFromZipcode(String zipcode)
    {
        // UI runs on main thread. getCurrentWeather's execute is a blocking call
        // so can run this on a separate thread so submit button doesn't stay depressed for a while

        // disposable is used to cancel the request
        // observable = observe result
        //Observable<CurrentWeather> observable = getCurrentWeather.getCurrentWeather(zipcode.getText());

        disposable = model.getCurrentWeather(zipcode)
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
        view.setTemperature(fahrenheit);

    }

    public void onError(Throwable throwable)
    {
        throwable.printStackTrace();
        view.showError();
    }

}