package menu;

import controllers.UserController;

import java.util.Scanner;

public class UsersMenu {
    static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the users' menu! Please, choose what do you want to do! ");
        System.out.println("1. Add user ->");
        System.out.println("2. Choose that return in MAIN MENU:");//return in main menu(RestaurantProgramList -> mainAction)
        System.out.println("0. Exit!");
        System.out.println();
        int inputSelection = scanner.nextInt();
        switch (inputSelection) {
            case 1:
                UserController.addNewUser();
                break;
            case 2:
                menu.UsersMenu.execute();
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

