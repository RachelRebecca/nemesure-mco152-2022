import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ScrabbleGame
{
    private ArrayList<String> playedWords = new ArrayList<>();
    private ArrayList<Character> tiles = new ArrayList<>();
    private ScrabbleDictionary dictionary = new ScrabbleDictionary();

    private final Random RAND = new Random();

    private ScrabbleLetterPool letterPool = new ScrabbleLetterPool();

    //make a test
    public ScrabbleGame()
    {
        // give the player 7 random files
        for (int i = 0; i < 7; i++)
        {
            tiles.add(letterPool.takeRandomLetterFromPool());
        }

    }

    public ArrayList<Character> getTiles()
    {
        return tiles;
    }

    /**
     * if word exists in scrabble dictionary,
     * and the letter exist in the tiles list
     * remove the letters from the list and add new random letters
     */

    public boolean playWord(String word)
    {
        if (dictionary.isWord(word))
        {
            char[] letters = word.toCharArray();
            boolean allLettersInTile = true;
            for (int i = 0; i < letters.length; i++)
            {
                if (!tiles.contains(letters[i]))
                {
                    allLettersInTile = false;
                }
            }

            if (!allLettersInTile)
            {
                return false;
            }
            else
            {
                for (int i = 0; i < tiles.size(); i++)
                {
                    for (Character c : letters)
                    {
                        if (c.equals(tiles.get(i)))
                        {
                            letterPool.insertLetter(c);
                            tiles.set(i, letterPool.takeRandomLetterFromPool());
                            break;
                        }
                    }
                }
                return true;
            }
        }

        return false;
    }
}
