package controllers;

import com.sun.tools.javac.Main;
import menu.MainMenu;
import util.DBHelper;

import menu.AuthorsMenu;
import objects.Authors;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class AuthorController {
    final private static Scanner scanner = new Scanner(System.in);

    public static int addNewAuthor() {
        System.out.print("Enter the name of the author: ");
        String authorName = scanner.nextLine();

        System.out.print("Enter author's date of birth (yyyy-MM-dd): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Enter author's date of death (dd/MM/yyyy, optional): ");
        String dateOfDeath = scanner.nextLine();

        System.out.print("Enter any additional information about author (optional): ");
        String authorInfo = scanner.nextLine();

        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "INSERT INTO Authors (authorName, dateOfBirth, dateOfDeath, authorInfo) " +
                            "VALUES( '" + authorName + "', '" + dateOfBirth + "' , '" + dateOfDeath + "' , '" + authorInfo + "');");

            String queryLastRowInserted = "SELECT last_insert_rowid() AS authorID;";
            statement.execute(queryLastRowInserted);
            ResultSet rs = statement.getResultSet();
            rs.next();
            int generatedID = rs.getInt("authorID");

            System.out.println("Successfully added new author " + authorName + " with ID: " + generatedID);
            statement.close();
            AuthorController.execute();
            return generatedID;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Failed to add new author. Try again.");
            return -1;
        }
    }


    public static boolean deleteAuthor() {

        int id = findAuthorById().getAuthorID();

        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "DELETE FROM authors WHERE authorID = " + id);
            System.out.println("Author with id " + id + " is successfully removed from database");
            statement.close();
            AuthorController.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean updateAuthor() {

        Scanner scanner = new Scanner(System.in);
        int id = findAuthorById().getAuthorID();

        System.out.println("");
        System.out.println("What information do you want to change?");
        System.out.println("Please, print:  \n ---> 1 for NAME; \n ---> 2 for DATE OF BIRTH; \n ---> 3 for DATE OF BIRTH \n ---> 4 for INFORMATION");
        int column = scanner.nextInt();
        scanner.nextLine();
        System.out.println("");
        System.out.println("Enter new information: ");
        String info = scanner.nextLine().trim();
        System.out.println("");
        try {
            Statement statement = MainMenu.helper.getStatment();

            if (column == 1) {
                statement.execute("UPDATE Authors SET authorName = \"" + info + "\" WHERE authorID = " + id + ";");

            } else if (column == 2) {
                System.out.println("Use date format: yyyy-MM-dd");
                statement.execute("UPDATE Authors SET dateOfBirth = \"" + info + "\" WHERE authorID = " + id + ";");

            } else if (column == 3) {
                System.out.println("Use date format: yyyy-MM-dd");
                statement.execute("UPDATE Authors SET dateOfDeath = \"" + info + "\" WHERE authorID = " + id + ";");

            } else if (column == 4) {
                statement.execute("UPDATE authors SET authorInfo = \"" + info + "\" WHERE authorID = " + id + ";");
            } else {
                System.out.println("Update failed. Please check data and try again.");
            }

            System.out.println("Successfully updated! Information added: " + info);
            statement.close();
            AuthorController.execute();
            return true;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }


    public static Authors findAuthorById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the author: ");
        int id = scanner.nextInt();
        System.out.println("");

        try {
            Statement statement = MainMenu.helper.getStatment();

            statement.execute(
                    "SELECT * FROM authors WHERE authorID =" + id);

            int authorID;
            String authorName, authorInfo;
            Date dateOfBirth, dateOfDeath;

            Authors author = new Authors();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                authorID = rs.getInt("authorID");
                authorName = rs.getString("authorName");
                dateOfBirth = formatter.parse(rs.getString("dateOfBirth"));
                dateOfDeath = formatter.parse(rs.getString("dateOfDeath"));
                authorInfo = rs.getString("authorInfo");

                System.out.println("Author ID: " + authorID + "\n" + "Name: " + authorName + "\n" + "Date of birth: " + dateOfBirth + "\n" + "Date of death:" + dateOfDeath + "\n" + "Information: " + authorInfo);

            }
            return author;
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
            System.out.println("Failed to add new author. Try again.");
            return null;
        }
    }

    public static Authors findAuthorByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the author: ");
        String name = scanner.nextLine().trim();
        System.out.println("");

        try {
            Statement statement = MainMenu.helper.getStatment();

            statement.execute(
                    "SELECT * FROM authors WHERE authorName = \"" + name + "\";");

            int authorID;
            String authorName, authorInfo;
            Date dateOfBirth, dateOfDeath;

            Authors author = new Authors();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                authorID = rs.getInt("authorID");
                authorName = rs.getString("authorName");
                dateOfBirth = formatter.parse(rs.getString("dateOfBirth"));
                dateOfDeath = formatter.parse(rs.getString("dateOfDeath"));
                authorInfo = rs.getString("authorInfo");

                System.out.println("Author ID: " + authorID + "\n" + "Name: " + authorName + "\n" + "Date of birth: " + dateOfBirth + "\n" + "Date of death:" + dateOfDeath + "\n" + "Information: " + authorInfo);
                statement.close();

            }
            AuthorController.execute();
            return author;

        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
            System.out.println("Failed to add new author. Try again.");
            return null;
        }
    }

    public static String printAllAuthors() {
        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute("SELECT * FROM authors;");
            int authorID;
            String authorName, authorInfo;
            Date dateOfBirth, dateOfDeath;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                authorID = rs.getInt("authorID");
                authorName = rs.getString("authorName");
                dateOfBirth = formatter.parse(rs.getString("dateOfBirth"));
                dateOfDeath = formatter.parse(rs.getString("dateOfDeath"));
                authorInfo = rs.getString("authorInfo");
                System.out.println("Author id: " + authorID + ";\nName: " + authorName + ";\nDate of birth: " + dateOfBirth + ";\nDate of death: " + dateOfDeath + ";\nInformation: " + authorInfo + ";\n\n*************************\n");
            }
            statement.close();
            AuthorController.execute();
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void execute() {
        System.out.print("\nIf you want to go back to menu print: y ------>  ");
        String answer = scanner.next();
        if (answer.equals("y")) {
            AuthorsMenu.menu();
        }
    }
}
