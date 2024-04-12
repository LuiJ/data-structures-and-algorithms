package root.tasks;

// https://leetcode.com/problems/add-two-numbers/description/

import static root.Utils.printArray;

public class AddTwoNumbers {

    public static void main(String[] args) {
        printArray(addTwoNumbers(new int[]{2,4,3}, new int[]{5,6,4}));
        printArray(addTwoNumbers(new int[]{0}, new int[]{0}));
        printArray(addTwoNumbers(new int[]{9,9,9,9,9,9,9}, new int[]{9,9,9,9}));
    }

    private static int[] addTwoNumbers(int[] number1Digits, int[] number2Digits) {
        int maxLength = Math.max(number1Digits.length, number2Digits.length);
        int[] result = new int[maxLength + 1];
        for (int i = 0; i < maxLength; i++) {
            int digitOfNumber1 = i < number1Digits.length ? number1Digits[i] : 0;
            int digitOfNumber2 = i < number2Digits.length ? number2Digits[i] : 0;
            int remainder = i > 0 ? result[i] : 0;
            int sum = digitOfNumber1 + digitOfNumber2 + remainder;
            if (sum > 9) {
                result[i+1] = 1;
            }
            result[i] = sum % 10;
        }
        return result;
    }
}
