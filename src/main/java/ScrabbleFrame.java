import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ScrabbleFrame extends JFrame
{

    private boolean highScoreBeaten = false;

    private final ScrabblePresenter presenter;

    // member variables for views:
    private JPanel verticalPanel;
    private JLabel[] tilesLabel;
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
        ScrabbleGame game = new ScrabbleGame(dictionary, letterPool);
        presenter = new ScrabblePresenter(this, game);

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

        tilesLabel = new JLabel[7];
        for (int i = 0; i < tilesLabel.length; i++)
        {
            tilesLabel[i] = new JLabel();
            tilesPanel.add(tilesLabel[i]);
        }

        verticalPanel.add(tilesPanel);

        presenter.fillTiles();
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

        highScoreLabel = new JLabel();
        setHighScore("high score: 0");
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
        String word = inputField.getText();
        presenter.playWord(word);
    }

    /**
     * Start new game when button is clicked,
     * reset all fields except high score
     * Display JOptionPane if user beat high score
     * @param e - ActionEvent
     */


    public void onNewGameClicked(ActionEvent e)
    {
        presenter.newGame();

        if (highScoreBeaten)
        {
            JOptionPane.showMessageDialog(null, "You beat the high score!");
        }
        highScoreBeaten = false;
    }

    public void setScore(String strScore)
    {
        this.scoreLabel.setText(strScore);
    }

    public void setHighScore(String strHighScore)
    {
        this.highScoreLabel.setText(strHighScore);
    }

    public void setHighScoreBeaten(boolean beaten)
    {
        highScoreBeaten = beaten;
    }

    public void setTiles(ArrayList<Character> tiles)
    {
        for (int i = 0; i < tiles.size(); i++)
        {
            tilesLabel[i].setText(tiles.get(i).toString());
        }
    }

    public void setErrorMessage(String errorMessage)
    {
        outputLabel.setText(errorMessage);
    }


    public static void main(String[] args)
    {
        JFrame jFrame = new ScrabbleFrame();

        jFrame.setVisible(true);
    }

}
