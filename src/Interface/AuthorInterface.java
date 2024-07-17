package Interface;
import Model.*;
import java.util.Optional;

public interface AuthorInterface {
    public Optional<Author> findAuthor(String fName, String lName);
    public void addAuthor(Author newAuthor);
    public void removeAuthor(Author author);
}
