package root.tasks;

import static root.Utils.printArray;
import static root.Utils.swap;

/*
 * Task:
 * 
 * Select 2 elements from array which produce maximum sum, e.g.:
 * 
 * Array: [5, 7, 6, -3, -11, 10, 1, 0]
 * MaxPair: [7, 10]
 * 
 */
public class MaxPairSelection
{
    public static void main(String[] args)
    {
        int[] array = new int[]{5, 7, 6, -3, -11, 10, 1, 0};
        int[] maxPair = selectMaxPair(array);
        printArray(maxPair);
    }
    
    private static int[] selectMaxPair(int[] array)
    {
        int[] maxPair = new int[2];
        maxPair[0] = array[0];
        maxPair[1] = array[1];
        
        for (int i = 2; i < array.length; i++)
        {
            if (array[i] > maxPair[0])
            {
                maxPair[0] = array[i];
            }
            if (array[i] > maxPair[1])
            {
                swap(maxPair, 0, 1);
            }
        }
        
        return maxPair;
    }
}
