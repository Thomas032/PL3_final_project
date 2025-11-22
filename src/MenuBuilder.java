import java.util.ArrayList;
import java.util.Scanner;

public class MenuBuilder {
   private static class MenuOption {
       String title;
       Runnable action;

       public MenuOption(String title, Runnable action) {
           this.title = title;
           this.action = action;
       }
   }

   private final ArrayList<MenuOption> options = new ArrayList<>();
   private final Scanner scanner;

   public MenuBuilder() {
       this.scanner = new Scanner(System.in);
   }

   public void addMenuOption(String title, Runnable action) {
       options.add(new MenuOption(title, action));
   }

   public void printMenu(){
       System.out.println("\nMenu of Searching and Sorting Testbed.\n");

       for (int i = 0; i < options.size(); i++) {
           System.out.printf("%d) %s\n", (i + 1), options.get(i).title);
       }
       System.out.println("\nq/Q) Quit\n");
   }

   public void start(){
       while(true){
            printMenu();

            System.out.print("Your choice: ");
            String in = scanner.nextLine().trim();

            if(in.equalsIgnoreCase("q")){
                System.out.println("Quitting...");
                break;
            }

            try{
                int choice = Integer.parseInt(in);

                if(choice > 0 && choice <= options.size()){
                    // valid choice - execute the right action
                    options.get(choice - 1).action.run();
                }
                else{
                    System.out.println("Invalid option. Please try again");
                }

            }catch (NumberFormatException e){
                System.out.println("Please enter a number or 'q' to quit.");
            }

           System.out.println("\n------------------------------------------");
       }
   }

}
