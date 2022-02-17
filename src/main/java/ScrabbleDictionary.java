import java.io.File;
import java.util.Scanner;
import java.util.HashMap;

public class ScrabbleDictionary
{
    private HashMap<String, String> words = new HashMap<>();

    public ScrabbleDictionary()
    {
        File file = new File("dictionary.txt");
        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] entry = line.split("\\s+", 2);
                if (entry.length == 1)
                {
                    words.put(entry[0], "");
                }
                else
                {
                    words.put(entry[0], entry[1]);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isWord(String lookupWord)
    {
       return getDefinition(lookupWord.toUpperCase()) != null;
    }

    public String getDefinition(String lookupWord)
    {
        return words.get(lookupWord.toUpperCase());
    }
}
