package root.tasks;

import java.util.Stack;

// https://leetcode.com/problems/multiply-strings/description/

public class MultiplyStrings {

    public static void main(String[] args) {
        System.out.println("'2 * 2' ---> '" + multiply("2", "2") + "'");
        System.out.println("'123 * 456' ---> '" + multiply("123", "456") + "'");
    }

    private static String multiply(String s1, String s2) {
        int i1 = toInt(s1);
        int i2 = toInt(s2);
        int multiplication = i1 * i2;
        return toString(multiplication);
    }

    private static int toInt(String string) {
        var stack = new Stack<Integer>();
        for (char ch : string.toCharArray()) {
            stack.push(Character.getNumericValue(ch));
        }
        var multiplier = 1;
        var integer = 0;
        while (!stack.empty()) {
            integer = integer + stack.pop() * multiplier;
            multiplier = multiplier * 10;
        }
        return integer;
    }

    private static String toString(int integer) {
        var stack = new Stack<Integer>();
        while (integer > 0) {
            stack.push(integer % 10);
            integer = integer / 10;
        }
        var stringBuilder = new StringBuilder();
        while (!stack.empty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }
}
