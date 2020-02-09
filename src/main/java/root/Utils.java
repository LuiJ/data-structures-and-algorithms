package root;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils
{
    public static void swap(int[] array, int index1, int index2)
    {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    
    public static void printArray(int[] array)
    {
        System.out.println(Arrays.stream(array)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(", ", "[", "]")));
    }

    public static int[] unite(int[] array1, int[] array2)
    {
        int[] unitedArray = new int[array1.length + array2.length];
        int unitedArrayPointer = 0;
        for (int i = 0; i < array1.length; i++)
        {
            unitedArray[unitedArrayPointer] = array1[i];
            unitedArrayPointer++;
        }
        for (int i = 0; i < array2.length; i++)
        {
            unitedArray[unitedArrayPointer] = array2[i];
            unitedArrayPointer++;
        }
        return unitedArray;
    }

    public static int sum(int[] array)
    {
        int sum = 0;
        for (int i = 0; i < array.length; i++)
        {
            sum += array[i];
        }
        return sum;
    }
}
