package menu;

import controllers.OrdersController;

import java.util.Scanner;

public class OrdersMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        System.out.println("Welcome to the orders' menu! Please, choose what do you want to do! ");
        System.out.println("1. Add a new order ->");
        System.out.println("2. Delete an order ->");
        System.out.println("3. Find a order by user ID ->");
        System.out.println("4. Update any information about order by order ID ->");
        System.out.println("5. Print all orders ->");
        System.out.println("6. Choose that for to return in MAIN MENU for librarians:");//return in main menu(RestaurantProgramList -> mainAction)
        System.out.println("0. Exit!");
        System.out.println();
        int inputSelection = scanner.nextInt();
        switch (inputSelection) {
            case 1:
                OrdersController.addNewOrder();
                break;
            case 2:
                OrdersController.deleteOrder();
                break;
            case 3:
                OrdersController.findOrderByUserId();
                break;
            case 4:
                OrdersController.updateOrder();
                break;
            case 5:
                OrdersController.printAllOrders();
                break;
            case 6:
                menu.OrdersMenu.execute();
                break;
            case 0:
                return;
            default:
                System.out.println("Did not recognize this selection, please try again!");
        }
        menu.OrdersMenu.returnToOrdersMenu();
    }

    public static void execute() {
        System.out.println("Welcome back to the menu for librarians!");
        MainMenu.librarianMenu();
    }

    public static void returnToOrdersMenu() {
        System.out.print("\nIf you want to go back to orders' menu print: y ------>  ");
        String answer = scanner.next();
        if (answer.equals("y")) {
            OrdersMenu.menu();
        } else {
            MainMenu.librarianMenu();
        }
    }


}
