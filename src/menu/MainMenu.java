package menu;

import objects.Books;
import util.DBHelper;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class MainMenu {
    static String fileName = null;
    static LibraryCollection collection = new LibraryCollection();
    static Scanner scan = new Scanner(System.in);
    static Boolean running = true;
    public static DBHelper helper;

    public void main(String[] args) {
        helper = new DBHelper();
        System.out.println("********************Welcome to the Rustic Library!********************");
        System.out.println();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter 1 if you are a client or 2 if you are a librarian.");
        if (scan.nextInt() == 2) {
            System.out.println("Please enter your userID.");
            String userID = scan.next();
            MainMenu.librarianMenu();
        } else {
            MainMenu.clientMenu();
        }
    }

    static void clientMenu() {
        while (running) { // i.e., while the application is running
            System.out.println("Enter 0 for entering your library account." + "\n"
                    + "Enter 1 to save and quit." + "\n"
                    + "Enter 2 to display full library collection and search for a book" + "\n"
                    + "Enter 3 to add a book to the library.");

            int clientResponse = scan.nextInt();

            switch (clientResponse) {
                case 0:
                    MainMenu.loadClientInfo();
                    break;

                case 1:
                    MainMenu.saveAndQuit();
                    break;

                case 2:
                    System.out.println(collection.toString());
                    MainMenu.searchBook();
                    break;

                case 3:
                    returnBook();
                    break;
            }
        }
        System.exit(0);
    }

    private static void searchBook() {

            System.out.println("Please, enter the title of the book you are looking for.");
            String title = scan.next();

            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:F:/javaProjects/SGT-Final-Project-Library/sql/library.db");
                Statement statement = connection.createStatement();
                statement.execute("SELECT * FROM Books JOIN Authors ON Books.authorID = Authors.authorID WHERE Books.title =" + " '" + title + "' ;");

                PreparedStatement bookInfo = connection.prepareStatement("SELECT authorID, yearPublished, publisher, edition, orderID, authorName FROM Books JOIN Authors WHERE Books.title = " + "'" + title + "';");
                ResultSet rs = statement.getResultSet();

                String authorID = rs.getString(1);
                String yearPublished = rs.getString(2);
                String publisher = rs.getString(3);
                String edition = rs.getString(4);
                int availability = rs.getInt(5); // 1 means the book is unavailable
                String authorName = rs.getString(6);

                if (availability != 1) {
                    System.out.println("Your book " + title + edition + " written by " + authorName + "\n"
                            + "(published in: " + yearPublished + " by " + publisher + ") " + "\n"
                            + " is shelved and available to borrow." + "\n");
                } else {
                    System.out.println("Your book " + title + edition + " written by " + authorName + "\n"
                            + "(published in: " + yearPublished + " by " + publisher + ") " + "\n"
                            + " is unavailable.");
                }

                statement.close();
                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("The book with this title has not been found.");

                System.exit(0);
            }
            }

        private static void returnBook() {
        int yearPublished;
        String author, title, isbn, publisher;

        System.out.println("Please, enter the author of the book: " + "\n");
        author = scan.next();

        System.out.println("Enter the title of the book: " + "\n");
        title = scan.next();

        System.out.println("What year has it been published?" + "\n");
        yearPublished = scan.nextInt();

        System.out.println("Please, enter the book's isbn" + "\n");
        isbn = scan.next();

        System.out.println("Enter the publisher. " + "\n");
        publisher = scan.next();

        // Creating a book object for this new book...
        Books newBookAdded = new Books(author, title, yearPublished, isbn, publisher);

        // ...and adding it to the library.
        collection.addBook(newBookAdded);
    }


    private static void saveAndQuit() {
        System.out.println("Enter file name: ");
        fileName = scan.next();
        running = false;

        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {

            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);

            out.writeObject(collection);
            fos.close();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void librarianMenu() {
        System.out.println("Welcome to the internal system of Rustic Library!");
        System.out.println("Please, choose one of the options below.");
        System.out.println();
        System.out.println("1--> choose action with BOOKS: ");
        System.out.println("2--> choose action with AUTHORS: ");
        System.out.println("3--> choose action with restaurant USERS: ");
        System.out.println("0--> EXIT! ");
        System.out.println();

        int inputSelection = scan.nextInt();

        switch (inputSelection) {
            case 1:
                //         BooksMenu.printMenu();
                break;
            case 2:
                AuthorsMenu.menu();
                break;
            case 3:
                UsersMenu.menu();
                break;
            case 0:
                return;
            default:
                System.out.println("Did not recognize this selection, please try again!");
                break;
        }
        System.exit(0);
    }

    private static void loadClientInfo() {

        System.out.println("Please, enter your client identification number.");
        String clientID = scan.next();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:F:/javaProjects/SGT-Final-Project-Library/sql/library.db");
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM Users WHERE userID = " + clientID + ";");

            PreparedStatement clientInfo = connection.prepareStatement("SELECT userFirstName, userLastName, userHistory FROM Users WHERE userID = " + clientID + ";");

            ResultSet rs = clientInfo.executeQuery();

            String clientName = rs.getString(1);
            String clientSurname = rs.getString(2);
            String clientHistory = rs.getString(3);

            System.out.println("Welcome " + clientName + " " + clientSurname + "\n"
                    + "Your client history is: " + clientHistory + "\n");

            statement.close();
            connection.close();
            System.exit(0);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("The account has not been found.");
        }

    }
}