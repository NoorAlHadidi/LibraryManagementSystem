package Service;
import DatabaseConnection.JDBC;
import Interface.BookInterface;
import Model.*;
import java.sql.*;
import java.util.Optional;

public class BookService implements BookInterface {
    public Optional<Book> findBook(String title) {
        String sql = "SELECT * FROM books WHERE id = ?";
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
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newBook.getTitle());
            statement.setInt(2, newBook.getAuthorID());
            statement.setString(3, newBook.getGenre());
            statement.setDate(4, newBook.getPublished());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeBook(Book book) {

    }
    public void displayBooks() {

    }
    public void retrieveBook(Author author) {

    }
}
