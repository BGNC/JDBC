package UX;

import Database.DatabaseHelper;
import utilities.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    Connection connection = null;
    DatabaseHelper databaseHelper = new DatabaseHelper();
    Statement statement=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet;

    public void init() throws SQLException {

        deleteOperation();
        insertOperation();
        updateOperation();
        selectOperation();


    }

    private void deleteOperation(){
        try {
            connection=databaseHelper.getConnection();
            statement= connection.createStatement();
            String sql = "DELETE FROM city WHERE ID =?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"4085");
            preparedStatement.executeUpdate();
            System.out.println("delete is ok ");
        } catch (SQLException e) {
            databaseHelper.showErrorMessageRelatedDatabase(e);
        }
    }

    private void updateOperation(){
        try {
            connection=databaseHelper.getConnection();
            statement=connection.createStatement();
            String sql = "UPDATE city SET Info=150000 where ID =?";

            preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setString(1,"4085");
            int isOk = preparedStatement.executeUpdate();
            System.out.println("Update is ok "+isOk);
        } catch (SQLException e) {
            databaseHelper.showErrorMessageRelatedDatabase(e);
        }
    }

    private void insertOperation() throws SQLException{

        try {
            connection=databaseHelper.getConnection();
            statement= connection.createStatement();


            String sql = "insert into city(Name,CountryCode,District,Info) values(?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"Düzce 2");
            preparedStatement.setString(2,"TUR");
            preparedStatement.setString(3,"Turkey");
            preparedStatement.setString(4,"70000");
            int result = preparedStatement.executeUpdate();
            System.out.println("Insert is OK"+result);
        }
        catch (SQLException e)
        {
            databaseHelper.showErrorMessageRelatedDatabase(e);
        }



        


    }

    private void selectOperation() throws SQLException {



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
