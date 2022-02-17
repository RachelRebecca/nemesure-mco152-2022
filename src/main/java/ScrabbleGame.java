import java.util.ArrayList;

public class ScrabbleGame
{
    private final ArrayList<String> playedWords = new ArrayList<>();
    private final ArrayList<Character> tiles = new ArrayList<>();
    private final ScrabbleDictionary dictionary = new ScrabbleDictionary();

    private final ScrabbleLetterPool letterPool = new ScrabbleLetterPool();

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

    public boolean playWord(String word) // still have to break up this method into chunks
    {
        playedWords.add(word);

        if (dictionary.isWord(word))
        {
            char[] letters = word.toCharArray();
            for (char letter : letters)
            {
                if (!tiles.contains(letter))
                {
                    return false;
                }
            }

            replaceTiles(letters);
            return true;
        }

        return false;
    }

    private void replaceTiles(char[] letters)
    {
        for (int i = 0; i < tiles.size(); i++)
        {
            for (Character c : letters)
            {
                if (c == tiles.get(i))
                {
                    letterPool.insertLetter(c);
                    tiles.set(i, letterPool.takeRandomLetterFromPool());
                    break;
                }
            }
        }
    }
}
