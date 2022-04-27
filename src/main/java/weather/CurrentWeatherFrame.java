package weather;

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
            CurrentWeather currentWeather = getCurrentWeather.getCurrentWeather(zipcode.getText());
            double tempKelvin = currentWeather.getTemperature();
            double tempFahr = convertToFahrenheit(tempKelvin);
            String fahrenheit = String.format("%s %.2f %n", "Current Temperature: ", tempFahr);

            temperature.setText(fahrenheit);
        }
        catch (IOException e)
        {
           e.printStackTrace();
        }
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
