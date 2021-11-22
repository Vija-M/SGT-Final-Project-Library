package objects;

import java.util.Date;
import java.util.StringJoiner;

public class Orders {
private int orderID;
private int BookID;
private Date issueDate;
private Date returnDate;
private String orderInfo;

    public Orders() {
    }

    public Orders(int orderID, int bookID, Date issueDate, Date returnDate, String orderInfo) {
        this.orderID = orderID;
        BookID = bookID;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.orderInfo = orderInfo;
    }

    public Orders(int orderID, int bookID, Date issueDate, Date returnDate) {
        this.orderID = orderID;
        BookID = bookID;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Orders.class.getSimpleName() + "[", "]")
                .add("orderID=" + orderID)
                .add("BookID=" + BookID)
                .add("issueDate=" + issueDate)
                .add("returnDate=" + returnDate)
                .add("orderInfo='" + orderInfo + "'")
                .toString();
    }
}
