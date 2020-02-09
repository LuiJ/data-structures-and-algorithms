package root.sorting;

import static root.Utils.printArray;
import static root.Utils.swap;

public class QuickSort
{
    public static void main(String[] args)
    {
        int[] array = new int[]{3, 15, 46, -1, 7, 2, -55, 6};
        sort(array);
        printArray(array);
    }
    
    private static void sort(int[] array)
    {
        int pivotIndex = array.length - 1;
        int pivotValue = array[array.length - 1];       
        boolean swapped = true;
        while (swapped)
        {
            swapped = false;
            for (int i = 0; i < array.length; i++)
            {
                if (array[i] > pivotValue)
                {
                    for (int j = i + 1; j < array.length; j++)
                    {
                        if (array[j] < pivotValue)
                        {
                            swap(array, i, j);
                            swapped = true;
                            break;
                        }
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] > pivotValue)
            {
                swap(array, pivotIndex, i);
                break;
            }
        }
    }
}
