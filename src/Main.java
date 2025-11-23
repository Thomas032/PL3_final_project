import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        MenuBuilder menu = new MenuBuilder();

        menu.addMenuOption("Linear searching", Main::performLinearSearch);
        menu.addMenuOption("Binary searching", Main::performBinarySearch);
        menu.addMenuOption("O(n^2) type of sorting", Main::performInsertionSort);
        menu.addMenuOption("O(n*log(n)) type of sorting", Main::performInsertionSort);
        menu.addMenuOption("Sorting performance", () -> System.out.println("Cmp"));

        menu.start();
    }

    /** Driver function for linear search action. */
    private static void performLinearSearch() {
        final int upperBound = 9;
        final int lowerBound = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = lowerBound; i <= upperBound; i++){
            list.add(i);
        }

        System.out.printf("In the list are values %d, ..., %d\n", lowerBound, upperBound);
        System.out.print("Which value would you like to search with linear search? ");

        try{
            String val = input.nextLine();
            int element = Integer.parseInt(val);

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

    /** Driver function for binary search action. */
    private static void performBinarySearch() {
        final int upperBound = 9;
        final int lowerBound = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = lowerBound; i <= upperBound; i++){
            list.add(i);
        }

        System.out.printf("In the list are values %d, ..., %d\n", lowerBound, upperBound);
        System.out.print("Which value would you like to search with linear search? ");

        try{
            String val = input.nextLine();
            int element = Integer.parseInt(val);

            int l = 0;
            int r = list.size() - 1;

            while(l <= r){
                int middle = (l+r)/2;

                if(list.get(middle) == element){
                    System.out.println("\nFound");
                    return;
                }

                if(list.get(middle) > element) {
                    r = middle - 1;
                }
                else {
                    l = middle + 1;
                }
            }

            System.out.println("\nNot found");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    /**
     * Helper function for printing arrays.
     * @param data array to print
     */
    private static void printData(int[] data) {
        for (Integer i : data) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /** Driver function for insertion sort action. */
    private static void performInsertionSort() {
        int[] data = {-93, -36, 25, 44, -30, -21, 34, 56, 82, 64};

        System.out.println("\nData set before insertion sorting:");
        printData(data);

        insertionSort(data);

        System.out.println("\nData set after insertion sorting:");
        printData(data);
    }

    /**
     * Insertion sort implementation.
     * @param array array to sort
     */
    private static void insertionSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    /** Driver method for quick sort action. */
    private static void performQuickSort(){
        int[] data = {-93, -36, 25, 44, -30, -21, 34, 56, 82, 64};

        System.out.println("\nData set before quicksort:");
        printData(data);

        quickSort(data, 0, data.length - 1);

        System.out.println("\nData set after quicksort:");
        printData(data);
    }

    /**
     * Quicksort algorithm.
     * @param array array to sort
     * @param low starting index
     * @param high ending index
     */
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    /**
     * Partition step for quicksort.
     * @param array array to partition
     * @param low low index
     * @param high high index (pivot)
     * @return pivot index
     */
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}
