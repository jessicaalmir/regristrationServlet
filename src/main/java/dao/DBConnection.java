import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    protected static Connection initializeDatabase() throws SQLException, ClassNotFoundException{
        String mysqlDriver = "com.mysql.jdbc.Driver";
        String dbURL= "jdbc:mysql://localhost:3306/test";
        String userName = "jessica";
        String password = "Valledupar123";

        Class.forName("mysqlDriver");
        Connection dbConnection = DriverManager.getConnection(dbURL, userName, password);

        return  dbConnection;
    }
}