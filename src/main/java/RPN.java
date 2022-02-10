import java.util.Stack;

/**
 * RPN EXPLANATION GOES HERE
 */
public class RPN
{
    private Stack<Double> stack;

    public RPN(String expression)
    {
        this.stack = new Stack<>();
    }

    /**
     * Method to evaluate RPN expression
     * @param expression - the RPN expression
     * @return String - either RPN converted to String, or an error message
     */
    public String evaluate(String expression)
    {
        String[] rpnChars = expression.split("\\s+");
        String rpn = "Something went wrong. RPN expression ignored.";
        if (calculateExpressionSuccessfully(rpnChars))
        {
            rpn = getRPN();
        }
        return rpn;
    }

    /**
     * Method to calculate RPN expression and check if its legal
     * @param rpnChars - loops through this String array to check each String one by one
     * to see if it's an integer, an operator, or if something went wrong.
     * @return boolean if everything went smoothly, returns true
     */
    private boolean calculateExpressionSuccessfully(String[] rpnChars)
    {
        final String[] operators = {"+", "-", "*", "X", "x", "/", "÷"};
        boolean success = true;
        for (String rpnChar : rpnChars)
        {
            if (isDouble(rpnChar))
            {
                Double rpnDouble = Double.parseDouble(rpnChar);
                stack.push(rpnDouble);
            }
            else
            {
                String value = getOperator(rpnChar, operators);
                Double[] twoVariables = popTwoVariables();
                Double var1 = twoVariables[0];
                Double var2 = twoVariables[1];
                if (var1 == null || var2 == null || !performOperationSuccessfully(value, var1, var2))
                {
                    success = false;
                }
            }
        }
        return success;
    }

    /**
     * Check if current RPN value is a double
     * @param value (String)
     * @return boolean if it can be parsed as a Double
     */
    private boolean isDouble(String value)
    {
        boolean isDouble = true;
        try
        {
            Double.parseDouble(value);
        }
        catch (Exception e)
        {
            isDouble = false;
        }
        return isDouble;
    }

    /**
     * Check if current RPN value is an operator and which one it is
     * @param value (String) - variable to check if it is an operator
     * @param operators (String[]) - list of legal operators
     * @return String, null if not an operator, or the operator if it is one
     */
    private String getOperator(String value, String[] operators)
    {
        String operator = "null";
        for (String item : operators)
        {
            if (item.equals(value))
            {
                operator = item;
                break;
            }
        }
        return operator;
    }

    /**
     * Method to pop the stack twice and check each time if trying to pop empty stack
     * @return an array of Double objects containing variable 1 and variable 2
     */
    private Double[] popTwoVariables()
    {
        Double var1 = null;
        Double var2 = null;

        if (!stack.isEmpty())
        {
            var2 = stack.pop();
        }

        if (!stack.isEmpty())
        {
            var1 = stack.pop();
        }

        return new Double[]{var1, var2};
    }

    /**
     * Method performs the various operations, and pushes the result back in the stack
     * @param value - the current RPN value trying to check which operator is used
     * @param var1 - the first variable
     * @param var2 - the second variable
     * @return boolean - returns true if nothing went wrong and false if something went wrong
     */
    private boolean performOperationSuccessfully(String value, Double var1, Double var2)
    {
        boolean success = true;
        switch (value)
        {
            case "+":
                double sum = var1 + var2;
                stack.add(sum);
                break;
            case "-":
                double difference = var1 - var2;
                stack.add(difference);
                break;
            case "*":
            case "X":
            case "x":
                double product = var1 * var2;
                stack.add(product);
                break;
            case "/":
            case "÷":
                if (var2 != 0)
                {
                    double quotient = var1 / var2;
                    stack.add(quotient);
                }
                else
                {
                    //divide by zero error
                    success = false;
                }
                break;
            default:
                //something went wrong
                success = false;
        }
        return success;
    }

    /**
     * Method to get the RPN
     * @return String - if all goes well, returns the RPN, otherwise returns an error message
     */
    public String getRPN()
    {
        String rpnMessage;

        if (stack.isEmpty())
        {
            rpnMessage = "Syntax error. RPN expression ignored.";
        }
        else
        {
            double rpn = stack.pop(); //this should be the last remaining member of the stack: the final answer

            boolean extraJunk = !stack.isEmpty();

            rpnMessage = "RPN: " + rpn;
            if (extraJunk)
            {
                rpnMessage += "\nExtra junk ignored";
            }
        }
        return rpnMessage;
    }

}
