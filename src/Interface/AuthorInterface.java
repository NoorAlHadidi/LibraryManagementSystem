package Interface;
import Model.*;

public interface AuthorInterface {
    public Author findAuthor(String fName, String lName);
    public void addAuthor(Author newAuthor);
    public void removeAuthor(Author author);
}
