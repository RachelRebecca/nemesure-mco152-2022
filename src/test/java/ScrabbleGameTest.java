import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbleGameTest
{
    // these fields reset themselves for each test
    ScrabbleDictionary dictionary = Mockito.mock(ScrabbleDictionary.class);
    ScrabbleLetterPool letterPool = Mockito.mock(ScrabbleLetterPool.class);

    @Test
    public void playWord_true()
    {
        //given
        Mockito.doReturn(true).when(dictionary).isWord("HELLO");
        // we only want to test ScrabbleGame in ScrabbleGame, NOT ScrabbleDictionary
        Mockito.doReturn('A', 'H', 'E', 'L', 'L', 'O', 'G').when(letterPool).takeRandomLetterFromPool();
        // Mockito methods don't do anything if void,
        // if returns, returns 0, false, or null
        // so doReturn assigns the expected output each time it gets called, after a comma - assigning the return value
        // we can "inspect" mocks

        ScrabbleGame game = new ScrabbleGame(dictionary, letterPool);

        // when

        // then
        assertTrue(game.playWord("HELLO"));
        Mockito.verify(letterPool, Mockito.times(7 + 5)).takeRandomLetterFromPool();
        assertTrue(game.playedWords.contains("HELLO"));
        assertEquals(1, game.playedWords.size());
        assertEquals(7, game.getTiles().size());
    }

    @Test
    public void playWord_false()
    {
        //given
        Mockito.doReturn(true).when(dictionary).isWord("LOGO");
                                // if you put a bug into ScrabbleDictionary, shouldn't fail all these tests
        Mockito.doReturn('A', 'H', 'E', 'L', 'L', 'O', 'G').when(letterPool).takeRandomLetterFromPool();

        ScrabbleGame game = new ScrabbleGame(dictionary, letterPool);

        //when

        //then
        assertFalse(game.playWord("LOGO"));
        assertTrue(game.playedWords.isEmpty());
        Mockito.verify(letterPool, Mockito.times(7)).takeRandomLetterFromPool();
        assertEquals(7, game.getTiles().size());
    }

    @Test
    public void playWord_notInDictionary()
    {
        //given
        Mockito.doReturn(true).when(dictionary).isWord("HELLO");
        Mockito.doReturn('A', 'H', 'E', 'L', 'L', 'O', 'G').when(letterPool).takeRandomLetterFromPool();

        ScrabbleGame game = new ScrabbleGame(dictionary, letterPool);

        // when
        boolean val = game.playWord("HEL");

        //then
        Mockito.verify(dictionary).isWord("HEL");
            // verify that the part of the method that checks dictionary is actually called in code
        assertFalse(val);
        assertTrue(game.playedWords.isEmpty());
    }
}