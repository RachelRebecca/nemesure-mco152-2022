import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbleGameTest
{
    @Test
    public void playWord()
    {
        //given
        ScrabbleGame game = new ScrabbleGame();

        ArrayList<Character> tiles = game.getTiles();
        ArrayList<Character> originalTiles = new ArrayList<>(tiles);

        boolean wordFound = false;

        //when
        System.out.println(tiles);

        for (int i = 0; i < tiles.size(); i++)
        {
            for (int j = 0; j < tiles.size(); j++)
            {
                for (int k = 0; k < tiles.size(); k++)
                {
                    String word = tiles.get(i).toString() + tiles.get(j).toString() + tiles.get(k).toString();
                    System.out.println(word);
                    if (game.playWord(word))
                    {
                        wordFound = true;
                        System.out.println(tiles);
                    }
                }
            }
        }

        //then
        if (wordFound)
        {
            assertFalse(originalTiles.equals(tiles));
        }
        else
        {
            assertTrue(originalTiles.equals(tiles));
        }
    }
}