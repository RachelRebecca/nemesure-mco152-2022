import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrabbleFrame extends JFrame
{
    private int score = 0;
    private int highScore = 0;
    private boolean highScoreBeaten = false;
    private ScrabbleGame game;

    // member variables for views:
    private JPanel verticalPanel;
    private JLabel[] tiles;
    private JTextField inputField;
    private JLabel scoreLabel;
    private JButton submitButton;
    private JLabel outputLabel;
    private JLabel highScoreLabel;

    public ScrabbleFrame()
    {
        setForm();

        ScrabbleDictionary dictionary = new ScrabbleDictionary();
        ScrabbleLetterPool letterPool = new ScrabbleLetterPool();
        game = new ScrabbleGame(dictionary, letterPool);

        setVerticalPanel();
    }

    private void setVerticalPanel()
    {
        verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        add(verticalPanel);

        addTilesPanel();

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(120, 60));
        verticalPanel.add(inputField);

        addScoreButtonOutputPanel();

        addHighScoreNewGamePanel();

    }

    private void addHighScoreNewGamePanel()
    {
        JPanel hsngPanel = new JPanel();
        hsngPanel.setLayout(new FlowLayout());

        highScoreLabel = new JLabel("high score: " + highScore);
        hsngPanel.add(highScoreLabel);

        JButton startNewGame = new JButton("Start New Game");
        startNewGame.addActionListener(this::onNewGameClicked);
        hsngPanel.add(startNewGame);

        verticalPanel.add(hsngPanel);
    }

    private void setForm()
    {
        setTitle("Touro University Scrabble");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
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

    private void addScoreButtonOutputPanel()
    {
        JPanel sboPanel = new JPanel();
        sboPanel.setLayout(new FlowLayout());
        scoreLabel = new JLabel("score: 0");
        sboPanel.add(scoreLabel);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::onSubmitClicked);

        // event is an ActionEvent var,
        // lambda replaces new ActionListener() {@Override .....} .....
        // event -> System.out.println("Clicked")
        // lambda is a method reference
        sboPanel.add(submitButton);

        outputLabel = new JLabel("Output");
        sboPanel.add(outputLabel);

        verticalPanel.add(sboPanel);
    }

    public void onSubmitClicked(ActionEvent event)
    {
        String word = inputField.getText().toUpperCase();
        if (game.playWord(word))
        {
            score += word.length();
            scoreLabel.setText("score: " + (score));
            if (score > highScore)
            {
                highScore = score;
                highScoreLabel.setText("high score: " + highScore);
                highScoreBeaten = true;
            }
            outputLabel.setText("Great job!");
            for (int i = 0; i < 7; i++)
            {
                tiles[i].setText(game.getTiles().get(i).toString());
            }
        }
        else
        {
            String output;
            if (game.getErr_msg().equals("none"))
            {
                output = "Something went wrong";
            } else
            {
                output = game.getErr_msg();
            }
            outputLabel.setText("Attempt failed: " + output);
        }
    }

    public void onNewGameClicked(ActionEvent e)
    {
        score = 0;
        scoreLabel.setText("score: " + 0);
        game = new ScrabbleGame(new ScrabbleDictionary(), new ScrabbleLetterPool());
        outputLabel.setText("New Game");
        for (int i = 0; i < 7; i++)
        {
            tiles[i].setText(game.getTiles().get(i).toString());
        }
        if (highScoreBeaten)
        {
            JOptionPane.showMessageDialog(null, "You beat the high score!");
        }
        highScoreBeaten = false;
    }

    public static void main(String[] args)
    {
        JFrame jFrame = new ScrabbleFrame();

        jFrame.setVisible(true);
    }
}
