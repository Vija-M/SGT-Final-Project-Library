package menu;

import java.util.Scanner;

import objects.Books;

public class PrintMenu {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); //monitors console for an input

        System.out.println("********************Welcome to the Rustic Library!********************");
        System.out.println();
        System.out.println("Please enter your clientID");
        String clientID = scanner.next();

        // 1) retrieve info from the database about this user.
        // 2) Print it out.

    }

    private static void printMenu() {
        {
            Scanner sc = new Scanner(System.in); //monitors console for an input

            System.out.println("Welcome to the Rustic Library!");
            System.out.println("How can we help?");
            System.out.println();
            System.out.println("1: See the catalog.");
            System.out.println("2: Search the book by title.");
            System.out.println("3: Search the book by an author.");
            System.out.println("4: Search the book by its ISBN number.");
            System.out.println("0: Exit");
            System.out.println();

            int inputSelection = sc.nextInt();

            switch (inputSelection) {
                case 1:
                    System.out.println("Catalog: Rustic Library");
                    // ... leads to the catalog, I guess ....
                    break;
                case 2:
                    System.out.println("Please enter the title of the book.");
                    String titleBook = scanner.next();
                        /* 1) Compare info with the database records.

                              if bookTitle = Books.title {
                              System.out.println("Prints out the book info page" + number of copies + availability);
                              } else {
                              System.out.println("The library does not shelf a book with this title, would you like to search by an author?");

                           2) boolean yes/no -> yes: return to the menu; no: exit switch
                         */
                    break;
                case 3:
                    System.out.println("Please enter full author's name.");
                    String authorName = scanner.next();
                    /* 1) Compare the information with that in the database
                          Something like this:

                       int i = 0; //that's the row number

                       while(authorName == authorID) {
                       System.out.println(book information);
                       ++i;
                       }
                     */

                    // 2) then System.out.println("These are the books by authorId");
                    // 3) Create an array by this author's books.
                    // 4) print out the results
                    // 5) Make a selection case with another switch.
                    break;
                case 4:
                    System.out.println("Please enter the ISBN number of the book.");
                    String isbnNumber = scanner.next();
                    /* 1) Compare the information with that in the database
                          Something like this:
                       int i = 0; //that's the row number

                       while(isbnNumber == isbn) {
                       System.out.println(book information);
                       ++i;
                       }

                    2) Print out book information or information about its absence.
                     */
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