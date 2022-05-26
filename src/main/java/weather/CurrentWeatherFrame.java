package weather;

import weather.dagger.DaggerCurrentWeatherComponent;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * A JTextField for the zipcode
 * A JButton to Submit
 * A JLabel for temperature in Fahrenheit
 */
@Singleton
public class CurrentWeatherFrame extends JFrame
{
    private TemperatureSign temperatureSign;

    private JTextField zipcode;
    private JButton submitButton;
    private JLabel temperature;

    private JPanel verticalPanel;

    private CurrentWeatherPresenter presenter;

    @Inject
    public CurrentWeatherFrame(CurrentWeatherPresenter presenter)
    {
        setForm();

        setVerticalPanel();

        setInitialValues();

        this.presenter = presenter;

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
        zipcode = new JTextField();
        zipcode.setText("zipcode goes here");
        verticalPanel.add(zipcode);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::onSubmitClicked);
        verticalPanel.add(submitButton);

        temperature = new JLabel();
        temperature.setText("Current temperature goes here");
        verticalPanel.add(temperature);

        temperatureSign = new TemperatureSign();
        verticalPanel.add(temperatureSign);
    }

    public void onSubmitClicked(ActionEvent actionEvent)
    {
        presenter.loadWeatherFromZipcode(zipcode.getText());
    }

    public void setTemperature(double fahrenheit)
    {
        temperature.setText("Current temperature: " + fahrenheit);
        temperatureSign.setTemperature(fahrenheit);
        repaint();
    }

    public void showError()
    {

    }

    public static void main(String[] args)
    {
        CurrentWeatherFrame frame = DaggerCurrentWeatherComponent.create()
                .getCurrentWeatherFrame();
        frame.setVisible(true);
    }

}
