package Interface;
import Model.*;

public interface BookInterface {
    public Book findBook(String title);
    public void addBook(Book newBook);
    public void removeBook(Book book);
    public void displayBooks();
    public void retrieveBook(Author author);
}
