package objects;

import java.util.StringJoiner;

public class Books {
    private int id;
    private String title;
    private String isbn;
    private int authorID;
    private int yearPublished;
    private String language;
    private int pages;
    private String genre;
    private String publisher;
    private String edition;
    private boolean hasHardCover;
    private String information;
    private int clientID;
    private int orderID;

    public Books(String title, String isbn, int authorID, int yearPublished, String language, int pages, String genre, String publisher, String edition, boolean hasHardCover, String information, int clientID, int orderID) {
        this.title = title;
        this.isbn = isbn;
        this.authorID = authorID;
        this.yearPublished = yearPublished;
        this.language = language;
        this.pages = pages;
        this.genre = genre;
        this.publisher = publisher;
        this.edition = edition;
        this.hasHardCover = hasHardCover;
        this.information = information;
        this.clientID = clientID;
        this.orderID = orderID;
    }

    public Books(String title, String isbn, int authorID, int yearPublished, String language, int pages, String genre, String publisher, String edition, boolean hasHardCover, String information) {
        this.title = title;
        this.isbn = isbn;
        this.authorID = authorID;
        this.yearPublished = yearPublished;
        this.language = language;
        this.pages = pages;
        this.genre = genre;
        this.publisher = publisher;
        this.edition = edition;
        this.hasHardCover = hasHardCover;
        this.information = information;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!(title.isEmpty())) {
            this.title = title;
        } else {
            System.out.println("Please add the title of the book!");
        }
    }

    public String getIsbn() {
        return isbn;
    }

    // ^(?:ISBN(?:-13)?:?\ )?(?=[0-9]{13}$|(?=(?:[0-9]+[-\ ]){4})[-\ 0-9]{17}$)97[89][-\ ]?[0-9]{1,5}[-\ ]?[0-9]+[-\ ]?[0-9]+[-\ ]?[0-9]$   -  a complete ISBN regex//
    public void setIsbn(String isbn) {
        if (isbn.matches("^(\\d{13})?$")) {
            this.isbn = isbn;
        } else {
            System.out.println("Invalid format! Please enter ISBN number that consists only of 13 digits!");
        }
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        if (authorID == getAuthorID()) {
            this.authorID = authorID;
        } else {
            System.out.println("Author is not in the database. ");      //TODO: When method createAuthor is written, add a call to it here//
        }
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        if (yearPublished > 1455 && yearPublished < 2100) {
            this.yearPublished = yearPublished;
        } else {
            System.out.println("Invalid year, please try again!");
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if (!(language.isEmpty()) || language.matches("[a-zA-Z]")) {
            this.language = language;
        } else {
            System.out.println("Please add language of the book!");
        }
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages > 0 && pages < 9999) {
            this.pages = pages;
        } else {
            System.out.println("Please enter a valid number of pages! ");
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (!(genre.isEmpty()) || genre.matches("[a-zA-Z]")) {
            this.genre = genre;
        } else {
            System.out.println("Please add genre of the book!");
        }
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (!(publisher.isEmpty())) {
            this.publisher = publisher;
        } else {
            System.out.println("Please add publisher of the book!");
        }
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        if (!(edition.isEmpty())) {
            this.edition = edition;
        } else {
            System.out.println("Please add edition of the book!");
        }
    }

    public boolean isHasHardCover() {
        return hasHardCover;
    }

    public void setHasHardCover(boolean hasHardCover) {
        this.hasHardCover = hasHardCover;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {  //TODO: add validation later when ORDERS are completed - if book is in order this.clientID = clientID from order + think of default value if book is not ordered//
        this.clientID = clientID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) { ////TODO: add validation later when ORDERS are completed - if book is in order this.orderID = orderID from order + think of default value if book is not ordered//
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Books.class.getSimpleName() + "[", "]")
                .add("title=" + title)
                .add("ISBN='" + isbn + "'")
                .add("authorID='" + authorID + "'")
                .add("yearPublished='" + yearPublished + "'")
                .add("language='" + language + "'")
                .add("pages=" + pages)
                .add("genre='" + genre + "'")
                .add("publisher='" + publisher + "'")
                .add("edition='" + edition + "'")
                .add("hasHardCover='" + hasHardCover + "'")
                .add("information='" + information + "'")
                .add("clientID='" + clientID + "'")
                .add("orderID='" + orderID + "'")
                .toString();
    }
}