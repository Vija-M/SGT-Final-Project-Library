package menu;

import controllers.BooksController;

import java.util.Scanner;

public class BooksMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void menu() {
        System.out.println("Welcome to the books' menu! Please, choose what do you want to do! ");
        System.out.println("1. Add a new book ->");
        System.out.println("2. Remove an book ->");
        System.out.println("3. Find a book by ID ->");
        System.out.println("4. Find a book by title ->");
        System.out.println("5. Update any information about book by ID ->");
        System.out.println("6. Print all books ->");
        System.out.println("7. Choose that for to return in MAIN MENU for librarians:");//return in main menu(RestaurantProgramList -> mainAction)
        System.out.println("0. Exit!");
        System.out.println();
        int inputSelection = scanner.nextInt();
        switch (inputSelection) {
            case 1:
                BooksController.addNewBook();
                break;
            case 2:
                BooksController.deleteBook();
                break;
            case 3:
                BooksController.findBookById();
                break;
            case 4:
                BooksController.findBookByTitle();
                break;
            case 5:
                BooksController.updateBook();
                break;
            case 6:
                BooksController.printAllBooks();
                break;
            case 7:
                BooksMenu.execute();
                break;
            case 0:
                return;
            default:
                System.out.println("Did not recognize this selection, please try again!");
        }
        BooksMenu.returnToBooksMenu();
    }


    public static void execute() {
        System.out.println("Welcome back to the menu for librarians!");
        MainMenu.librarianMenu();
    }

    public static void returnToBooksMenu() {
        System.out.print("\nIf you want to go back to books' menu print: y ------>  ");
        String answer = scanner.next();
        if (answer.equals("y")) {
            BooksMenu.menu();
        } else {
            MainMenu.librarianMenu();
        }
    }
}