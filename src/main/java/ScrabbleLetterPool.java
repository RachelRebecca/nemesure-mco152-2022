import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ScrabbleLetterPool
{
    private ArrayList<Character> letterPool = new ArrayList<>();
    private final Random RAND = new Random();

    public ScrabbleLetterPool()
    {
        File file = new File("letters.txt");
        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                String letter = scanner.nextLine();
                char[] letters = letter.toCharArray();
                letterPool.add(letters[0]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Character> getLetterPool()
    {
        return letterPool;
    }

    public Character takeRandomLetterFromPool()
    {
        int index = letterPool.get(RAND.nextInt(letterPool.size()));
        Character letter = letterPool.get(index);
        letterPool.remove(letterPool.get(index));
        return letter;
    }

    public void insertLetter(char letter)
    {
        letterPool.add(letter);
    }
}
