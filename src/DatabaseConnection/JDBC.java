package DatabaseConnection;

import java.sql.*;

public class JDBC {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/library";
    private static final String user = "root";
    private static final String password = "P@554NOORmySQL";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    /*Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "P@554NOORmySQL");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("title"));
            System.out.println(resultSet.getString("genre"));
    }*/
}