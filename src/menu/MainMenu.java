package menu;

import objects.Books;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class MainMenu {
    static String fileName = null;
    static Collection collection = new Collection();
    static Scanner scan = new Scanner(System.in);
    static Boolean running = true;


    public static void main(String[] args) {


        System.out.println("Welcome to the Library!");
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
                    + "Enter 2 to display full library collection" + "\n"
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
                    searchBook();
                    break;

                case 3:
                    returnBook();
                    break;
            }
        }
        System.exit(0);
    }

    private static void searchBook() {
        //... code to come
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
            // clientInfo.setString(0, clientID);

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