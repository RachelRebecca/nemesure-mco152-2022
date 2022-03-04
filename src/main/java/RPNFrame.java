import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RPNFrame extends JFrame
{
    private RPNExpression rpnExpression;

    private JPanel verticalPanel;
    private JTextField expression;
    private JButton btnCalculate;
    private JLabel outputLabel;

    public RPNFrame()
    {
        setTitle("RPN Calculator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        add(verticalPanel);

        expression = new JTextField("Enter an RPN expression");
        expression.setPreferredSize(new Dimension(200, 50));
        verticalPanel.add(expression);

        btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(this::onClickCalculate);
        verticalPanel.add(btnCalculate);

        outputLabel = new JLabel();
        verticalPanel.add(outputLabel);
    }

    private void onClickCalculate(ActionEvent actionEvent)
    {
        rpnExpression = new RPNExpression(expression.getText().toString());
        outputLabel.setText("<html>" + rpnExpression.evaluate().replaceAll("\n", "<br/>") + "</html>");
    }

    public static void main(String[] args)
    {
        JFrame frame = new RPNFrame();

        frame.setVisible(true);
    }
}
