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
    
    public static void printIntArray(int[] array)
    {
        System.out.println(Arrays.stream(array)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(", ", "[", "]")));
    }
}
