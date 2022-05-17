import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ScrabblePresenterTest
{
    private ScrabbleFrame view = mock(ScrabbleFrame.class);
    private ScrabbleGame model = mock(ScrabbleGame.class);
    private ScrabblePresenter presenter = new ScrabblePresenter(view, model);
    private ArrayList<Character> tiles = new ArrayList<>();

    @Test
    public void fillTiles()
    {
        //given
        doReturn(tiles).when(model).getTiles();

        //when
        presenter.fillTiles();

        //then
        verify(model).getTiles();
        verify(view).setTiles(tiles);
    }

    @Test
    public void playWord_true()
    {
        //given
        doReturn(tiles).when(model).getTiles();
        doReturn(true).when(model).playWord("HELLO");
        //when
        presenter.playWord("HELLO");

        //then
        verify(model).playWord("HELLO");
        verify(view).setScore("score: 5");
        verify(model).getTiles();
        verify(view).setTiles(tiles);
    }

    @Test
    public void playWord_false()
    {
        //given
        doReturn(tiles).when(model).getTiles();
        doReturn(false).when(model).playWord("HELLO");
        doReturn("ERROR").when(model).getErrorMessage();
        //when
        presenter.playWord("HELLO");

        //then
        verify(model).playWord("HELLO");
        String err =  "<html>Attempt failed: <br/>ERROR</html>";
        verify(view).setErrorMessage(err);
        System.out.println(err);
    }
}
