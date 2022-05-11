package weather;

import javax.swing.*;
import java.awt.*;

public class TemperatureSign extends JComponent
{
    private double temperature;

    private static final int CAR_WIDTH = 20;
    private static final int CAR_HEIGHT = 60;

    private double angle = 0;

    public TemperatureSign()
    {
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g1) // My program should NOT call paintComponent
    {
        // NOTE: Graphics g is really Graphics2D which extends Graphics
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g); // clears the part of the screen the component uses
        if (temperature > 50)
            g.setColor(Color.RED);
        else
            g.setColor(Color.BLUE);

        Dimension size = getPreferredSize(); // this is what the component sets

        // getWidth() and getHeight() gets the ACTUAL width and height of the component,
        // safer to use than getPreferredSize(), since some layout managers make things larger if they can be

     /*   g.drawLine(0, 0, size.width, size.height); // In computers, (0, 0) is the UPPER LEFT CORNER
        g.drawLine(0, size.height, size.width, 0); // In computers, (0, 0) is the UPPER LEFT CORNER

        g.drawOval(0, 0, size.width, size.height);
    */
        int width = getWidth();
        int height = getHeight();

         g.drawLine(0, 0, width, height);
         g.drawLine(0, height, width, 0);

         g.drawOval(0, 0, width, height);

        g.setColor(Color.RED);
        //g.fillRect(width / 2 - CAR_WIDTH / 2, height / 2 - CAR_HEIGHT / 2, CAR_WIDTH, CAR_HEIGHT);
        // The better way to make rectangle is translate origin:
        // here, we're going to rotate the origin so this is the focus
        g.translate(width / 2, height / 2);
        g.rotate(Math.toRadians(angle)); // a clockwise rotation
        angle += 0.1;
        g.fillRect(-CAR_WIDTH / 2, -CAR_HEIGHT / 2, CAR_WIDTH, CAR_HEIGHT);

        repaint(); // poor man's animation
    }

    public void setTemperature(double temperature)
    {
        this.temperature = temperature;
        repaint(); // paint() is ONLY called by Operating System, unless you're doing a test
        // you call repaint() to tell OS to call paint()
    }
}
