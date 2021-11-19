package controllers;

import menu.MainMenu;

import java.sql.*;
import java.util.Scanner;

public class UserController {
    final private static Scanner scanner = new Scanner(System.in);

    public static int addNewUser(Connection connection, Statement statement) {
        System.out.print("Please, enter user's: ");
        System.out.print("first name: ");
        String userFirstName = scanner.nextLine();
        System.out.print("last name: ");
        String userLastName = scanner.nextLine();
        System.out.print("e-mail: ");
        String email = scanner.nextLine();
        System.out.print("phone number: ");
        String phone = scanner.nextLine();
        System.out.print("date of birth (dd/MM/yyyy): ");
        String birthDate = scanner.nextLine();
        System.out.print("address: ");
        String address = scanner.nextLine();
        System.out.print("city: ");
        String city = scanner.nextLine();
        System.out.print("country: ");
        String country = scanner.nextLine();
        System.out.print("postal code: ");
        String postalCode = scanner.nextLine();
        System.out.println("important information if there is any: ");
        String userHistory = scanner.nextLine();

        try {
            statement = MainMenu.helper.getStatment();
            statement.execute(
                    "INSERT INTO Users (userFirstName, userLastName, email, phone, birthDate, address, city, country, postalCode, userHistory) " +
                            "VALUES( '" + userFirstName + "', '" + userLastName + "' , '" + email + "' , '" + phone + "' , '" + birthDate + "' , '" + address +
                            "' , '" + city + "' , '" + country + "' , '" + postalCode + "' , '" + userHistory + "');");

            String queryLastRowInserted = "SELECT last_insert_rowid() AS userID;";
            statement.execute(queryLastRowInserted);
            ResultSet rs = statement.getResultSet();
            rs.next();
            int generatedID = rs.getInt("userID");

            System.out.println("Successfully added new user " + userFirstName + userLastName + "with ID:" + generatedID);
            return generatedID;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Failed to add new user. Try again.");

            return -1;
        }
    }
}
