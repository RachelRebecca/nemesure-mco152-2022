import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrabbleFrame extends JFrame
{
    private int score = 0;
    private ScrabbleGame game;

    // member variables for views:
    private final JPanel verticalPanel;
    private JLabel[] tiles;
    private final JTextField inputField;
    private final JLabel scoreLabel;
    private final JButton submitButton;
    private final JLabel outputLabel;

    public ScrabbleFrame()
    {
        setTitle("Touro University Scrabble");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        ScrabbleDictionary dictionary = new ScrabbleDictionary();
        ScrabbleLetterPool letterPool = new ScrabbleLetterPool();
        game = new ScrabbleGame(dictionary, letterPool);

        verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        add(verticalPanel);

        addTilesPanel();

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(120, 60));
        verticalPanel.add(inputField);

        scoreLabel = new JLabel("score: 0");
        verticalPanel.add(scoreLabel);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::onSubmitClicked);

        // event is an ActionEvent var,
        // lambda replaces new ActionListener() {@Override .....} .....
            // event -> System.out.println("Clicked")
        // lambda is a method reference
        verticalPanel.add(submitButton);

        outputLabel = new JLabel("Output");
        verticalPanel.add(outputLabel);
    }

    private void addTilesPanel()
    {
        JPanel tilesPanel = new JPanel();
        tilesPanel.setLayout(new FlowLayout());

        tiles = new JLabel[7];
        for (int i = 0; i < 7; i++)
        {
            tiles[i] = new JLabel(game.getTiles().get(i).toString());
            tilesPanel.add(tiles[i]);
        }

        verticalPanel.add(tilesPanel);
    }

    public void onSubmitClicked(ActionEvent event)
    {
        String word = inputField.getText().toUpperCase();
        if (game.playWord(word))
        {
            score += word.length();
            scoreLabel.setText("score: " + (score));
            outputLabel.setText("Great job!");
            for (int i = 0; i < 7; i++)
            {
                tiles[i].setText(game.getTiles().get(i).toString());
            }
        }
        else
        {
            outputLabel.setText("Attempt failed");
        }
    }

    public static void main(String[] args)
    {
        JFrame jFrame = new ScrabbleFrame();

        jFrame.setVisible(true);
    }
}
