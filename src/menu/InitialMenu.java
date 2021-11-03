package menu;

import java.util.Scanner;

public class InitialMenu {
    public static void printMenu() {
        Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the Rustic Library");
            System.out.println("Would you like to see a catalog? ");
            System.out.println("1: See the catalog.");
            System.out.println("0: Exit");

            int inputSelection = scanner.nextInt();

            switch (inputSelection){
                case 1:
                    System.out.println("Catalog: Rustic Library");
                    // ... leads to the catalog, I guess ....
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Did not recognize this selection, please try again!");
                    break;
            }
            printMenu();
        }
        public static void exit() {}
    }

