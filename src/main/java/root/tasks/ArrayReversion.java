package root.tasks;

import static root.Utils.printArray;
import static root.Utils.swap;

/*
 * Task:
 *
 * Revert array: [0, 1, 2] -> [2, 1, 0]
 *
 */
public class ArrayReversion
{
    public static void main(String[] args)
    {
        int[] array = new int[]{0, 1, 2, 3, 4, 5};
        printArray(array);
        revert(array);
        printArray(array);
    }

    private static void revert(int[] array)
    {
        int forwardPointer = 0;
        int backwardPointer = array.length - 1;
        while (forwardPointer < backwardPointer)
        {
            swap(array, forwardPointer, backwardPointer);
            forwardPointer++;
            backwardPointer--;
        }
    }
}
