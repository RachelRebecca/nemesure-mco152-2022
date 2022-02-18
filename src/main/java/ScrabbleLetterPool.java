import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ScrabbleLetterPool
{
    private final ArrayList<Character> letterPool = new ArrayList<>();

    public static final Character[] AVAILABLE_LETTERS = new Character[]
            {
                    'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
                    'B', 'B', 'C', 'C', 'C', 'D', 'D', 'D', 'D',
                    'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E',
                    'E', 'E', 'E', 'F', 'F', 'G', 'G', 'G', 'H',
                    'H', 'I', 'I', 'I', 'I', 'I', 'I', 'I', 'I',
                    'I', 'J', 'K', 'L', 'L', 'L', 'L', 'M', 'M',
                    'N', 'N', 'N', 'N', 'N', 'N', 'O', 'O', 'O',
                    'O', 'O', 'O', 'O', 'O', 'P', 'P', 'Q', 'R',
                    'R', 'R', 'R', 'R', 'R', 'S', 'S', 'S', 'S',
                    'T', 'T', 'T', 'T', 'T', 'T', 'U', 'U', 'U',
                    'U', 'V', 'V', 'W', 'W', 'X', 'Y', 'Y', 'Z'
            };
    private final Random RAND = new Random();

    public ScrabbleLetterPool()
    {
        letterPool.addAll(Arrays.asList(AVAILABLE_LETTERS));

        //Since my letterPool is an ArrayList, not a List,
        // I was having trouble getting the suggested change to work
    }

    public ArrayList<Character> getLetterPool()
    {
        return letterPool;
    }

    public Character takeRandomLetterFromPool()
    {
        int index = RAND.nextInt(letterPool.size());
        Character letter = letterPool.get(index);
        letterPool.remove(letterPool.get(index));
        return letter;
    }

    public void insertLetter(char letter)
    {
        letterPool.add(letter);
    }

    public int size()
    {
        return letterPool.size();
    }
}
