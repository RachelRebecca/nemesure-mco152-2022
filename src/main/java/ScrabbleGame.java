import java.util.ArrayList;
import java.util.HashMap;

public class ScrabbleGame
{
    final ArrayList<String> playedWords = new ArrayList<>(); //package private
    private final ArrayList<Character> tiles = new ArrayList<>();
    private final ScrabbleDictionary dictionary;
    private final ScrabbleLetterPool letterPool;

    final static String NOT_IN_DICTIONARY = "Word played is not in dictionary";
    final static String NOT_IN_TILES = "Letters are not in your tiles";
    final static String DEFAULT_ERROR = "Something went wrong";
    private String errorMessage = DEFAULT_ERROR;

    public ScrabbleGame(ScrabbleDictionary dictionary, ScrabbleLetterPool letterPool)
    {
        // since game is dependent on these classes, don't instantiate them, pass them through constructor
        this.dictionary = dictionary;
        this.letterPool = letterPool;

        set7NewTiles();

    }

    public void set7NewTiles()
    {
        // give the player 7 random files
        tiles.clear();
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
     * @return whether word exists and is valid
     */
    public boolean playWord(String word)
    {
        if (dictionary.isWord(word))
        {
            char[] letters = word.toCharArray();
            if (!isValidLetters(letters))
            {
                errorMessage = NOT_IN_TILES;
                return false;
            }

            replaceTiles(letters);
            playedWords.add(word);
            return true;
        }

        errorMessage = NOT_IN_DICTIONARY;
        return false;
    }

    /**
     * check that the word contains only letters found in tiles
     * and that there are no non-allowed repeats
     * @param letters - the letters in the played word
     * @return boolean - valid, or not valid
     */
    private boolean isValidLetters(char[] letters)
    {
        HashMap<Character, Integer> numTimesLettersAppearInWord = new HashMap<>();
        HashMap<Character, Integer> numTimesLettersAppearInTiles = new HashMap<>();

        setNumTimesLettersAppearInTiles(numTimesLettersAppearInTiles);
        setNumTimesLettersAppearInWord(letters, numTimesLettersAppearInWord);

        for (Character c : numTimesLettersAppearInWord.keySet())
        {
            if (numTimesLettersAppearInWord.containsKey(c) && numTimesLettersAppearInTiles.containsKey(c))
            {
                if (numTimesLettersAppearInWord.get(c) > numTimesLettersAppearInTiles.get(c))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }

        return true;

    }

    /**
     * Set number of times each letter appears in the word, using HashMap
     * @param letters - the letters in the played word
     * @param numTimesLettersAppearInWord - the HashMap for letter-frequency comparisons
     */
    private void setNumTimesLettersAppearInWord(char[] letters, HashMap<Character, Integer> numTimesLettersAppearInWord)
    {
        for (Character c : letters)
        {
            if (numTimesLettersAppearInWord.containsKey(c))
            {
                int currNum = numTimesLettersAppearInWord.get(c);
                numTimesLettersAppearInWord.put(c, currNum + 1);
            } else
            {
                numTimesLettersAppearInWord.put(c, 1);
            }
        }
    }

    /**
     * Set number of times each letters appears in the tiles, using HashMap
     * @param numTimesLettersAppearInTiles - HashMap for tile letter - frequency comparison
     */
    private void setNumTimesLettersAppearInTiles(HashMap<Character, Integer> numTimesLettersAppearInTiles)
    {
        for (Character tile : tiles)
        {
            if (numTimesLettersAppearInTiles.containsKey(tile))
            {
                int currNum = numTimesLettersAppearInTiles.get(tile);
                numTimesLettersAppearInTiles.put(tile, currNum + 1);
            } else
            {
                numTimesLettersAppearInTiles.put(tile, 1);
            }
        }
    }

    /**
     * Replace each used tile with a new, randomly selected tile
     * @param letters - the letters in the played word
     */
    private void replaceTiles(char[] letters)
    {
        for (Character c : letters)
        {
            tiles.remove(c); // remove the played tile
            tiles.add(letterPool.takeRandomLetterFromPool()); // take a new letter from available letters
            letterPool.insertLetter(c); // add new letter

            // NOTE: Since only one player plays, and he can at most play 7 tiles,
            // there will never be more than original size of letter pool - 14,
            // so do not have to worry about pulling characters from an empty letter pool
        }
    }

    public ArrayList<String> getPlayedWords()
    {
        return playedWords;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
}
