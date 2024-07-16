package Interface;
import Model.*;

public interface CustomerInterface {
    public Customer findCustomer(String fName, String lName);
    public void addCustomer(Customer newCustomer);
    public void removeCustomer(Customer customer);
    public void borrowBook(Customer customer, Book book);
    public void returnBook(Customer customer, Book book);
}
