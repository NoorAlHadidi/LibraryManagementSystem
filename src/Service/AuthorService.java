package Service;
import DatabaseConnection.JDBC;
import Interface.AuthorInterface;
import Model.*;

import java.sql.*;
import java.util.Optional;

public class AuthorService implements AuthorInterface {
    public Optional<Author> findAuthor(String fName, String lName) {
        String sql = "SELECT * FROM authors WHERE first_name = ? AND last_name = ?";
        Author author = null;
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fName);
            statement.setString(2, lName);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                author = new Author(result.getInt("id"), result.getString("first_name"), result.getString("last_name"), result.getInt("age"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(author);
    }
    public void addAuthor(Author newAuthor) {
        String sql = "INSERT INTO authors (first_name, last_name, age) VALUES (?, ?, ?)";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, newAuthor.getFirstName());
            statement.setString(2, newAuthor.getLastName());
            statement.setInt(3, newAuthor.getAge());
            statement.executeUpdate();
            //setting author id with the auto generated value
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newAuthor.setID(generatedKeys.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeAuthor(Author author) {
        String sql = "DELETE FROM authors WHERE id = ?";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, author.getID());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
