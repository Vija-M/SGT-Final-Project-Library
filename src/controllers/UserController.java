package controllers;


import menu.MainMenu;
import menu.UsersMenu;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            UserController.execute();
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
            UserController.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateUser() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the user: ");
        int id = scanner.nextInt();

        System.out.println("");
        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "SELECT * FROM users WHERE userID =" + id);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = statement.getResultSet();

            int userID = rs.getInt("userID");
            String userFirstName = rs.getString("userFirstName");
            String userLastName = rs.getString("userLastName");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            java.util.Date birthDate = formatter.parse(rs.getString("birthDate"));
            String address = rs.getString("address");
            String city = rs.getString("city");
            String country = rs.getString("country");
            String postalCode = rs.getString("postalCode");
            String userHistory = rs.getString("userHistory");

            System.out.println("User ID: " + userID + "\n" + "Name: " + userFirstName + "\n" + "Family name: " + userLastName + "\n" + "E-mail: " + email +
                    "\n" + "Phone: " + phone + "\n" + "Date of birth: " + birthDate + "\n" + "Address: " + address + "\n"  + "City: " + city + "\n" +
                    "Country: " + country + "\n" + "PostalCode: " + postalCode + "\n"+ "User history: " + userHistory);

            System.out.println("What information do you want to change?");
            System.out.println("Please, print:  \n ---> 1 for NAME; \n ---> 2 for FAMILY NAME; \n ---> 3 for E-MAIL; \n ---> 4 for PHONE Nr.; \n ---> 5 for DATE OF BIRTH;" +
                    " \n ---> 6 for ADDRESS; \n ---> 7 for CITY; \n ---> 8 for COUNTRY; \n ---> 9 for POSTAL CODE; \n ---> 10 for INFORMATION");
            int column = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");
            System.out.println("Enter new information: ");
            String info = scanner.nextLine().trim();
            System.out.println("");


            if (column == 1) {
                statement.execute("UPDATE Users SET userFirstName = \"" + info + "\" WHERE userID = " + id + ";");

            } else if (column == 2) {
                    statement.execute("UPDATE Users SET userLastName = \"" + info + "\" WHERE userID = " + id + ";");

            } else if (column == 3) {
                statement.execute("UPDATE Users SET email = \"" + info + "\" WHERE userID = " + id + ";");

            } else if (column == 4) {
                statement.execute("UPDATE Users SET phone = \"" + info + "\" WHERE userID = " + id + ";");

            } else if (column == 5) {
                System.out.println("Use date format: yyyy-MM-dd");
                statement.execute("UPDATE Users SET birthDate = \"" + info + "\" WHERE userID = " + id + ";");

            } else if (column == 6) {
                statement.execute("UPDATE Users SET address = \"" + info + "\" WHERE userID = " + id + ";");

            } else if (column == 7) {

                statement.execute("UPDATE Authors SET city = \"" + info + "\" WHERE authorID = " + id + ";");

            } else if (column == 8) {
                statement.execute("UPDATE Authors SET country = \"" + info + "\" WHERE authorID = " + id + ";");

            } else if (column == 9) {
                statement.execute("UPDATE authors SET postalCode = \"" + info + "\" WHERE authorID = " + id + ";");

            } else if (column == 10) {
                statement.execute("UPDATE authors SET userHistory = \"" + info + "\" WHERE authorID = " + id + ";");

            } else {
                System.out.println("Update failed. Please check data and try again.");
            }

            System.out.println("Successfully updated! Information added: " + info);

            UserController.execute();
            return true;


        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }




    public static void execute() {
        System.out.print("\nIf you want to go back to menu print: y ------>  ");
        String answer = scanner.next();
        if (answer.equals("y")) {
            UsersMenu.menu();
        }
    }
}
