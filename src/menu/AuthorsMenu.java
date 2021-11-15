package menu;

import controllers.AuthorController;

import java.util.Scanner;

public class AuthorsMenu {
    static void printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the authors' menu! Please, choose what do you want to do! ");
        System.out.println("1. Add author ->");
        System.out.println("2. Choose that return in MAIN MENU:");//return in main menu(RestaurantProgramList -> mainAction)
        System.out.println("0. Exit!");
        System.out.println();
        int inputSelection = scanner.nextInt();
        switch (inputSelection) {
            case 1:
                AuthorController.addNewAuthor();
                break;
            case 2:
                AuthorsMenu.execute();
                break;
            case 0:
                return;
            default:
                System.out.println("Did not recognize this selection, please try again!");
        }
    }

    public static void execute() {
        System.out.println("Welcome back to the menu for librarians!");
        MainMenu.librarianMenu();
    }
}

