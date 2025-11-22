public class Main {
    public static void main(String[] args) {
        MenuBuilder menu = new MenuBuilder();

        menu.addMenuOption("Linear searching", () -> System.out.println("Ln search"));
        menu.addMenuOption("Binary searching", () -> System.out.println("Bin search"));
        menu.addMenuOption("O(n^2) type of sorting", () -> System.out.println("Exp sort"));
        menu.addMenuOption("O(n*log(n)) type of sorting", () -> System.out.println("Ln sort"));
        menu.addMenuOption("Sorting performance", () -> System.out.println("Cmp"));

        menu.start();
    }
}