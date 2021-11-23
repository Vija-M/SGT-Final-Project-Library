package menu;

import controllers.UserController;

import java.util.Scanner;

public class UsersMenu {
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the users' menu! Please, choose what do you want to do! ");
        System.out.println("1. Add user ->");
        System.out.println("2. Delete user ->");
        System.out.println("3. Update any user's info ->");
        System.out.println("4. Find user by ID ->");
        System.out.println("5. Find user by e-mail ->");
        System.out.println("6. Print a list of all users ->");
        System.out.println("7. Choose that return in MAIN MENU for librarians:");//return in main menu(RestaurantProgramList -> mainAction)
        System.out.println("0. Exit!");
        System.out.println();
        int inputSelection = scanner.nextInt();
        switch (inputSelection) {
            case 1:
                UserController.addNewUser();
                break;
            case 2:
                UserController.deleteUser();
                break;
            case 3:
                UserController.updateUser();
                break;
            case 4:
                UserController.findUserById();
                break;
            case 5:
                UserController.findUserByEmail();
                break;
            case 6:
                UserController.printAllUsers();
                break;
            case 7:
                menu.UsersMenu.execute();
                break;
            case 0:
                return;
            default:
                System.out.println("Did not recognize this selection, please try again!");
        }
        UserController.execute();
    }

    public static void execute() {
        System.out.println("Welcome back to the menu for librarians!");
        MainMenu.librarianMenu();
    }
}

