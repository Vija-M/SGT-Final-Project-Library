package menu;

import controllers.AuthorController;
import util.DBHelper;

import java.util.Scanner;

public class MainMenu {

    static Scanner scan = new Scanner(System.in);
    static Boolean running = true;
    public static DBHelper helper;

    public static void main(String[] args) {
        helper = new DBHelper();
        System.out.println("Welcome to the Library!");

        System.out.println("Please enter 1 if you are a client or 2 if you are a librarian.");
        if (scan.nextInt() == 2) {
            System.out.println("Please enter your userID.");
            int userID = scan.nextInt();
            MainMenu.librarianMenu();
        } else {
            MainMenu.clientMenu();
        }
    }

    static void clientMenu() {
        while (running) { // i.e., while the application is running
            System.out.println("Enter 0 for entering your library account." + "\n"
                    + "Enter 1 to print library collection." + "\n"
                    + "Enter 2 to search for a book." + "\n"
                    + "Enter 3 to borrow a book." + "\n"
                    + "Enter 4 to return a book." + "\n"
                    + "Enter 5 to retrieve a short bio for an author." + "\n"
                    + "Enter 6 to save and quit.");

            int clientResponse = scan.nextInt();

            switch (clientResponse) {
                case 0:
                    LibraryCollection.loadClientInfo();
                    break;

                case 1:
                    LibraryCollection.libraryCollection();
                    break;

                case 2:
                    scan.nextLine();
                    LibraryCollection.searchBook();
                    break;

                case 3:
                    scan.nextLine();
                    LibraryCollection.borrowBook();
                    break;

                case 4:
                    scan.nextLine();
                    LibraryCollection.returnBook();
                    break;

                case 5:
                    AuthorController.findAuthorByName();
                    break;

                case 6:
                    MainMenu.saveAndQuit();
                    break;
            }
        }
        System.exit(0);
    }


    public static void librarianMenu() {
        System.out.println("Welcome to the internal system of Rustic Library!");
        System.out.println("Please, choose one of the options below.");
        System.out.println();
        System.out.println("1--> choose action with BOOKS: ");
        System.out.println("2--> choose action with AUTHORS: ");
        System.out.println("3--> choose action with library USERS: ");
        System.out.println("4--> choose action with library ORDERS: ");
        System.out.println("0--> EXIT! ");
        System.out.println();

        int inputSelection = scan.nextInt();

        switch (inputSelection) {
            case 1:
                BooksMenu.menu();
                break;
            case 2:
                AuthorsMenu.menu();
                break;
            case 3:
                UsersMenu.menu();
                break;
            case 4:
                OrdersMenu.menu();
                break;
            case 0:
                return;
            default:
                System.out.println("Did not recognize this selection, please try again!");
                break;
        }
        System.exit(0);
    }

    private static void saveAndQuit() {
        System.exit(0);
    }
}