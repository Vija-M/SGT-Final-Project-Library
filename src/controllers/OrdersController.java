package controllers;

import menu.MainMenu;
import menu.OrdersMenu;
import objects.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OrdersController {
    static Scanner scanner = new Scanner(System.in);
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    public static int addNewOrder() {
        System.out.print("Enter the BookID: ");
        String bookID = scanner.nextLine();

        System.out.print("Enter the UserID: ");
        String userID = scanner.nextLine();

        System.out.print("Enter the issue date of the order (yyyy-MM-dd): ");
        String issueDate = scanner.nextLine();

        System.out.print("Enter the return date of the order(yyyy-MM-dd): ");
        String returnDate = scanner.nextLine();

        System.out.print("Enter any additional information about the order (optional): ");
        String orderInfo = scanner.nextLine();

        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "INSERT INTO Orders (bookID, userID, issueDate, returnDate, information) " +
                            "VALUES( '" + bookID + "','" + userID + "' , '" + issueDate + "' , '" + returnDate + "' , '" + orderInfo + "');");

            String queryLastRowInserted = "SELECT last_insert_rowid() AS orderID;";
            statement.execute(queryLastRowInserted);
            ResultSet rs = statement.getResultSet();
            rs.next();
            int generatedID = rs.getInt("orderID");

            System.out.println("Successfully added new order with ID: " + generatedID + " with return date " + returnDate);
            return generatedID;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Failed to add new author. Try again.");
            return -1;
        }
    }

    public static boolean deleteOrder() {
        System.out.println("Enter the ID of the order you want to delete: ");
        int id = scanner.nextInt();
        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "SELECT * FROM Orders WHERE orderID =" + id);
            ResultSet rs = statement.getResultSet();
            String issueDate = rs.getString("issueDate");
            System.out.println("Order was issued on " + issueDate);
            statement.execute(
                    "DELETE FROM Orders WHERE orderID = " + id);
            System.out.println("An order with issue date " + issueDate + " and with id: " + id + " is successfully removed from database.");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Orders findOrderByUserId() {
        System.out.println("UserID: ");
        String usersID = scanner.nextLine().trim();

        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "SELECT * FROM Orders WHERE userID = \"" + usersID + "\";");


            int orderID;
            Orders order = new Orders();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                orderID = rs.getInt("orderID");
                int bookID = rs.getInt("bookID");
                int userID = rs.getInt("userID");
                Date issueDate = formatter.parse(rs.getString("issueDate"));
                Date returnDate = formatter.parse(rs.getString("returnDate"));
                String orderInfo = rs.getString("information");

                System.out.println("Order ID: " + orderID + "\n" + "Book ID: " + bookID + "\n" + "User ID: " + userID + "\n" +
                        "Date order was issued : " + issueDate + "\n" + "Return date: " + returnDate + "\n" + "Additional information about order: " + orderInfo);
            }
            return order;

        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
            System.out.println("Didn't find an order. Try again.");
            return null;
        }
    }

    public static boolean updateOrder() {
        System.out.println("Enter the ID of the order: ");
        int id = scanner.nextInt();

        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute(
                    "SELECT * FROM Orders WHERE orderID =" + id);

            ResultSet rs = statement.getResultSet();

            int orderID = rs.getInt("orderID");
            int bookID = rs.getInt("bookID");
            int userID = rs.getInt("userID");
            Date issueDate = formatter.parse(rs.getString("issueDate"));
            Date returnDate = formatter.parse(rs.getString("returnDate"));
            String orderInfo = rs.getString("information");

            System.out.println("Order ID: " + orderID + "\n" + "Book ID: " + bookID + "\n" + "User ID: " + userID + "\n" +
                    "Date order was issued : " + issueDate + "\n" + "Return date: " + returnDate + "\n" + "Additional information about order: " + orderInfo);

            System.out.println("What information do you want to change?");
            System.out.println("Please, print:  \n ---> 1 for orderID; \n ---> 2 for bookID; \n ---> 3 for userID; \n ---> 4 for issueDate (yyyy-MM-dd); \n ---> 5 for returnDate (yyyy-MM-dd);" +
                    " \n ---> 6 for information; \n ---> 0 if you want to return to Orders Menu");
            int column = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new information: ");
            String info = scanner.nextLine().trim();

            if (column == 1) {
                statement.execute("UPDATE Orders SET orderID = \"" + info + "\" WHERE orderID = " + id + ";");

            } else if (column == 2) {
                statement.execute("UPDATE Orders SET bookID = \"" + info + "\" WHERE orderID = " + id + ";");

            } else if (column == 3) {
                statement.execute("UPDATE Orders SET userID = \"" + info + "\" WHERE orderID = " + id + ";");

            } else if (column == 4) {
                statement.execute("UPDATE Orders SET issueDate = \"" + info + "\" WHERE orderID = " + id + ";");

            } else if (column == 5) {
                statement.execute("UPDATE Orders SET returnDate = \"" + info + "\" WHERE orderID = " + id + ";");

            } else if (column == 6) {
                statement.execute("UPDATE Orders SET information = \"" + info + "\" WHERE orderID = " + id + ";");

            } else if (column == 0) {
                OrdersMenu.execute();

            } else {
                System.out.println("Update failed. Please check data and try again.");
                OrdersMenu.execute();
            }
            System.out.println("Successfully updated! Information added: " + info);
            return true;

        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static String printAllOrders() {
        try {
            Statement statement = MainMenu.helper.getStatment();
            statement.execute("SELECT * FROM Orders;");
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int bookID = rs.getInt("bookID");
                int userID = rs.getInt("userID");
                Date issueDate = formatter.parse(rs.getString("issueDate"));
                Date returnDate = formatter.parse(rs.getString("returnDate"));
                String orderInfo = rs.getString("information");

                System.out.println("Order ID: " + orderID + "\n" + "Book ID: " + bookID + "\n" + "User ID: " + userID + "\n" +
                        "Date order was issued : " + issueDate + "\n" + "Return date: " + returnDate + "\n" + "Additional information about order: " + orderInfo + "\n***************************************");
            }

        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}








