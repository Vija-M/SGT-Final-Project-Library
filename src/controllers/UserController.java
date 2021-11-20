package controllers;

import menu.MainMenu;

import java.sql.*;
import java.util.Scanner;

public class UserController {
    final private static Scanner scanner = new Scanner(System.in);

    public static int addNewUser() {
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
            Statement statement = MainMenu.helper.getStatment();
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


    public static boolean deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the user: ");
        int id = scanner.nextInt();
        System.out.println("");
        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "SELECT * FROM users WHERE userID =" + id);
            ResultSet rs = statement.getResultSet();
            String userFirstName = rs.getNString("userFirstName");
            String userLastName = rs.getString("userLastName");
            statement.execute(
                    "DELETE FROM users WHERE userID = " + id);
            System.out.println("A user " + userFirstName + userLastName + " with id: " + id + " is successfully removed from database.");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
