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

    /**
     * Set the default settings of the Form
     */
    private void setForm()
    {
        setTitle("Touro University Scrabble");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
    }

    /**
     * Define vertical panel and fill it with tiles, a text field for users to play words,
     * the score label, submit button, output label, high score label, and start new game button
     */
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

    /**
     * Layer of vertical panel containing the user's current tiles
     */
    private void addTilesPanel()
    {
        JPanel tilesPanel = new JPanel();
        tilesPanel.setLayout(new FlowLayout());

        tiles = new JLabel[game.getTiles().size()];
        for (int i = 0; i < game.getTiles().size(); i++)
        {
            tiles[i] = new JLabel(game.getTiles().get(i).toString());
            tilesPanel.add(tiles[i]);
        }

        verticalPanel.add(tilesPanel);
    }

    /**
     * Layer of vertical panel containing score label, Submit button, and output label
     */
    private void addScoreButtonOutputPanel()
    {
        JPanel scoreSubmitOutputPanel = new JPanel();
        scoreSubmitOutputPanel.setLayout(new FlowLayout());
        scoreLabel = new JLabel("score: 0");
        scoreSubmitOutputPanel.add(scoreLabel);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::onSubmitClicked);

        // event is an ActionEvent var,
        // lambda replaces new ActionListener() {@Override .....} .....
        // event -> System.out.println("Clicked")
        // lambda is a method reference
        scoreSubmitOutputPanel.add(submitButton);

        outputLabel = new JLabel("Output");
        scoreSubmitOutputPanel.add(outputLabel);

        verticalPanel.add(scoreSubmitOutputPanel);
    }

    /**
     * Layer of vertical panel containing the high score label and Start New Game button
     */
    private void addHighScoreNewGamePanel()
    {
        JPanel highScoreNewGamePanel = new JPanel();
        highScoreNewGamePanel.setLayout(new FlowLayout());

        highScoreLabel = new JLabel("high score: " + highScore);
        highScoreNewGamePanel.add(highScoreLabel);

        JButton startNewGame = new JButton("Start New Game");
        startNewGame.addActionListener(this::onNewGameClicked);
        highScoreNewGamePanel.add(startNewGame);

        verticalPanel.add(highScoreNewGamePanel);
    }

    /**
     * When submitting the played word,
     * play the word and then update score and output message
     * @param event - ActionEvent
     */
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
            for (int i = 0; i < game.getTiles().size(); i++)
            {
                tiles[i].setText(game.getTiles().get(i).toString());
            }
        }
        else
        {
            outputLabel.setText("<html>" + "Attempt failed: " + "<br/>" + game.getErrorMessage() + "</html>");
        }
    }

    /**
     * Start new game when button is clicked,
     * reset all fields except high score
     * Display JOptionPane if user beat high score
     * @param e - ActionEvent
     */
    public void onNewGameClicked(ActionEvent e)
    {
        score = 0;
        scoreLabel.setText("score: " + 0);
        game = new ScrabbleGame(new ScrabbleDictionary(), new ScrabbleLetterPool());
        outputLabel.setText("New Game");
        for (int i = 0; i < game.getTiles().size(); i++)
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
