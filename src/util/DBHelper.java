package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

    private Statement statement;
    private Connection connection;

    public DBHelper() {
        try {
            connection = DriverManager.getConnection(
                    // !!! Change to your db location !!!//
                    "jdbc:sqlite:/Users/Everita/IdeaProjects/SGT-Final-Project-Library/sql/library.db");
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