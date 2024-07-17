import Model.*;
import Service.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        BookService bookServ = new BookService();
        AuthorService authorServ = new AuthorService();
        CustomerService customerServ = new CustomerService();
        /*//checking availability and adding of book and author
        System.out.println("Enter the title of the book you'd like to add:");
        String bookTitle;
        while (true) {
            bookTitle = scanner.nextLine();
            if (bookServ.findBook(bookTitle).isEmpty()) {
                System.out.println("Enter the book's author's first and last name:");
                String bookAuthor = scanner.nextLine();
                String[] authorDetails = bookAuthor.split(" ");
                if (authorServ.findAuthor(authorDetails[0], authorDetails[1]).isEmpty()) {
                    Author author = new Author(0, authorDetails[0], authorDetails[1]);
                    authorServ.addAuthor(author);
                }
                int bookAuthorID = (authorServ.findAuthor(authorDetails[0], authorDetails[1]).get()).getID();
                System.out.println("Enter the book's genre:");
                String bookGenre = scanner.nextLine();
                System.out.println("Enter the book's publish date:");
                String bookRelease = scanner.nextLine();
                Book newBook = new Book(0, bookTitle, bookAuthorID, bookGenre, Date.valueOf(bookRelease));
                bookServ.addBook(newBook);
                break;
            }
            System.out.println("Book is already added, enter another title:");
        }
        //removing a book
        System.out.println("Enter the title of the book you'd like to remove:");
        String bookRemove;
        while (true) {
            bookRemove = scanner.nextLine();
            if (bookServ.findBook(bookRemove).isPresent()) {
                bookServ.removeBook((bookServ.findBook(bookRemove)).get());
                break;
            }
            System.out.println("Book does not exist, enter another title:");
        }
        //displaying all books
        System.out.println("Displaying all books");
        bookServ.displayBooks();
        //retrieving books by a certain author
        System.out.println("Enter the first and last name of the desired author:");
        String retAuthor;
        while (true) {
            retAuthor = scanner.nextLine();
            String[] retAuthorDetails = retAuthor.split(" ");
            if (authorServ.findAuthor(retAuthorDetails[0], retAuthorDetails[1]).isPresent()) {
                bookServ.retrieveBook((authorServ.findAuthor(retAuthorDetails[0], retAuthorDetails[1])).get());
                break;
            }
            System.out.println("Author does not exist, enter correct author name:");
        }*/
        //customer borrowing a book
        System.out.println("Enter the customer's first and last name:");
        String borrowCustomer = scanner.nextLine();
        String[] borrowCustDetails = borrowCustomer.split(" ");
        if (customerServ.findCustomer(borrowCustDetails[0], borrowCustDetails[1]).isEmpty()) {
            System.out.println("Welcome new customer! Please enter your age:");
            String custAge = scanner.nextLine();
            Customer newCustomer = new Customer(0, borrowCustDetails[0], borrowCustDetails[1], Integer.parseInt(custAge));
            customerServ.addCustomer(newCustomer);
        }
        System.out.println("Enter the title of book you want to borrow:");
        String bookBorrow;
        while (true) {
            bookBorrow = scanner.nextLine();
            if (bookServ.findBook(bookBorrow).isPresent()) {
                Book tempBook = bookServ.findBook(bookBorrow).get();
                Customer tempCust = customerServ.findCustomer(borrowCustDetails[0], borrowCustDetails[1]).get();
                if(customerServ.findTransaction(tempCust, tempBook) == 3) {
                    customerServ.borrowBook(tempCust, tempBook, Date.valueOf("2022-09-10"));
                }
                break;
            }
            System.out.println("Book does not exist, enter another title:");
        }
        //customer returning a book
        System.out.println("Enter the customer's first and last name:");
        String returnCustomer;
        String[] returnCustDetails;
        while(true) {
            returnCustomer = scanner.nextLine();
            returnCustDetails = returnCustomer.split(" ");
            if(customerServ.findCustomer(returnCustDetails[0], returnCustDetails[1]).isPresent()) {
                break;
            }
            System.out.println("Customer does not exist, please enter a valid name:");
        }
        System.out.println("Enter the title of book you want to return:");
        String bookReturn;
        while (true) {
            bookReturn = scanner.nextLine();
            if (bookServ.findBook(bookReturn).isPresent()) {
                Book tempBook = bookServ.findBook(bookReturn).get();
                Customer tempCust = customerServ.findCustomer(returnCustDetails[0], returnCustDetails[1]).get();
                if(customerServ.findTransaction(tempCust, tempBook) == 2) {
                    customerServ.returnBook(tempCust, tempBook, Date.valueOf("2024-02-16"));
                }
                break;
            }
            System.out.println("Book does not exist, enter another title:");
        }
    }
}
