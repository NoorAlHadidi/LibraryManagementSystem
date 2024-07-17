package Interface;
import Model.*;
import java.sql.Date;
import java.util.Optional;

public interface CustomerInterface {
    public Optional<Customer> findCustomer(String fName, String lName);
    public void addCustomer(Customer newCustomer);
    public void removeCustomer(Customer customer);
    public int findTransaction(Customer customer, Book book);
    public void borrowBook(Customer customer, Book book, Date borrowed);
    public void returnBook(Customer customer, Book book, Date returned);
}
