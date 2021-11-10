package controllers;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class AuthorController {
    private static Scanner scanner = new Scanner(System.in);

    public static boolean addNewAuthor() {
        System.out.println("Enter the name of the author: ");
        String authorName = scanner.nextLine();

        System.out.println("Enter author's date of birth (dd/MM/yyyy): ");
        String dateOfBirth = scanner.nextLine();

        System.out.println("Enter author's date of death (dd/MM/yyyy, optional): ");
        String dateOfDeath = scanner.nextLine();

        System.out.print("Enter any additional information about author (optional): ");
        String authorInfo = scanner.nextLine();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/37126/SQLITE3/Library.db");
            Statement statement = connection.createStatement();

            String queryLastRowInserted = "SELECT last_insert_rowid() AS authorID;";
            statement.execute(queryLastRowInserted);
            ResultSet rs = statement.getResultSet();
            rs.next();
            int generatedID = rs.getInt("authorID");
            statement.execute(
                    "INSERT INTO Authors (authorName, dateOfBirth, dateOfDeath, authorInfo) " +
                            "VALUES( '" + authorName + "', '" + dateOfBirth + "' , '" + dateOfDeath + "' , '" + authorInfo + "');");
            statement.close();
            System.out.println("Successfully added new author " + authorName);

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Failed to add new doctor. Try again.");
            return false;
        }
    }
}

