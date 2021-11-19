package controllers;

import menu.MainMenu;
import objects.Books;

import java.awt.print.Book;
import java.sql.*;
import java.util.Scanner;

public class BooksController {
    private static Scanner scanner = new Scanner(System.in);

    public static int addNewBook(Statement statement, Connection connection) {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();

        System.out.print("Enter 13 digit ISBN number of the book: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter author ID: ");
        String authorID = scanner.nextLine();

        System.out.print("Enter the year the book was published (yyyy): ");
        String yearPublished = scanner.nextLine();

        System.out.print("Enter the language if book is not written in English: ");
        String language = scanner.nextLine();

        System.out.print("Enter the number of pages: ");
        String pages = scanner.nextLine();

        System.out.print("Enter the genre of the book (optional): ");
        String genre = scanner.nextLine();

        System.out.print("Enter the publisher (optional): ");
        String publisher = scanner.nextLine();

        System.out.print("Enter the edition of the book (optional): ");
        String edition = scanner.nextLine();

        System.out.print("Is book a hardcover (true / false): "); //TODO - return to this and check how this connects to constructor / database
        boolean hasHardCover = scanner.nextBoolean();

        System.out.print("Information about the book: ");
        String information = scanner.nextLine();

        System.out.print("Enter the clientID (optional) : ");
        String clientID = scanner.nextLine();

        System.out.print("Enter the orderID (optional) : ");
        String orderID = scanner.nextLine();

        try {
            statement = MainMenu.helper.getStatment();
            statement.execute(
                    "INSERT INTO Books (title, isbn, authorID, yearPublished, language, pages, genre, publisher, edition,  hasHardCover, information, clientID, orderID) " +
                            "VALUES( '" + title + "', '" + isbn + "' , '" + authorID + "' , '" + yearPublished + "', '" + language + "' , '" + pages + "' , '" + genre + "', '" + publisher + "' , '" + edition + "' , '" + hasHardCover + "' , '" + information + "', '" + clientID + "' , '" + orderID + "');");

            String queryLastRowInserted = "SELECT last_insert_rowid() AS id;";
            statement.execute(queryLastRowInserted);
            ResultSet rs = statement.getResultSet();
            rs.next();
            int generatedID = rs.getInt("id");

            System.out.println("Successfully added new book \"" + title + "\" (ISBN" + isbn + "), book ID is " + generatedID);
            return generatedID;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Failed to add new book, please try again.");
            return -1;
        }

//
//        public static Books getBooks(Statement statement, int id) {
//            try {
//                statement.execute("SELECT * FROM Books WHERE id = " + id + ";");
//                ResultSet rs = statement.getResultSet();
//                rs.next();
//
//                Books output = new Book(id);
//                int entryId = rs.getInt("id");
//                output.title = rs.getString("title");
//                output.isbn = rs.getString("isbn");
//                output.authorID = rs.getInt("authorID");
//                output.yearPublished = rs.getInt("yearPublished");
//                output.language = rs.getString("language");
//                output.pages = rs.getInt("pages");
//                output.genre = rs.getString("genre");
//                output.publisher = rs.getString("publisher");
//                output.edition = rs.getString("edition");
//                output.hasHardCover = rs.getInt("hasHardCover") == 1;
        //        output.information = rs.getString("information");
//                output.clientID = rs.getString("clientID");
//                output.orderID = rs.getString("orderID");
//
//                EntryDatabase.selectEntry(statement, id, output);
//
//                return output;
//            } catch (SQLException exception) {
//                System.out.println("This was not possible to retrieve.");
//            }
//            return null;
//        }

    }
}


