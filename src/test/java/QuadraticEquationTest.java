import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuadraticEquationTest
{
    @Test
    public void getXSimple()
    {
        // given
        QuadraticEquation qe = new QuadraticEquation(2, 6, 4);

        // when
        double[] results = qe.getX();

        // then
        assertArrayEquals(new double[]{-1.0, -2.0}, results);

    }

    @Test
    public void getXNaN()
    {
        // given

        QuadraticEquation qe1 = new QuadraticEquation(0, 20, 5);   // a = 0

        QuadraticEquation qe2 = new QuadraticEquation(10,  2, 20); // negative radical

        QuadraticEquation qe3 = new QuadraticEquation(10, 0, 5);   // zero radical

        QuadraticEquation qe4 = new QuadraticEquation(0, 2, 20);   // negative radical and a = 0

        QuadraticEquation qe5 = new QuadraticEquation(0, 0, 0);    // zero radical and a = 0

        double[] expectedValue = new double[]{Double.NaN, Double.NaN};

        // when
        double[] results1 = qe1.getX();
        double[] results2 = qe2.getX();
        double[] results3 = qe3.getX();
        double[] results4 = qe4.getX();
        double[] results5 = qe5.getX();

        // then
        assertArrayEquals(expectedValue, results1);
        assertArrayEquals(expectedValue, results2);
        assertArrayEquals(expectedValue, results3);
        assertArrayEquals(expectedValue, results4);
        assertArrayEquals(expectedValue, results5);
    }


    @Test
    public void testToString()
    {
        // given
        QuadraticEquation qe = new QuadraticEquation(-2, 6.4, 4);

        // when
        String strQE = qe.toString();

        // then
        assertEquals("-2.0x^2 + 6.4x + 4.0 = 0", strQE);
    }
}