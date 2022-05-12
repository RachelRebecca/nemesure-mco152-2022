package weather;

import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weather.json.CurrentWeather;
import weather.json.OpenWeatherMapService;

import static org.mockito.Mockito.*;

class CurrentWeatherPresenterTest
{
    public CurrentWeatherPresenterTest()
    {
        // another way to get code to run before the tests
    }

    @BeforeAll
    public static void beforeAllTests()
    {
        // the following code makes tests run single-threaded
        RxJavaPlugins.setIoSchedulerHandler((scheduler) -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler((scheduler) -> Schedulers.trampoline());

        // this will run one time before all tests in this class
    }

    @BeforeEach
    public void beforeEachTest()
    {
        // this code runs before each test


    }

    @Test
    void loadWeatherFromZipcode()
    {
        //given
        CurrentWeatherFrame view = mock(CurrentWeatherFrame.class);
        OpenWeatherMapService model = mock(OpenWeatherMapService.class);
        CurrentWeatherPresenter presenter = new CurrentWeatherPresenter(view, model);
        CurrentWeather currentWeather = mock(CurrentWeather.class);

        //GetCurrentWeather returns an Observable<CurrentWeather> object
        // by returning a Mock (presenter.loadWeatherFromZipcode("12345"),
        // you'll get a NullPointerException, because model.getCurrentWeather
        // is going to return null using the Mock

        doReturn(100.0).when(currentWeather).getTemperature();
        doReturn(Single.just(currentWeather)).when(model).getCurrentWeather("00000");
        // Observable.just creates an observable with something already in it

        // when
        presenter.loadWeatherFromZipcode("00000");

        // then
        verify(view).setTemperature(100.0);
    }

    @Test
    void cancel()
    {

    }

    @Test
    void onNext()
    {

    }

    @Test
    void onError()
    {

    }
}