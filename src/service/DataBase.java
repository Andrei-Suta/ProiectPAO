package service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/spital";
    private static final String DATABASE_USER_NAME = "root";
    private static final String DATABASE_PASSWORD = "habarnam2511";
    private Connection connection = null;

    private static final DataBase INSTANCE = new DataBase();

    private DataBase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = getConnection();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE_URL,DATABASE_USER_NAME,DATABASE_PASSWORD);
    }

    public static DataBase getInstance() {
        return INSTANCE;
    }

    public Connection getDBConnection() {
        return this.connection;
    }
}
