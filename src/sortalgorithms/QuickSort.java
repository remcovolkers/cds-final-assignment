package sortalgorithms;

import java.util.List;

public class QuickSort {
    //inspiratie van https://www.geeksforgeeks.org/quick-sort/ en sheets
    public static <T extends Comparable<T>> void quickSort(List<T> list, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(list, begin, end);

            quickSort(list, begin, partitionIndex - 1);
            quickSort(list, partitionIndex + 1, end);
        }
    }

    private static <T extends Comparable<T>> int partition(List<T> list, int begin, int end) {
        T pivot = list.get(end);
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;

                T swapTemp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, swapTemp);
            }
        }
        T swapTemp = list.get(i + 1);
        list.set(i + 1, list.get(end));
        list.set(end, swapTemp);

        return i + 1;
    }
}
