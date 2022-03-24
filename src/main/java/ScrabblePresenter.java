import javax.swing.*;
import java.util.ArrayList;

public class ScrabblePresenter
{

    private ScrabbleFrame view;
    private ScrabbleGame model;
    private int score;
    private int highScore;

    public ScrabblePresenter(ScrabbleFrame view, ScrabbleGame model)
    {
        this.view = view;
        this.model = model;
    }

    public void playWord(String word)
    {
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

            //for (int i = 0; i < model.getTiles().size(); i++)
            //{
            //    tiles[i].setText(model.getTiles().get(i).toString());
           // }
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
        //ScrabbleGame game = new ScrabbleGame(new ScrabbleDictionary(), new ScrabbleLetterPool());
        //presenter = new ScrabblePresenter(this, game);
        view.setErrorMessage("New Game");
        model.set7NewTiles();
        fillTiles();
    }
}
