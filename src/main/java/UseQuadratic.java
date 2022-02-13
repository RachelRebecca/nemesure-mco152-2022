public class UseQuadratic
{
    public static void main(String[] args)
    {
        //QuadraticEquation equation = new QuadraticEquation(5, 0, 0);
        QuadraticEquation equation = new QuadraticEquation(0, 10, 2);

        System.out.println(equation);
        double[] x = equation.getX();
        System.out.println("x = " + x[0] + " and x = " + x[1]);
    }
}
