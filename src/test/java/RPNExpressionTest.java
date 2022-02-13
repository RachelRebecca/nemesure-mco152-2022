import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RPNExpressionTest
{
    private final String ERR_MSG = "Something went wrong. RPN expression ignored.";

    @Test
    public void evaluateNull()
    {
        //given
        RPNExpression rpn2 = new RPNExpression( null);

        //when
        String result = rpn2.evaluate();

        //then
        assertEquals(ERR_MSG, result);
    }

    @Test
    public void evaluateEmpty()
    {
        // given
        RPNExpression rpn = new RPNExpression("");
        RPNExpression rpn1 = new RPNExpression(" ");

        //when
        String result = rpn.evaluate();
        String result1 = rpn1.evaluate();

        //then
        assertEquals(ERR_MSG, result);
        assertEquals(ERR_MSG, result1);
    }

    @Test
    public void evaluateSimpleAdd()
    {
        //given
        RPNExpression rpnAddition = new RPNExpression("2.5 3 +");

        //when
        String result = rpnAddition.evaluate();

        //then
        assertEquals("RPN: 5.5", result);
    }

    @Test
    public void evaluateSimpleSubtract()
    {
        //given
        RPNExpression rpnSubtract = new RPNExpression("2 3 -");

        //when
        String result = rpnSubtract.evaluate();

        //then
        assertEquals("RPN: -1.0", result);
    }

    @Test
    public void evaluateSimpleMultiply()
    {
        // three multiply characters allowed (*, x, and X)

        //given
        RPNExpression rpnMultiply1 = new RPNExpression("2 3 *");
        RPNExpression rpnMultiply2 = new RPNExpression("2 3 x");
        RPNExpression rpnMultiply3 = new RPNExpression("2 3 X");

        //when
        String result1 = rpnMultiply1.evaluate();
        String result2 = rpnMultiply2.evaluate();
        String result3 = rpnMultiply3.evaluate();

        //then
        assertEquals("RPN: 6.0", result1);
        assertEquals("RPN: 6.0", result2);
        assertEquals("RPN: 6.0", result3);
    }

    @Test
    public void evaluateSimpleDivide()
    {
        // two divide characters allowed (/ and ÷)

        //given
        RPNExpression rpnDivision1 = new RPNExpression("2 3 /");
        RPNExpression rpnDivision2 = new RPNExpression("2 3 ÷");

        //when
        String result1 = rpnDivision1.evaluate();
        String result2 = rpnDivision2.evaluate();

        //then
        assertEquals("RPN: 0.6666666666666666", result1);
        assertEquals("RPN: 0.6666666666666666", result2);
    }

    @Test
    public void evaluateComplex()
    {
        //given
        RPNExpression rpn = new RPNExpression("2 3 4 + * 5 / 7 -");

        //when
        String result = rpn.evaluate();

        //then
        assertEquals("RPN: -4.2", result);
    }

    @Test
    public void evaluateNonNumberAndNonOperator()
    {
        //given
        RPNExpression rpn1 = new RPNExpression("a b c");
        RPNExpression rpn2 = new RPNExpression("abc");
        RPNExpression rpn3 = new RPNExpression("[ ] ^ &");

        //when
        String result1 = rpn1.evaluate();
        String result2 = rpn2.evaluate();
        String result3 = rpn3.evaluate();

        //then
        assertEquals(ERR_MSG, result1);
        assertEquals(ERR_MSG, result2);
        assertEquals(ERR_MSG, result3);
    }

    @Test
    public void evaluateExtraJunk()
    {
        //given
        RPNExpression rpn = new RPNExpression("6 4 2 /");

        //when
        String result = rpn.evaluate();

        //then
        assertEquals("RPN: 2.0\nExtra junk ignored", result);
    }

    @Test
    public void evaluateOnlyNumberInput()
    {
        //given
        RPNExpression rpn1 = new RPNExpression("3");
        RPNExpression rpn2 = new RPNExpression("3 4");

        //when
        String result1 = rpn1.evaluate();
        String result2 = rpn2.evaluate();

        //then
        assertEquals("RPN: " + 3.0, result1);
        assertEquals("RPN: 4.0\nExtra junk ignored", result2);
    }

    @Test
    public void evaluateIncompleteExpression()
    {
        // valid characters, not a complete RPN expression

        //given
        RPNExpression rpn1 = new RPNExpression("+");
        RPNExpression rpn2 = new RPNExpression("+ 3");
        RPNExpression rpn3 = new RPNExpression("+ +");
        RPNExpression rpn4 = new RPNExpression("3 +");

        //when
        String result1 = rpn1.evaluate();
        String result2 = rpn2.evaluate();
        String result3 = rpn3.evaluate();
        String result4 = rpn4.evaluate();

        //then
        assertEquals(ERR_MSG, result1);
        assertEquals(ERR_MSG, result2);
        assertEquals(ERR_MSG, result3);
        assertEquals(ERR_MSG, result4);
    }

    @Test
    public void evaluateIncorrectSyntaxExtraSymbols()
    {
        //given
        RPNExpression rpn1 = new RPNExpression("3 4 + +");
        RPNExpression rpn2 = new RPNExpression("+ 3 4 *");

        //when
        String result1 = rpn1.evaluate();
        String result2 = rpn2.evaluate();

        //then
        assertEquals(ERR_MSG, result1);
        assertEquals(ERR_MSG, result2);
    }

    @Test
    public void evaluateIncorrectSyntaxNoSpaces()
    {
        //given
        RPNExpression rpn1 = new RPNExpression("34+");
        RPNExpression rpn2 = new RPNExpression("3 4+");

        //when
        String result1 = rpn1.evaluate();
        String result2 = rpn2.evaluate();

        //then
        assertEquals(ERR_MSG, result1);
        assertEquals(ERR_MSG, result2);
    }

    @Test
    public void evaluateIncorrectSyntaxRegularNotation()
    {
        //given
        RPNExpression rpn = new RPNExpression("3 + 4");

        //when
        String result = rpn.evaluate();

        //then
        assertEquals(ERR_MSG, result);
    }

    @Test
    public void evaluateDivideByZero()
    {
        //given
        RPNExpression rpn1 = new RPNExpression("4 0 /");
        RPNExpression rpn2 = new RPNExpression("5 3 -3 + /");

        //when
        String result1 = rpn1.evaluate();
        String result2 = rpn2.evaluate();

        //then
        assertEquals(ERR_MSG, result1);
        assertEquals(ERR_MSG, result2);
    }

    @Test
    public void evaluateExtraWhiteSpace()
    {
        //given
        RPNExpression rpn = new RPNExpression(" 3 4 + ");
        RPNExpression rpn1 = new RPNExpression("3 4 +    ");
        RPNExpression rpn2 = new RPNExpression("    3 4 +");

        //when
        String result = rpn.evaluate();
        String result1 = rpn1.evaluate();
        String result2 = rpn2.evaluate();

        //then
        assertEquals("RPN: 7.0", result);
        assertEquals("RPN: 7.0", result1);
        assertEquals("RPN: 7.0", result2);
    }

    @Test
    public void evaluateMultipleTimes()
    {
        //given
        RPNExpression rpn1 = new RPNExpression("3 4 + +");

        //when
        String rpn1Round1 = rpn1.evaluate();
        String rpn1Round2 = rpn1.evaluate();

        //then
        assertEquals(rpn1Round1, rpn1Round2);
        assertEquals(rpn1Round2, rpn1Round1);
    }
}