import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        MenuBuilder menu = new MenuBuilder();

        menu.addMenuOption("Linear searching", Main::performLinearSearch);
        menu.addMenuOption("Binary searching", () -> System.out.println("Bin search"));
        menu.addMenuOption("O(n^2) type of sorting", () -> System.out.println("Exp sort"));
        menu.addMenuOption("O(n*log(n)) type of sorting", () -> System.out.println("Ln sort"));
        menu.addMenuOption("Sorting performance", () -> System.out.println("Cmp"));

        menu.start();
    }

    private static void performLinearSearch(){
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

            for(int i : list){
                if(element == i){
                    System.out.println("\nFound");
                    return;
                }
            }

            System.out.println("\nNot found");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }


    }
}