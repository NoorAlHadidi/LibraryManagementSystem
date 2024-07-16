package Interface;
import Model.*;
import java.util.Optional;

public interface BookInterface {
    public Optional<Book> findBook(String title);
    public void addBook(Book newBook);
    public void removeBook(Book book);
    public void displayBooks();
    public void retrieveBook(Author author);
}
