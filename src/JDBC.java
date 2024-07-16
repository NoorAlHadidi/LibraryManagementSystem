import java.sql.*;

public class JDBC {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "P@554NOORmySQL");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("isbn"));
            System.out.println(resultSet.getString("title"));
            System.out.println(resultSet.getString("genre"));
        }
    }
}