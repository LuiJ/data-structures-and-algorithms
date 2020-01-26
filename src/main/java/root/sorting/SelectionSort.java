package root.sorting;

import static root.Utils.printIntArray;
import static root.Utils.swap;

public class SelectionSort
{
    public static void main(String[] args)
    {
        int[] array = new int[]{3, 5, 4, 1, 2};
        sort(array);
        printIntArray(array);
    }
    
    private static void sort(int[] array)
    {
        for (int pointer = 0; pointer < array.length - 1; pointer++)
        {
            int lowestElementIndex = pointer;
            int lowestElementValue = array[pointer];
            for (int j = pointer + 1; j < array.length; j++)
            {
                if (lowestElementValue > array[j])
                {
                    lowestElementIndex = j;
                    lowestElementValue = array[j];
                }
            }
            swap(array, pointer, lowestElementIndex);
        }
    }
}
