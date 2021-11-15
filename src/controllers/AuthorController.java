package controllers;

import java.sql.*;
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
}