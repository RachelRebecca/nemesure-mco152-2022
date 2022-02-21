import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbleDictionaryTest
{
    @Test
    void isWord_HAPPY()
    {
       ScrabbleDictionary sd = new ScrabbleDictionary();

        assertTrue(sd.isWord("HAPPY"));
    }
    @Test
    void isWord_ZOOGEOGRAPHICAL()
    {
        ScrabbleDictionary sd = new ScrabbleDictionary();

        assertTrue(sd.isWord("zoogeographical"));
    }

    @Test
    void isWord_FRAGMENT()
    {
        ScrabbleDictionary sd = new ScrabbleDictionary();

        assertFalse(sd.isWord("happ"));
    }

    @Test
    void isWord_NOT_PRESENT()
    {
        ScrabbleDictionary sd = new ScrabbleDictionary();

        assertFalse(sd.isWord("hapy"));
    }


    @Test
    void getDefinition_HAPPY()
    {
        ScrabbleDictionary sd = new ScrabbleDictionary();

        assertEquals("marked by joy [adj -PIER, -PIEST] : HAPPILY [adv]", sd.getDefinition("HAPPY"));
    }

    @Test
    void getDefinition_ZOOGEOGRAPHICAL()
    {
        ScrabbleDictionary sd = new ScrabbleDictionary();

        assertEquals("", sd.getDefinition("zoogeographical"));
    }
    @Test
    void getDefinition_FRAGMENT()
    {
        ScrabbleDictionary sd = new ScrabbleDictionary();

        assertNull(sd.getDefinition("happ"));
    }
    @Test
    void getDefinition_NOT_PRESENT()
    {
        ScrabbleDictionary sd = new ScrabbleDictionary();

        assertNull(sd.getDefinition("hapy"));
    }
}