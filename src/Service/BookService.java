package Service;
import DatabaseConnection.JDBC;
import Interface.BookInterface;
import Model.*;
import java.sql.*;
import java.util.Optional;

public class BookService implements BookInterface {
    public Optional<Book> findBook(String title) {
        String sql = "SELECT * FROM books WHERE title = ?";
        Book book = null;
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                book = new Book(result.getInt("id"), result.getString("title"), result.getInt("author_id"), result.getString("genre"), result.getDate("publish_date"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(book);
    }
    public void addBook(Book newBook) {
        String sql = "INSERT INTO books (title, author_id, genre, publish_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, newBook.getTitle());
            statement.setInt(2, newBook.getAuthorID());
            statement.setString(3, newBook.getGenre());
            statement.setDate(4, newBook.getPublished());
            statement.executeUpdate();
            //setting book id with the auto generated value
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newBook.setID(generatedKeys.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeBook(Book book) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, book.getID());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayBooks() {
        String sql = "SELECT b.id, b.title, b.genre, b.publish_date, a.first_name, a.last_name FROM (books as b JOIN authors as a ON b.author_id = a.id)";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println("Book ID: " + result.getInt("id"));
                System.out.println("Book title: " + result.getString("title"));
                System.out.println("Book author: " + result.getString("first_name") + " " + result.getString("last_name"));
                System.out.println("Book genre: " + result.getString("genre"));
                System.out.println("Book publish date: " + result.getDate("publish_date"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void retrieveBook(Author author) {
        String sql = "SELECT * FROM books WHERE author_id = ?";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, author.getID());
            ResultSet result = statement.executeQuery();
            System.out.println("Books written by " + author.getFirstName() + " " + author.getLastName());
            while (result.next()) {
                System.out.println("Book ID: " + result.getInt("id"));
                System.out.println("Book title: " + result.getString("title"));
                System.out.println("Book genre: " + result.getString("genre"));
                System.out.println("Book publish date: " + result.getDate("publish_date"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //another method for displayBooks()
    /*public List<Book> displayBooks() {
        String sql = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();

        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Book person = new Book(result.getInt("id"), result.getString("title"), result.getInt("author_id"), result.getString("genre"), result.getDate("publish_date"));
                books.add(book);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }*/
}
