package controllers;

import menu.MainMenu;
import objects.Books;

import java.sql.*;
import java.util.Scanner;

public class BooksController {
    static Scanner scanner = new Scanner(System.in);

    public static int addNewBook() {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();

        System.out.print("Enter 13 digit ISBN number of the book: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter authors ID: ");
        int authorID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the year the book was published (yyyy): ");
        int yearPublished = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the language if book is not written in English: ");
        String language = scanner.nextLine();

        System.out.print("Enter the number of pages: ");
        int pages = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the genre of the book (optional): ");
        String genre = scanner.nextLine();

        System.out.print("Enter the publisher (optional): ");
        String publisher = scanner.nextLine();

        System.out.print("Enter the edition of the book (optional): ");
        String edition = scanner.nextLine();

        System.out.print("Is book a hardcover (true / false): "); //TODO - return to this and check how this connects to constructor / database
        boolean hasHardCover = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Information about the book: ");
        String information = scanner.nextLine();

        System.out.print("Enter the userID (optional) : ");
        int userID = scanner.nextInt();

        System.out.print("Enter the orderID (optional) : ");
        int orderID = scanner.nextInt();

        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "INSERT INTO Books (title, isbn, authorID, yearPublished, language, pages, genre, publisher, edition,  hasHardCover, information, userID, orderID) " +
                            "VALUES( '" + title + "', '" + isbn + "' , '" + authorID + "' , '" + yearPublished + "', '" + language + "' , '" + pages + "' , '" + genre + "', '" + publisher + "' , '" + edition + "' , '" + hasHardCover + "' , '" + information + "', '" + userID + "' , '" + orderID + "'");

            String queryLastRowInserted = "SELECT last_insert_rowid() AS id;";
            statement.execute(queryLastRowInserted);
            ResultSet rs = statement.getResultSet();
            rs.next();
            int generatedID = rs.getInt("id");

            System.out.println("Successfully added new book \"" + title + "\" (ISBN" + isbn + "), book ID is " + generatedID);
            return generatedID;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Failed to add new book, please try again!");
            return -1;
        }
    }

    public static boolean deleteBook() {
        System.out.println("Enter the ID of the book: ");
        int id = scanner.nextInt();
        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "SELECT * FROM Books WHERE id =" + id);
            ResultSet rs = statement.getResultSet();
            String bookName = rs.getString("title");
            System.out.println("Book you want to delete is " + bookName);
            statement.execute(
                    "DELETE FROM Books WHERE id = " + id);
            System.out.println("A book " + bookName + " with id: " + id + " is successfully removed from database.");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Books findBookById() {
        System.out.println("Enter the ID of the book: ");
        int bookID = scanner.nextInt();
        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "SELECT * FROM Books WHERE id =" + bookID);

            Books book = new Books();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String isbn = rs.getString("isbn");
                int authorID = rs.getInt("authorID");
                int yearPublished = rs.getInt("yearPublished");
                String language = rs.getString("language");
                int pages = rs.getInt("pages");
                String genre = rs.getString("genre");
                String publisher = rs.getString("publisher");
                String edition = rs.getString("edition");
                boolean hasHardCover = rs.getBoolean("hasHardCover");
                String information = rs.getString("information");
                int userID = rs.getInt("userID");
                int orderID = rs.getInt("orderID");
                System.out.println("Book ID: " + id + "\n" + "Title: " + title + "\n" + "ISBN: " + isbn + "\n" + "AuthorID:" + authorID +
                        "\n" + "Year published: " + yearPublished + "Language: " + language + "\n" + "Number of pages: " + pages +
                        "\n" + "Genre: " + genre + "\n" + "Publisher: " + publisher + "Edition: " + edition + "\n" + "Hardcover: " + hasHardCover +
                        "\n" + "Information: " + information + "\n" + "UserID: " + userID + "\n" + "OrderID:" + orderID);
            }
            return book;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Didn't find a book. Please try again!");
            return null;
        }
    }

    public static Books findBookByTitle() {
        System.out.println("Enter the title of the book: ");
        String booksTitle = scanner.nextLine().trim();

        try {
            Statement statement = MainMenu.helper.getStatment();

            statement.execute(
                    "SELECT * FROM Books WHERE title LIKE '%" + booksTitle + "%'");

            Books book = new Books();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String isbn = rs.getString("isbn");
                int authorID = rs.getInt("authorID");
                int yearPublished = rs.getInt("yearPublished");
                String language = rs.getString("language");
                int pages = rs.getInt("pages");
                String genre = rs.getString("genre");
                String publisher = rs.getString("publisher");
                String edition = rs.getString("edition");
                boolean hasHardCover = rs.getBoolean("hasHardCover");
                String information = rs.getString("information");
                int userID = rs.getInt("userID");
                int orderID = rs.getInt("orderID");
                System.out.println("Book ID: " + id + "\n" + "Title: " + title + "\n" + "ISBN: " + isbn + "\n" + "AuthorID:" + authorID +
                        "\n" + "Year published: " + yearPublished + "Language: " + language + "\n" + "Number of pages: " + pages +
                        "\n" + "Genre: " + genre + "\n" + "Publisher: " + publisher + "Edition: " + edition + "\n" + "Hardcover: " + hasHardCover +
                        "\n" + "Information: " + information + "\n" + "UserID: " + userID + "\n" + "OrderID:" + orderID);
            }
            return book;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Didn't find a book. Please try again!");
            return null;
        }
    }

    public static boolean updateBook() {
        System.out.println("Enter the ID of the book: ");
        int id = scanner.nextInt();
        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "SELECT * FROM Books WHERE id =" + id);

            ResultSet rs = statement.getResultSet();

            id = rs.getInt("id");
            String title = rs.getString("title");
            String isbn = rs.getString("isbn");
            int authorID = rs.getInt("authorID");
            int yearPublished = rs.getInt("yearPublished");
            String language = rs.getString("language");
            int pages = rs.getInt("pages");
            String genre = rs.getString("genre");
            String publisher = rs.getString("publisher");
            String edition = rs.getString("edition");
            boolean hasHardCover = rs.getBoolean("hasHardCover");
            String information = rs.getString("information");
            int userID = rs.getInt("userID");
            int orderID = rs.getInt("orderID");

            System.out.println("Book ID: " + id + "\n" + "Title: " + title + "\n" + "ISBN: " + isbn + "\n" + "AuthorID:" + authorID +
                    "\n" + "Year published: " + yearPublished + "Language: " + language + "\n" + "Number of pages: " + pages +
                    "\n" + "Genre: " + genre + "\n" + "Publisher: " + publisher + "Edition: " + edition + "\n" + "Hardcover: " + hasHardCover +
                    "\n" + "Information: " + information + "\n" + "UserID: " + userID + "\n" + "OrderID:" + orderID);

            System.out.println("What information do you want to change?");
            System.out.println("Please, print:  " +
                    "\n ---> 1 for Title; " +
                    "\n ---> 2 for ISBN; " +
                    "\n ---> 3 for AuthorID; " +
                    "\n ---> 4 for Year published; " +
                    "\n ---> 5 for Language; " +
                    "\n ---> 6 for Number of pages " +
                    "\n ---> 7 for Genre; " +
                    "\n ---> 8 for Publisher; " +
                    "\n ---> 9 for Edition;" +
                    "\n ---> 10 for Hardcover; " +
                    "\n ---> 11 for Information; " +
                    "\n ---> 12 for UserID; " +
                    "\n ---> 13 for OrderID; " +
                    "\n ---> 0 return to Books menu");

            int column = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new information: ");
            String info = scanner.nextLine().trim();


            if (column == 1) {
                statement.execute("UPDATE Books SET title = \"" + info + "\" WHERE title = " + id + ";");

            } else if (column == 2) {
                statement.execute("UPDATE Books SET isbn = \"" + info + "\" WHERE isbn = " + id + ";");

            } else if (column == 3) {
                statement.execute("UPDATE Books SET authorID = \"" + info + "\" WHERE authorID = " + id + ";");

            } else if (column == 4) {
                statement.execute("UPDATE Books SET yearPublished = \"" + info + "\" WHERE yearPublished = " + id + ";");

            } else if (column == 5) {
                statement.execute("UPDATE Books SET language = \"" + info + "\" WHERE language = " + id + ";");

            } else if (column == 6) {
                statement.execute("UPDATE Books SET pages = \"" + info + "\" WHERE pages = " + id + ";");

            } else if (column == 7) {
                statement.execute("UPDATE Books SET genre = \"" + info + "\" WHERE genre = " + id + ";");
            } else if (column == 8) {
                statement.execute("UPDATE Books SET publisher = \"" + info + "\" WHERE publisher = " + id + ";");

            } else if (column == 9) {
                statement.execute("UPDATE Books SET edition = \"" + info + "\" WHERE edition = " + id + ";");

            } else if (column == 10) {
                statement.execute("UPDATE Books SET hasHardCover = \"" + info + "\" WHERE hasHardCover = " + id + ";");

            } else if (column == 11) {
                statement.execute("UPDATE Books SET information = \"" + info + "\" WHERE information = " + id + ";");

            } else if (column == 12) {
                statement.execute("UPDATE Books SET userID = \"" + info + "\" WHERE userID = " + id + ";");

            } else if (column == 13) {
                statement.execute("UPDATE Books SET orderID = \"" + info + "\" WHERE orderID = " + id + ";");

            } else {
                System.out.println("Update failed. Please check data and try again.");
                updateBook();
            }

            System.out.println("Successfully updated! Information added: " + info);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static String printAllBooks(){
        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute("SELECT * FROM Books;");
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String isbn = rs.getString("isbn");
                int authorID = rs.getInt("authorID");
                int yearPublished = rs.getInt("yearPublished");
                String language = rs.getString("language");
                int pages = rs.getInt("pages");
                String genre = rs.getString("genre");
                String publisher = rs.getString("publisher");
                String edition = rs.getString("edition");
                boolean hasHardCover = rs.getBoolean("hasHardCover");
                String information = rs.getString("information");
                int userID = rs.getInt("userID");
                int orderID = rs.getInt("orderID");

                System.out.println("Book ID: " + id + "\n" + "Title: " + title + "\n" + "ISBN: " + isbn + "\n" + "AuthorID:" + authorID +
                        "\n" + "Year published: " + yearPublished + "Language: " + language + "\n" + "Number of pages: " + pages +
                        "\n" + "Genre: " + genre + "\n" + "Publisher: " + publisher + "Edition: " + edition + "\n" + "Hardcover: " + hasHardCover +
                        "\n" + "Information: " + information + "\n" + "UserID: " + userID + "\n" + "OrderID:" + orderID + "\n*************************\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}














