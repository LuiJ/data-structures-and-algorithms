package root.sorting;

import static root.Utils.printArray;

public class MergeSort
{
    public static void main(String[] args)
    {
        int[] unsortedArray = new int[]{4, 7, 2, 72, 16, 19, -1, 50};
        int[] sortedArray = performMergeSort(unsortedArray, 0, unsortedArray.length - 1);
        printArray(sortedArray);
    }

    private static int[] performMergeSort(int[] array, int startIndex, int endIndex)
    {
        if (startIndex == endIndex)
        {
            return new int[]{array[startIndex]};
        }
        int middleIndex = (endIndex - startIndex) / 2 + startIndex;
        int[] sortedPart1 = performMergeSort(array, startIndex, middleIndex);
        int[] sortedPart2 = performMergeSort(array, middleIndex + 1, endIndex);
        return mergeSortedParts(sortedPart1, sortedPart2);
    }

    private static int[] mergeSortedParts(int[] sortedPart1, int[] sortedPart2)
    {
        int[] mergeResult = new int[sortedPart1.length + sortedPart2.length];
        int pointer1 = 0;
        int pointer2 = 0;
        for (int i = 0; i < mergeResult.length; i++)
        {
            if (pointer1 < sortedPart1.length && pointer2 < sortedPart2.length)
            {
                if (sortedPart1[pointer1] < sortedPart2[pointer2])
                {
                    mergeResult[i] = sortedPart1[pointer1];
                    pointer1++;
                }
                else if (sortedPart1[pointer1] > sortedPart2[pointer2])
                {
                    mergeResult[i] = sortedPart2[pointer2];
                    pointer2++;
                }
            }
            else if (pointer2 == sortedPart2.length && pointer1 < sortedPart1.length)
            {
                mergeResult[i] = sortedPart1[pointer1];
                pointer1++;
            }
            else if (pointer1 == sortedPart1.length && pointer2 < sortedPart2.length)
            {
                mergeResult[i] = sortedPart2[pointer2];
                pointer2++;
            }
        }
        return mergeResult;
    }
}
