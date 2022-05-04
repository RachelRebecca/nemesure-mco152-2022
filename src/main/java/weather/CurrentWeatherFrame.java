package weather;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import weather.json.CurrentWeather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * A JTextField for the zipcode
 * A JButton to Submit
 * A JLabel for temperature in Fahrenheit
 */
public class CurrentWeatherFrame extends JFrame
{
    private GetCurrentWeather getCurrentWeather;
    private JTextField zipcode;
    private JButton submitButton;
    private JLabel temperature;

    private JPanel verticalPanel;


    public CurrentWeatherFrame()
    {
        setForm();

        setVerticalPanel();

        setInitialValues();
    }

    /**
     * Set the default settings of the Form
     */
    private void setForm()
    {
        setTitle("Current Weather");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
    }

    private void setVerticalPanel()
    {
        verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        add(verticalPanel);
    }

    private void setInitialValues()
    {
        getCurrentWeather = new GetCurrentWeather();

        zipcode = new JTextField();
        zipcode.setText("zipcode goes here");
        verticalPanel.add(zipcode);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::onSubmitClicked);
        verticalPanel.add(submitButton);

        temperature = new JLabel();
        temperature.setText("Current temperature goes here");
        verticalPanel.add(temperature);
    }

    private void onSubmitClicked(ActionEvent actionEvent)
    {
       try
       {
            // UI runs on main thread. getCurrentWeather's execute is a blocking call
            // so can run this on a separate thread so submit button doesn't stay depressed for a while

            Observable<CurrentWeather> observable = getCurrentWeather.getCurrentWeather(zipcode.getText());
            Disposable disposable = observable
                    .subscribeOn(Schedulers.io()) // do this request in the background
                    .observeOn(Schedulers.newThread())   // run onNext in a new thread
                    .subscribe(this::onNext, this::onError);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onNext(CurrentWeather currentWeather)
    {
        double kelvin = currentWeather.getTemperature();
        temperature.setText("Current temperature: " + kelvin);

    }

    public void onError(Throwable throwable)
    {
        throwable.printStackTrace();
    }

    private double convertToFahrenheit(double tempInKelvin)
    {
        return (1.8*(tempInKelvin-273) + 32);
    }


    public static void main(String[] args)
    {
        JFrame jFrame = new CurrentWeatherFrame();

        jFrame.setVisible(true);
    }

}
