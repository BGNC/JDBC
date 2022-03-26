package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    private String userName="root";
    private String password = "";
    private String dbUrl="jdbc:mysql://localhost:3306/world";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, userName, password);
    }

    public void showErrorMessageRelatedDatabase(SQLException sqlException){
        System.out.println(sqlException.getMessage());
        System.out.println("Error code : "+sqlException.getErrorCode());
    }


}
