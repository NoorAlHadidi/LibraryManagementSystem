import Model.*;
import Service.*;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        BookService bookServ = new BookService();
        // add a book
        Book newBook = new Book(4, "Test", 2, "Test Genre", Date.valueOf("2000-10-10"));
        bookServ.addBook(newBook);
    }
}
