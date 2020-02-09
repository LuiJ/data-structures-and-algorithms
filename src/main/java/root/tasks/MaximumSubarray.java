package root.tasks;

import static root.Utils.printArray;
import static root.Utils.sum;

public class MaximumSubarray {

    public static void main(String[] args)
    {
        int[] array = new int[] {13, 3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        System.out.println("Input array");
        printArray(array);

        long startBruteForce = System.nanoTime();
        int[] maxSubarrayByBruteForce = findUsingBruteForce(array);
        long timeOfBruteForce = System.nanoTime() - startBruteForce;
        System.out.println("Result by Brute Force method:");
        printArray(maxSubarrayByBruteForce);
        System.out.println("Brute Force method took: [" + timeOfBruteForce + "] ns");

        long startDivideAndConquer = System.nanoTime();
        int[] maxSubarrayByDivideAndConquer = findUsingDivideAndConquer(array);
        long timeOfDivideAndConquer = System.nanoTime() - startDivideAndConquer;
        System.out.println("Result by Divide And Conquer method:");
        printArray(maxSubarrayByDivideAndConquer);
        System.out.println("Divide And Conquer method took: [" + timeOfDivideAndConquer + "] ns");

        if (timeOfBruteForce > timeOfDivideAndConquer)
        {
            System.out.println("Divide And Conquer is [" + (timeOfBruteForce - timeOfDivideAndConquer) + "] ns faster than Brute Force");
        }
        else
        {
            System.out.println("Brute Force is [" + (timeOfDivideAndConquer - timeOfBruteForce) + "] ns faster than Divide And Conquer");
        }
    }

    private static int[] findUsingBruteForce(int[] array)
    {
        int[] maxSubarray = array;
        int subarrayMaxSum = Integer.MIN_VALUE;
        for (int subarrayLength = 1; subarrayLength <= array.length; subarrayLength++)
        {
            for (int i = 0; i + subarrayLength <= array.length; i++)
            {
                int[] subarray = new int[subarrayLength];
                int subarraySum = 0;
                for (int j = 0; j < subarrayLength; j++)
                {
                    subarray[j] = array[i + j];
                    subarraySum += array[i + j];
                }
                if (subarraySum > subarrayMaxSum)
                {
                    subarrayMaxSum = subarraySum;
                    maxSubarray = subarray;
                }
            }
        }
        return maxSubarray;
    }

    private static int[] findUsingDivideAndConquer(int[] array)
    {
        if (array.length <= 1)
        {
            return new int[] {array[0]};
        }
        int startIndex = 0;
        int endIndex = array.length - 1;
        int middleIndex = (endIndex - startIndex) / 2 + startIndex;
        int[] leftSubarray = getSubarray(array, startIndex, middleIndex);
        int[] rightSubarray = getSubarray(array, middleIndex + 1, endIndex);
        int[] maxLeftSubarray = findUsingDivideAndConquer(leftSubarray);
        int[] maxRightSubarray = findUsingDivideAndConquer(rightSubarray);
        int[] maxSubarray = array;
        int subarrayMaxSum = sum(array);
        int leftSubarraySum = sum(maxLeftSubarray);
        int rightSubarraySum = sum(maxRightSubarray);
        if (leftSubarraySum > subarrayMaxSum)
        {
            subarrayMaxSum = leftSubarraySum;
            maxSubarray = maxLeftSubarray;
        }
        if (rightSubarraySum > subarrayMaxSum)
        {
            subarrayMaxSum = rightSubarraySum;
            maxSubarray = maxRightSubarray;
        }
        int[] maxMiddleSubarray = findMaxMiddleSubarray(leftSubarray, rightSubarray);
        int middleSubarraySum = sum(maxMiddleSubarray);
        if (middleSubarraySum > subarrayMaxSum)
        {
            maxSubarray = maxMiddleSubarray;
        }
        return maxSubarray;
    }

    private static int[] findMaxMiddleSubarray(int[] leftSubarray, int[] rightSubarray)
    {
        if (leftSubarray.length == 1 || rightSubarray.length == 1)
        {
            return sum(leftSubarray) > sum(rightSubarray) ? leftSubarray : rightSubarray;
        }
        int[] maxMiddleSubarray = leftSubarray;
        int middleSubarrayMaxSum = Integer.MIN_VALUE;
        for (int middleSubarrayLength = 2; middleSubarrayLength <= leftSubarray.length; middleSubarrayLength++)
        {
            int numberOfElementsFromRightSubarray = 1;
            int numberOfElementsFromLeftSubarray = middleSubarrayLength - numberOfElementsFromRightSubarray;
            while (numberOfElementsFromLeftSubarray > 0)
            {
                int[] middleSubarray = new int[middleSubarrayLength];
                int middleSubarrayPointer = 0;
                for (int i = leftSubarray.length - numberOfElementsFromLeftSubarray; i < leftSubarray.length; i++)
                {
                    middleSubarray[middleSubarrayPointer] = leftSubarray[i];
                    middleSubarrayPointer++;
                }
                for (int i = 0; i < numberOfElementsFromRightSubarray; i++)
                {
                    middleSubarray[middleSubarrayPointer] = rightSubarray[i];
                    middleSubarrayPointer++;
                }
                int middleSubarraySum = sum(middleSubarray);
                if (middleSubarraySum > middleSubarrayMaxSum)
                {
                    middleSubarrayMaxSum = middleSubarraySum;
                    maxMiddleSubarray = middleSubarray;
                }
                numberOfElementsFromLeftSubarray--;
                numberOfElementsFromRightSubarray++;
            }
        }
        return maxMiddleSubarray;
    }

    private static int[] getSubarray(int[] array, int startIndex, int endIndex)
    {
        int[] subarray = new int[endIndex - startIndex  + 1];
        for (int i = 0; i < subarray.length; i++)
        {
            subarray[i] = array[startIndex + i];
        }
        return subarray;
    }
}
