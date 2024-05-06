package root.tasks;

/*
    Merge 2 sorted arrays with unique elements without duplications:

    Input:
    array1 = [1, 3, 4, 5, 7]
    array2 = [2, 3, 5, 6]

    Result: [1, 2, 3, 4, 5, 6, 7]
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MergeTwoSortedArrays {

    public static void main(String[] args) {
        var array1 = new int[] {1, 1, 3, 4, 5, 7, 7, 7};
        var array2 = new int[] {2, 3, 5, 6, 6, 8, 8, 8};
        var result = mergeArrays(array1, array2);
        System.out.println(result);
    }

    private static List<Integer> mergeArrays(int[] array1, int[] array2) {
        var result = new ArrayList<Integer>();
        var array1Pointer = 0;
        var array2Pointer = 0;
        while (array1Pointer < array1.length || array2Pointer < array2.length) {
            var array1ItemOptional = getArrayItem(array1, array1Pointer);
            var array2ItemOptional = getArrayItem(array2, array2Pointer);
            if (array1ItemOptional.isPresent() && array2ItemOptional.isEmpty()) {
                addItemToResult(array1ItemOptional.get(), result);
                array1Pointer++;
            } else if (array1ItemOptional.isEmpty() && array2ItemOptional.isPresent()) {
                addItemToResult(array2ItemOptional.get(), result);
                array2Pointer++;
            } else {
                var array1Item = array1ItemOptional.get();
                var array2Item = array2ItemOptional.get();
                if (array1Item < array2Item) {
                    addItemToResult(array1Item, result);
                    array1Pointer++;
                } else if (array2Item < array1Item) {
                    addItemToResult(array2Item, result);
                    array2Pointer++;
                } else { // items are equal
                    addItemToResult(array1Item, result);
                    array1Pointer++;
                    array2Pointer++;
                }
            }
        }
        return result;
    }

    private static Optional<Integer> getArrayItem(int[] array, int index) {
        if (index >= array.length) {
            return Optional.empty();
        }
        return Optional.of(array[index]);
    }

    private static void addItemToResult(Integer item, List<Integer> result) {
        if (result.size() == 0) {
            result.add(item);
            return;
        }
        var lastAddedItem = result.get(result.size() - 1);
        if (!lastAddedItem.equals(item)) {
            result.add(item);
        }
    }
}
