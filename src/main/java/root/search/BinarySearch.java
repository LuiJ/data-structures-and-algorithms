package root.search;

public class BinarySearch
{
    public static void main(String[] args)
    {
        int[] array = new int[]{1, 2, 3, 4, 5};
        System.out.println((search(1, array, 0, array.length - 1) ? "" : "NOT ") + "FOUND [" + 1 + "]");
        System.out.println((search(2, array, 0, array.length - 1) ? "" : "NOT ") + "FOUND [" + 2 + "]");
        System.out.println((search(3, array, 0, array.length - 1) ? "" : "NOT ") + "FOUND [" + 3 + "]");
        System.out.println((search(4, array, 0, array.length - 1) ? "" : "NOT ") + "FOUND [" + 4 + "]");
        System.out.println((search(5, array, 0, array.length - 1) ? "" : "NOT ") + "FOUND [" + 5 + "]");
        System.out.println((search(6, array, 0, array.length - 1) ? "" : "NOT ") + "FOUND [" + 6 + "]");
        System.out.println((search(7, array, 0, array.length - 1) ? "" : "NOT ") + "FOUND [" + 7 + "]");
    }
    
    public static boolean search(int element, int[] array, int startIndex, int endIndex)
    {
        if (startIndex > endIndex)
        {
            return false;
        }
        int middleIndex = (endIndex - startIndex) / 2 + startIndex;
        if (element > array[middleIndex])
        {
            return search(element, array, middleIndex + 1, endIndex);
        }
        else if (element < array[middleIndex])
        {
            return search(element, array, startIndex, middleIndex - 1);
        }
        else 
        {
            return true;
        }
    }
    
    /*
     * A way to find middleIndex of sub-array: 
     * 
     * Assume we have next array [0, 1, 2, 3, 4, 5, 6, 7] 
     * and we would like to find middleIndex of sub-array:
     * - startIndex = 3
     * - endIndex = 7
     * 
     * To find middleIndex we can represent the array as set of sections:
     * 
     * 0    1    2    3    4    5    6    7
     * |----|----|----|----|----|----|----|
     * A              B         D         C
     * 
     * In other words we are going to find length AD section.
     * 
     * A - first index of array;
     * B - first index of sub-array (startIndex);
     * C - last index of array and sub-array (endIndex);
     * D - middleIndex, which we are going to find.
     * 
     * Solution:
     * 
     * 1) BD = (C - B) / 2;
     * 2) AD = BD + AB;
     * 
     * Full formula:
     * 
     * D = (C - B) / 2 + AB;
     * 
     * OR
     * 
     * int middleIndex = (endIndex - startIndex) / 2 + startIndex;
     * 
     */
}
