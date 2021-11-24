package menu;

import util.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class LibraryCollection {

    static Scanner scan = new Scanner(System.in);
    //public static DBHelper helper;

    public static void loadClientInfo() {

        System.out.println("Please, enter your client identification number.");
        int userID = scan.nextInt();

        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute("SELECT * FROM Users WHERE userID = " + userID + ";");
            ResultSet rs = statement.getResultSet();

            // PreparedStatement clientInfo = helper.connection.prepareStatement("SELECT userFirstName, userLastName, userHistory FROM Users WHERE Users.userID = " + userID + ";");
            // ResultSet rs = clientInfo.executeQuery();

            String clientName = rs.getString("userFirstName");
            String clientSurname = rs.getString("userLastName");
            String clientHistory = rs.getString("userHistory");

            System.out.println("Welcome " + clientName + " " + clientSurname + "\n"
                    + "Your client history is: " + clientHistory + "\n");

            // statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("The account has not been found.");
        }
        MainMenu.clientMenu();
    }

    public static String libraryCollection() {
        {
            try {
                Statement statement = MainMenu.helper.getStatment();
                statement.execute("SELECT * FROM Books INNER JOIN Authors ON Books.authorID = Authors.authorID;");
                ResultSet rs = statement.getResultSet();

                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ". "
                            + rs.getString("authorName")
                            + " (" + rs.getInt("yearPublished") + ".) '"
                            + rs.getString("title") + ".' "
                            + rs.getString("publisher") + "." + "\n"
                            + "[in " + rs.getString("language") + "] " + "\n");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }

    public static void searchBook() {

        System.out.println("Please, enter the title of the book you are looking for.");
        String title = scan.nextLine();

        try {
            PreparedStatement bookInformation = MainMenu.helper.connection.prepareStatement("SELECT  Books.title, Books.authorID, Books.yearPublished, Books.publisher, Books.edition, Books.orderID, Authors.authorName FROM Books INNER JOIN Authors ON Books.authorID = Authors.authorID WHERE Books.title = " + "'" + title + "';");
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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("The book with this title has not been found.");
        }
        MainMenu.clientMenu();
    }

    public static void borrowBook() {
        int orderID = 1;
        System.out.println("Please, enter the title of a book you would like to borrow.");
        String title = scan.nextLine();

        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute("UPDATE Books SET orderID = " + orderID + " WHERE title = '" + title + "';");
            ResultSet rs = statement.getResultSet();

            System.out.println("You've successfully borrowed the book titled '" + title + "'." + "\n"
                    + "Thank you for using the library." + "\n");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Your attempt to borrow the book has failed.");
        }
        MainMenu.clientMenu();
    }

    public static void returnBook() {
        int orderID = 0;
        System.out.println("Please, enter the title of the book you are returning.");
        String title = scan.nextLine();


        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute("UPDATE Books SET orderID = " + orderID + " WHERE Books.title = '" + title + "' ;");
            ResultSet rs = statement.getResultSet();

            System.out.println("You've successfully returned the book. \n"
                    + "Thank you for using the library." + "\n");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Your attempt to return the book has failed.");
        }
        MainMenu.clientMenu();
    }
}