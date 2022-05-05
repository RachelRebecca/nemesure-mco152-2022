package weather;

import javax.swing.*;
import java.awt.*;

public class TemperatureSign extends JComponent
{
    private double temperature;

    public TemperatureSign()
    {
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) // My program should NOT call paintComponent
    {
        super.paintComponent(g); // clears the part of the screen the component uses
        if (temperature > 50)
            g.setColor(Color.RED);
        else
            g.setColor(Color.BLUE);
        g.drawLine(0, 0, 400, 400); // In computers, (0, 0) is the UPPER LEFT CORNER
        g.drawLine(0, 400, 400, 0); // In computers, (0, 0) is the UPPER LEFT CORNER

    }

    public void setTemperature(double temperature)
    {
        this.temperature = temperature;
    }
}
