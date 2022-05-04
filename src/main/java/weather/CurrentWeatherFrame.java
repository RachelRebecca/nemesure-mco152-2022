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

    private CurrentWeatherPresenter presenter;

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

        presenter = new CurrentWeatherPresenter(this, new GetCurrentWeather());
    }

    private void onSubmitClicked(ActionEvent actionEvent)
    {
        presenter.loadWeatherFromZipcode(zipcode.getText());
    }

    public void setTemperature(double fahrenheit)
    {
        temperature.setText("Current temperature: " + fahrenheit);
    }

    public void showError()
    {

    }

    public static void main(String[] args)
    {
        JFrame jFrame = new CurrentWeatherFrame();

        jFrame.setVisible(true);
    }

}
