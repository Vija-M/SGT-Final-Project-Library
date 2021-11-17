package controllers;

import objects.Authors;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class AuthorController {
    private static Scanner scanner = new Scanner(System.in);

    public static int addNewAuthor() {
        System.out.print("Enter the name of the author: ");
        String authorName = scanner.nextLine();

        System.out.print("Enter author's date of birth (dd/MM/yyyy): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Enter author's date of death (dd/MM/yyyy, optional): ");
        String dateOfDeath = scanner.nextLine();

        System.out.print("Enter any additional information about author (optional): ");
        String authorInfo = scanner.nextLine();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/37126/SQLITE3/Library.db");
            Statement statement = connection.createStatement();

            statement.execute(
                    "INSERT INTO Authors (authorName, dateOfBirth, dateOfDeath, authorInfo) " +
                            "VALUES( '" + authorName + "', '" + dateOfBirth + "' , '" + dateOfDeath + "' , '" + authorInfo + "');");

            String queryLastRowInserted = "SELECT last_insert_rowid() AS authorID;";
            statement.execute(queryLastRowInserted);
            ResultSet rs = statement.getResultSet();
            rs.next();
            int generatedID = rs.getInt("authorID");

            statement.close();
            connection.close();
            System.out.println("Successfully added new author " + authorName + "with ID:" + generatedID);
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
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/37126/SQLITE3/Library.db");
            Statement statement = connection.createStatement();

            statement.execute(
                    "DELETE FROM students WHERE id = " + id);

            statement.close();
            connection.close();
            System.out.println("Author with id " + id + " is successfully removed from database");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Authors findAuthorById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the author: ");
        int id = scanner.nextInt();
        System.out.println("");

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/37126/SQLITE3/Library.db");
            Statement statement = connection.createStatement();

            statement.execute(
                    "SELECT * FROM authors WHERE authorID =" + id);

            int authorID;
            String authorName, authorInfo;
            Date dateOfBirth, dateOfDeath;

            Authors author = new Authors();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                authorID = rs.getInt("authorID");
                authorName = rs.getString("authorName");
                dateOfBirth = rs.getDate("dateOfBirth", Calendar.getInstance());
                dateOfDeath = rs.getDate("dateOfDeath", Calendar.getInstance());
                authorInfo = rs.getString("authorInfo");

                System.out.println(authorID + "\t" + authorName + "\t" + dateOfBirth + "\t" + dateOfDeath + "\t" + authorInfo);
                statement.close();
                connection.close();
            }
            return author;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Failed to add new author. Try again.");
            return null;
        }
    }

}