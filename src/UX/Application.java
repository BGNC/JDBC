package UX;

import Database.DatabaseHelper;
import utilities.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public void init() throws SQLException {

        Connection connection = null;
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Statement statement=null;
        ResultSet resultSet;

        try {
            connection=databaseHelper.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery("select Code,Name,Capital,Code2 from country");
            List<Country> listOfCountry = new ArrayList<>();
            while (resultSet.next()){
                listOfCountry.add(new Country(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Capital"),
                        resultSet.getString("Code2")));
            }
            System.out.println(listOfCountry.size());


        } catch (SQLException e) {
            databaseHelper.showErrorMessageRelatedDatabase(e);
        }
        finally {
            connection.close();
        }
    }
}
