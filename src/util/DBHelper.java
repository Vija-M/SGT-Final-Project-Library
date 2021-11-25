package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

    public Statement statement;
    public Connection connection;

    public DBHelper() {
        try {
            connection = DriverManager.getConnection(
                    // !!! CHANGE TO YOUR DB LOCATION !!!//
                    //"jdbc:sqlite:/Users/Everita/IdeaProjects/SGT-Final-Project-Library/sql/library.db");
            "jdbc:sqlite:/Users/37126/IdeaProjects/SGT-Final-Project-Library/sql/Library.db");
            // Ance - "jdbc:sqlite:F:/javaProjects/SGT-Final-Project-Library/sql/Library.db"

            statement = connection.createStatement();
        } catch (
                SQLException exception) {
            System.out.println("There was an issue when creating a connection");
        }
    }

    public Statement getStatment() {
        try {
            statement = this.connection.createStatement();
            return statement;
        } catch (
                SQLException exception) {
            System.out.println("There was an issue when creating a statement");
        }
        return null;
    }
}