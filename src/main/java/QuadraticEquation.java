/**
 * @author Rachel Nemesure
 * A quadratic equation simulator
 */
public class QuadraticEquation
{
    private final double a;
    private final double b;
    private final double c;

    public QuadraticEquation(double a, double b, double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Get value of A
     * @return first coefficient
     */
    public double getA()
    {
        return a;
    }

    /**
     * Get value of B
     * @return second coefficient
     */
    public double getB()
    {
        return b;
    }

    /**
     * Get value of C
     * @return third coefficient
     */
    public double getC()
    {
        return c;
    }

    /**
     * uses quadratic formula to find x1 and x2,
     * the roots of this quadratic equation
     * quadratic formula = (-b ± sqrt(b^2 - 4ac)) / 2a
     * @return array of doubles representing x1 and x2
     */
    public double[] getX()
    {
        double radical = Math.sqrt(b * b - 4 * a * c);

        if (a == 0 || radical <= 0)
        {
            return new double[]{Double.NaN, Double.NaN};
        }

        else
        {
            double x1 = (-b + radical) / (2 * a);
            double x2 = (-b - radical) / (2 * a);

            return new double[]{x1, x2};
        }
    }

    /**
     * Standard quadratic equation is ax^2 + bx + c = 0
     * @return the equation in standard form
     */
    public String toString()
    {
        return  a + "x^2 + " +
                b + "x + "   +
                c + " = 0";
    }

}
