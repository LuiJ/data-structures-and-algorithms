package root.sorting;

import static root.Utils.printArray;
import static root.Utils.swap;

public class BubbleSort
{
    public static void main(String[] args)
    {
        int[] array = new int[]{3, 5, 4, 1, 2};
        sort(array);
        printArray(array);
    }
    
    private static void sort(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array.length - 1; j++)
            {
                if (array[j] > array[j + 1])
                {
                    // swap current and next element
                    swap(array, j, j + 1);
                }
            }
        }
    }
}
