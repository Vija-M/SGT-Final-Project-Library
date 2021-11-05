package menu;

import java.util.Scanner;

public class InitialMenu {

    public static void isThereABook() {
        Scanner scanner = new Scanner(System.in); //monitors console for an input

        System.out.println("What's your name?");
        String answer = scanner.next();

        System.out.println("What is the book you are looking for? ");
        String bookTitle = scanner.next();


        // Here we have to compare the input to the record in the library.
        // if bookTitle = Books.title {
        // System.out.println("Prints out the book info page" + number of copies + availability);
        // } else {
        // System.out.println("Can you name an author of the book");
        // String authorsName = scanner.next();
        // authorsName = author.id;
        // then System.out.println("These are the books by authorId");
        // make a loop to print out an array of book titles;
        // here could come in branching of whether the client find any of these titles interesting
        // default System.out.println("Sorry, we do not shelve this book.");
        // else System.out.println("Sorry, we do not shelve this book.");


    }

    private static void printMenu() {
        {
            Scanner sc = new Scanner(System.in); //monitors console for an input

            System.out.println("Welcome to the Rustic Library");
            System.out.println("Would you like to see a catalog? ");
            System.out.println("1: See the catalog.");
            System.out.println("0: Exit");

            int inputSelection = sc.nextInt();

            switch (inputSelection) {
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

    }
}

