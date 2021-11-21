package menu;

import controllers.AuthorController;
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
                    + "Enter 1 to search for a book." + "\n"
                    + "Enter 2 to return a book" + "\n"
                    + "Enter 3 to find an author by name" + "\n"
                    + "Enter 4 to print a list of all authors" + "\n"
                    + "Enter 5 to save and quit.");

            int clientResponse = scan.nextInt();

            switch (clientResponse) {
                case 0:
                    MainMenu.loadClientInfo();
                    break;

                case 1:
                    // System.out.println(collection.toString());
                    scan.nextLine();
                    MainMenu.searchBook();
                    break;

                case 2:
                    MainMenu.returnBook();
                    break;

                case 3:
                    AuthorController.findAuthorByName();
                    break;

                case 4:
                    AuthorController.printAllAuthors();
                    break;

                case 5:
                    MainMenu.saveAndQuit();
                    break;

            }
        }
        System.exit(0);
    }

    private static void loadClientInfo() {

        System.out.println("Please, enter your client identification number.");
        int userID = scan.nextInt();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:F:/javaProjects/SGT-Final-Project-Library/sql/Library.db");
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM Users WHERE userID = " + userID + ";");

            PreparedStatement clientInfo = connection.prepareStatement("SELECT userFirstName, userLastName, userHistory FROM Users WHERE userID = " + userID + ";");

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

    private static void searchBook() {

        System.out.println("Please, enter the title of the book you are looking for.");
        String title = scan.nextLine();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:F:/javaProjects/SGT-Final-Project-Library/sql/Library.db");
            Statement statement = connection.createStatement();

            PreparedStatement bookInformation = connection.prepareStatement("SELECT  Books.title, Books.authorID, Books.yearPublished, Books.publisher, Books.edition, Books.orderID, Authors.authorName FROM Books INNER JOIN Authors ON Books.authorID = Authors.authorID WHERE Books.title = " + "'" + title + "';");
            ResultSet rs = bookInformation.executeQuery();


            if (rs.getInt("orderID") != 1) {
                System.out.println("Your book '" + rs.getString("title") + "', the " + rs.getString("edition") + " edition, written by " + rs.getString("authorName") + "\n"
                        + "(published in: " + rs.getInt("yearPublished") + " by " + rs.getString("publisher") + ") " + "\n"
                        + "is shelved and available to borrow." + "\n");
            } else {
                System.out.println("Your book '" + rs.getString("title") + "', the " + rs.getString("edition") + " edition, written by " + rs.getString("authorName") + "\n"
                        + "(published in: " + rs.getInt("yearPublished") + " by " + rs.getString("publisher") + ") " + "\n"
                        + " is unavailable.");
            }

            statement.close();
            connection.close();
            System.exit(0);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("The book with this title has not been found.");

            System.exit(0);
        }
    }

    private static void returnBook() {
        System.out.println("Please, enter the title of the book you are returning.");
        String title = scan.nextLine();
        int orderID = 1;

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:F:/javaProjects/SGT-Final-Project-Library/sql/Library.db");
            Statement statement = connection.createStatement();
            statement.execute("SELECT Books.title, Books.authorID, Books.yearPublished, Books.publisher, Books.edition, Books.orderID, Authors.authorName FROM Books INNER JOIN Authors ON Books.authorID = Authors.authorID WHERE Books.title = " + "'" + title + "';");
            ResultSet rs = statement.getResultSet();

            statement.execute("UPDATE Books.orderID = \"" + orderID + "\" WHERE Books.title = " + "'" + title + "';");

                System.out.println("You've just returned a book by " + rs.getString("authorName") + " titled " + rs.getString("title") + "\n"
                        + "Thank you for using the library." + "\n");

            statement.close();
            connection.close();
            System.exit(0);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Your attempt to return the book has failed.");

            System.exit(0);
        }
    }


    private static void saveAndQuit() {
        System.out.println("Enter file name: ");

    }

    public static void librarianMenu() {
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
                BooksMenu.menu();
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

}