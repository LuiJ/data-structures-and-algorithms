package root.tasks;

import java.util.Stack;

// https://leetcode.com/problems/reverse-integer/description/

public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println("'123' ---> '" + reverse(123) + "'");
        System.out.println("'-123' ---> '" + reverse(-123) + "'");
        System.out.println("'120' ---> '" + reverse(120) + "'");
    }

    private static int reverse(int value) {
        var signMultiplier = value < 0 ? -1 : 1;
        var stack = new Stack<Integer>();
        var remainingValue = value * signMultiplier;
        while (remainingValue > 0) {
            stack.push(remainingValue % 10);
            remainingValue = remainingValue / 10;
        }
        var reversedValue = 0;
        var multiplier = 1;
        var stackSize = stack.size();
        for (int i = 0; i < stackSize; i++) {
            reversedValue = reversedValue + stack.pop() * multiplier;
            multiplier = multiplier * 10;
        }
        return reversedValue * signMultiplier;
    }
}
