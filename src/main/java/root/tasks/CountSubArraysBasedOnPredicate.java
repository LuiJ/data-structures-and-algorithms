package root.tasks;

/*
    Given an array of integers.
    Count number of sub-arrays in which sum of items <= K
    and number of 0-items <= 1

    Examples:

    solve([1,2,3,4], 4) --> [1], [1,2], [2], [3], [4] --> 5

    solve([1,0,2,0,3], 4) --> [1], [1,0], [1,0,2], [0], [0,2], [2], [2,0], [0], [0,3], [3] --> 10
 */

public class CountSubArraysBasedOnPredicate {

    public static void main(String[] args) {
        var input = new int[] {1,0,2,0,3};
        var sumThreshold = 4;
        var result = countSubArrays(input, sumThreshold);
        System.out.println(result);
    }

    private static int countSubArrays(int[] input, int sumThreshold) {
        int nSubArrays = 0;
        for (int i = 0; i < input.length; i++) {
            int nZeros = 0;
            int subArraySum = 0;
            for (int j = i; j < input.length; j++) {
                int item = input[j];
                if (item == 0) nZeros++;
                subArraySum += item;
                if (nZeros > 1 || subArraySum > sumThreshold) {
                    nZeros = 0;
                    subArraySum = 0;
                    break;
                }
                nSubArrays++;
            }
        }
        return nSubArrays;
    }
}
