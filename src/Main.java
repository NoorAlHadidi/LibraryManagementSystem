import Model.*;
import Service.*;
import java.sql.Date;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        BookService bookServ = new BookService();
        // add a book
        /*Book newBook = new Book(4, "Test", 2, "Test Genre", Date.valueOf("2000-10-10"));
        bookServ.addBook(newBook);*/
        //removing a book
        Optional<Book> book = bookServ.findBook("Test");
        if (book.isPresent()) {
            bookServ.removeBook(book.get());
        }
        else {
            System.out.println("No such book");
        }
        //display all
        bookServ.displayBooks();
        //display by author
        bookServ.retrieveBook(new Author(3, "Marwan", "Ahmed", 21));
    }
}
