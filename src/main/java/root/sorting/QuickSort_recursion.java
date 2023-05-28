package root.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort_recursion {

    public static void main(String[] args) {
        List<Integer> unsortedList = Arrays.asList(3, 15, 46, -1, 7, 2, -55, 6);
        List<Integer> sortedList = quickSort(unsortedList);
        System.out.println(unsortedList);
        System.out.println(sortedList);
    }

    private static List<Integer> quickSort(List<Integer> list) {
        if (list.size() < 2) {
            return list;
        }
        Integer pivot = list.get(0);
        List<Integer> lessThanPivot = new ArrayList<>();
        List<Integer> greaterThanPivot = new ArrayList<>();
        list.forEach(i -> {
            if (i < pivot) {
                lessThanPivot.add(i);
            } else if (i > pivot) {
                greaterThanPivot.add(i);
            }
        });
        return new ArrayList<Integer>() {{
            addAll(quickSort(lessThanPivot));
            add(pivot);
            addAll(quickSort(greaterThanPivot));
        }};
    }
}
