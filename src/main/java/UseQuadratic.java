public class UseQuadratic
{
    public static void main(String[] args)
    {
        QuadraticEquation equation = new QuadraticEquation(8, 19.3, 5);
        System.out.println(equation);
        double[] x = equation.getX();
        System.out.println("x = " + x[0] + " and x = " + x[1]);
    }
}
