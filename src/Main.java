import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        MenuBuilder menu = new MenuBuilder();

        menu.addMenuOption("Linear searching", Main::performLinearSearch);
        menu.addMenuOption("Binary searching", Main::performBinarySearch);
        menu.addMenuOption("O(n^2) type of sorting", Main::performInsertionSort);
        menu.addMenuOption("O(n*log(n)) type of sorting", Main::performQuickSort);
        menu.addMenuOption("Sorting performance", Main::performSortingPerformance);

        menu.start();
    }

    /**
     * Driver function for linear search action.
     */
    private static void performLinearSearch() {
        List<Integer> list = setupSearchList();

        try {
            int element = Integer.parseInt(input.nextLine());

            for (int i : list) {
                if (element == i) {
                    System.out.println("\nFound");
                    return;
                }
            }
            System.out.println("\nNot found");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    /**
     * Driver function for binary search action
     */
    private static void performBinarySearch() {
        List<Integer> list = setupSearchList();

        try {
            int element = Integer.parseInt(input.nextLine());
            int l = 0;
            int r = list.size() - 1;

            while (l <= r) {
                int middle = (l + r) / 2;

                if (list.get(middle) == element) {
                    System.out.println("\nFound");
                    return;
                }

                if (list.get(middle) > element) {
                    r = middle - 1;
                } else {
                    l = middle + 1;
                }
            }
            System.out.println("\nNot found");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    /**
     * Driver function for insertion sort demo
     */
    private static void performInsertionSort() {
        ComparableItem[] data = generateRandomData(10);

        System.out.println("\nData set before insertion sorting:");
        printData(data);

        insertionSort(data);

        System.out.println("\nData set after insertion sorting:");
        printData(data);
    }

    /**
     * Driver function for quick sort demo
     */
    private static void performQuickSort() {
        ComparableItem[] data = generateRandomData(10);

        System.out.println("\nData set before quicksort:");
        printData(data);

        quickSort(data, 0, data.length - 1);

        System.out.println("\nData set after quicksort:");
        printData(data);
    }

    /**
     * Driver function for sorting performance tests
     */
    private static void performSortingPerformance() {
        final int BASE_SIZE = 1000;
        final int STEPS = 10;
        String[] algorithms = {"bubbleSort", "insertionSort", "quickSort", "mergeSort"};

        // Header Row
        System.out.printf("%-30s", "");
        for (int i = 1; i <= STEPS; i++) {
            System.out.printf("%-12d", BASE_SIZE * i);
        }
        System.out.println();

        for (String algo : algorithms) {
            long[] comparisons = new long[STEPS];
            long[] times = new long[STEPS];

            // Run tests for sizes 1000, 2000 ... 10000
            for (int i = 1; i <= STEPS; i++) {
                int size = BASE_SIZE * i;
                ComparableItem[] data = generateRandomData(size);

                ComparableItem.resetComparisonCount();
                long start = System.nanoTime();

                switch (algo) {
                    case "bubbleSort":
                        bubbleSort(data);
                        break;
                    case "insertionSort":
                        insertionSort(data);
                        break;
                    case "quickSort":
                        quickSort(data, 0, data.length - 1);
                        break;
                    case "mergeSort":
                        mergeSort(data, 0, data.length - 1);
                        break;
                }

                long end = System.nanoTime();

                times[i - 1] = (end - start) / 1_000_000; // Convert ns to ms
                comparisons[i - 1] = ComparableItem.getComparisonCount();
            }

            // Comparison Row
            System.out.printf("%-30s", algo + ",random,comparisons");
            for (long c : comparisons) System.out.printf("%-12d", c);
            System.out.println();

            // Time Row
            System.out.printf("%-30s", algo + ",random,ms");
            for (long t : times) System.out.printf("%-12d", t);
            System.out.println();
        }
    }


    /**
     * Insertion sort algorithm implementation
     * @param array
     */
    private static void insertionSort(ComparableItem[] array) {
        for (int i = 1; i < array.length; i++) {
            ComparableItem key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    /**
     * Quick sort algorithm implementation
     * @param array
     * @param low
     * @param high
     */
    private static void quickSort(ComparableItem[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    /**
     * Helper function for QuickSort to partition the array.
     * @param array
     * @param low
     * @param high
     */
    private static int partition(ComparableItem[] array, int low, int high) {
        ComparableItem pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                ComparableItem temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        ComparableItem temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    /**
     * Bubble sort algorithm implementation
     * @param array
     */
    private static void bubbleSort(ComparableItem[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    ComparableItem temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Merge sort algorithm implementation
     * @param array
     * @param left
     * @param right
     */
    private static void mergeSort(ComparableItem[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    /**
     * Helper function for MergeSort to merge two subarrays.
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(ComparableItem[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        ComparableItem[] L = new ComparableItem[n1];
        ComparableItem[] R = new ComparableItem[n2];

        for (int i = 0; i < n1; ++i) L[i] = array[left + i];
        for (int j = 0; j < n2; ++j) R[j] = array[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) array[k++] = L[i++];
        while (j < n2) array[k++] = R[j++];
    }


    /**
     * Prepares the list and print statements for search demos.
     */
    private static List<Integer> setupSearchList() {
        List<Integer> list = new ArrayList<>();
        int lowerBound = 0;
        int upperBound = 9;

        for (int i = lowerBound; i <= upperBound; i++) {
            list.add(i);
        }

        System.out.printf("In the list are values %d, ..., %d\n", lowerBound, upperBound);
        System.out.print("Which value would you like to search? ");
        return list;
    }

    /**
     * Generates an array of random ComparableItem objects.
     * @param size
     * @return
     */
    private static ComparableItem[] generateRandomData(int size) {
        ComparableItem[] data = new ComparableItem[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            // Generates random long values between -100 and 100
            data[i] = new ComparableItem(random.nextInt(201) - 100);
        }
        return data;
    }

    /**
     * Prints the contents of a ComparableItem array.
     * @param data
     */
    private static void printData(ComparableItem[] data) {
        for (ComparableItem item : data) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}