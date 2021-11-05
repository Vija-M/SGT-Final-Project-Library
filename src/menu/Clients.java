package menu;

//import Books.Id;

public class Clients {
    int userId;
    int orderId;
    String userFirstName, userLastName;
    String checkoutDate = "Nov 29 21 at 15:40";
    String dueDate = "Dec 20 21";


    public Clients(int userId, int orderId, String checkoutDate, String dueDate) {
        this.userId = userId;
        this.orderId = orderId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public void printOutReceipt() {
        // System.out.println(String userId, String userFirstName, String userLastName, int orderId);
        System.out.println("On the date " + checkoutDate + "bookid" + " was borrowed.");
        // bookid is a placeholder for the Books.id that is supposed to extract the information about the book borrowed.
        System.out.println("The book is due" + dueDate);
    }


}
