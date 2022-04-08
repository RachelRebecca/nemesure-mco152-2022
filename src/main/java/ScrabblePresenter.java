import java.util.ArrayList;

public class ScrabblePresenter
{

    private final ScrabbleFrame view;
    private final ScrabbleGame model;
    private int score;
    private int highScore;

    public ScrabblePresenter(ScrabbleFrame view, ScrabbleGame model)
    {
        this.view = view;
        this.model = model;
    }

    public void playWord(String word)
    {
        word = word.toUpperCase();
        if (model.playWord(word))
        {
            score += word.length();
            view.setScore("score: " + (score));
            if (score > highScore)
            {
                highScore = score;
                view.setHighScore("high score: " + highScore);
                view.setHighScoreBeaten(true);
            }
            view.setErrorMessage("Great job!");
            view.setTiles(model.getTiles());
        }
        else
        {
            view.setErrorMessage("<html>" + "Attempt failed: " + "<br/>" + model.getErrorMessage() + "</html>");
        }
    }

    public void fillTiles()
    {
        ArrayList<Character> tiles = model.getTiles();
        view.setTiles(tiles);
    }

    public void newGame()
    {
        score = 0;
        view.setScore("score: " + 0);
        view.setErrorMessage("New Game");
        model.set7NewTiles();
        fillTiles();
    }
}
