package Service;
import DatabaseConnection.JDBC;
import Interface.CustomerInterface;
import Model.*;

import java.sql.*;
import java.util.Optional;

public class CustomerService implements CustomerInterface {
    public Optional<Customer> findCustomer(String fName, String lName) {
        String sql = "SELECT * FROM customers WHERE first_name = ? AND last_name = ?";
        Customer customer = null;
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fName);
            statement.setString(2, lName);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                customer = new Customer(result.getInt("id"), result.getString("first_name"), result.getString("last_name"), result.getInt("age"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(customer);
    }
    public void addCustomer(Customer newCustomer) {
        String sql = "INSERT INTO customers (first_name, last_name, age) VALUES (?, ?, ?)";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, newCustomer.getFirstName());
            statement.setString(2, newCustomer.getLastName());
            statement.setInt(3, newCustomer.getAge());
            statement.executeUpdate();
            //setting author id with the auto generated value
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newCustomer.setID(generatedKeys.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeCustomer(Customer customer) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customer.getID());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int findTransaction(Customer customer, Book book) {
        int retVal;
        String sql1 = "SELECT borrow_date, return_date FROM borrowbook WHERE book_id = ? AND customer_id = ? AND borrow_date IS NOT NULL AND return_date IS NOT NULL";
        String sql2 = "SELECT borrow_date, return_date FROM borrowbook WHERE book_id = ? AND customer_id = ? AND borrow_date IS NOT NULL AND return_date IS NULL";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement1 = connection.prepareStatement(sql1); PreparedStatement statement2 = connection.prepareStatement(sql2)) {
            statement1.setInt(1, book.getID());
            statement1.setInt(2, customer.getID());
            statement2.setInt(1, book.getID());
            statement2.setInt(2, customer.getID());
            ResultSet result1 = statement1.executeQuery();
            ResultSet result2 = statement2.executeQuery();
            if(result1.next()) {
                retVal = 1; //book has been borrowed and returned
            }
            else if(result2.next()) {
                retVal = 2; //book has been borrowed but not returned
            }
            else {
                retVal = 3; //no transaction between the book and customer yet
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            retVal = -1;
        }
        return retVal;
    }
    public void borrowBook(Customer customer, Book book, Date borrowed) {
        String sql = "INSERT INTO borrowbook (book_id, customer_id, borrow_date) VALUES (?, ?, ?)";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, book.getID());
            statement.setInt(2, customer.getID());
            statement.setDate(3, borrowed);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void returnBook(Customer customer, Book book, Date returned) {
        String sql = "UPDATE borrowbook SET return_date = ? WHERE book_id = ? AND customer_id = ?";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, returned);
            statement.setInt(2, book.getID());
            statement.setInt(3, customer.getID());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
