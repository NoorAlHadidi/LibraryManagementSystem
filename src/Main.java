import Model.*;
import Service.*;
import java.sql.Date;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        BookService bookServ = new BookService();
        AuthorService authorServ = new AuthorService();
        CustomerService custServ = new CustomerService();
    }
}
