package menu;

import objects.Books;

import java.util.ArrayList;
import java.util.List;

public class LibraryCollection {
    private List<Books> libraryCollection;

    public LibraryCollection() {
        libraryCollection = new ArrayList<Books>();
    }

    public void addBook(Books book) {
        libraryCollection.add(book);  // a book added to a list
    }

    @Override
    public String toString() {
        String bookInfo = "\n";

        for (int i = 0; i < libraryCollection.size(); i++) {
            {
                Books book = libraryCollection.get(i);
                bookInfo = bookInfo + book.toString();
            }
        }
        return bookInfo;
    }
}