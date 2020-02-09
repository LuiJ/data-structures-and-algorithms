package root.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

public class PostfixExpressionCalculator
{
    private static final String EXPRESSION_MEMBERS_SEPARATOR = " ";
    private static final Map<String, BiFunction<Integer, Integer, Integer>> MATH_OPERATIONS_MAP = new HashMap<>();

    static
    {
        MATH_OPERATIONS_MAP.put("+", (operand1, operand2) -> operand1 + operand2);
        MATH_OPERATIONS_MAP.put("-", (operand1, operand2) -> operand1 - operand2);
        MATH_OPERATIONS_MAP.put("/", (operand1, operand2) -> operand1 / operand2);
        MATH_OPERATIONS_MAP.put("*", (operand1, operand2) -> operand1 * operand2);
    }

    public static void main(String[] args)
    {
        String postfixExpression = "5 4 2 / 3 * +";
        int result = calculate(postfixExpression);
        System.out.println("[" + postfixExpression + "] = " + result);
    }

    private static int calculate(String postfixExpression)
    {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        String[] expressionMembers = postfixExpression.split(EXPRESSION_MEMBERS_SEPARATOR);
        for (String member : expressionMembers)
        {
            try
            {
                int operand = Integer.parseInt(member);
                stack.push(operand);
            }
            catch (NumberFormatException e)
            {
                String operator = member;
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                result = MATH_OPERATIONS_MAP.get(operator).apply(operand1, operand2);
                stack.push(result);
            }
        }
        return result;
    }
}
