import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbleLetterPoolTest
{

    @Test
    public void scrabbleLetterPool()
    {
        //given
        ScrabbleLetterPool pool = new ScrabbleLetterPool();
        Character[] availableLetters = ScrabbleLetterPool.AVAILABLE_LETTERS;

        //then
        assertEquals(availableLetters.length, pool.getLetterPool().size());

    }

    @Test
    public void takeRandomLetterFromPool()
    {
        //given
        ScrabbleLetterPool pool = new ScrabbleLetterPool();
        Character[] availableLetters = ScrabbleLetterPool.AVAILABLE_LETTERS;
        HashMap<Character, Integer> numLettersAvailable = new HashMap<>();
        populateHashMap(numLettersAvailable);

        //when
        Character letter = pool.takeRandomLetterFromPool();

        int numTimesLetterAppears = 0;
        for (Character character : pool.getLetterPool())
        {
            if (character.equals(letter))
            {
                    numTimesLetterAppears++; // number of times letter appears in the pool
            }
        }

        // then
        if (numLettersAvailable.containsKey(letter))
        {
            assertEquals(numLettersAvailable.get(letter) - 1, numTimesLetterAppears);
        }
        assertEquals(availableLetters.length - 1, pool.getLetterPool().size());
    }

    @Test
    public void insertLetter()
    {
        //given
        ScrabbleLetterPool pool = new ScrabbleLetterPool();

        Character[] availableLetters = ScrabbleLetterPool.AVAILABLE_LETTERS;

        Character letter = pool.takeRandomLetterFromPool();

        // when
        pool.insertLetter(letter);

        //then
        assertEquals(availableLetters.length, pool.getLetterPool().size());
        assertEquals(letter, pool.getLetterPool().get(pool.getLetterPool().size() - 1));

    }

    private void populateHashMap(HashMap<Character, Integer> numLettersAvailable)
    {
        numLettersAvailable.put('A', 9);
        numLettersAvailable.put('B', 2);
        numLettersAvailable.put('C', 2);
        numLettersAvailable.put('D', 4);
        numLettersAvailable.put('E', 12);
        numLettersAvailable.put('F', 2);
        numLettersAvailable.put('G', 3);
        numLettersAvailable.put('H', 2);
        numLettersAvailable.put('I', 9);
        numLettersAvailable.put('J', 1);
        numLettersAvailable.put('K', 1);
        numLettersAvailable.put('L', 4);
        numLettersAvailable.put('M', 2);
        numLettersAvailable.put('N', 6);
        numLettersAvailable.put('O', 8);
        numLettersAvailable.put('P', 2);
        numLettersAvailable.put('Q', 1);
        numLettersAvailable.put('R', 6);
        numLettersAvailable.put('S', 4);
        numLettersAvailable.put('T', 6);
        numLettersAvailable.put('U', 4);
        numLettersAvailable.put('V', 2);
        numLettersAvailable.put('W', 2);
        numLettersAvailable.put('X', 1);
        numLettersAvailable.put('Y', 2);
        numLettersAvailable.put('Z', 1);

    }
}