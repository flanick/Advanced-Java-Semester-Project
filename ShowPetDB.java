import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

/**
 * This program displays the Pet Database
 */

public class ShowPetDB {
    public static void main(String[] args) {
        //create a named constant for the URL
        final String DB_URL = "jdbc:derby:PetDB";

        try {
            //create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL);

            //Create a statement object
            Statement stmt = conn.createStatement();

            //create a string with a SELECT statement
            String sqlStatement = "SELECT * FROM Pet";

            //Send the statement to the DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            //Display a header for the listing
            System.out.println("Pet ID \t\tType \t\t\t\t\t\t  Name \t\t\t\t\t\t\tDescription");
            System.out.println("-----------------------------------------------------------------------------------");

            //display the contents of the result set
            //the result set will have four columns
            while (result.next()){
                System.out.println(result.getString("PetID") + "\t" +
                        result.getString("Type") + result.getString("Name") +
                        result.getString("Description"));
            }
            //close the connection
            conn.close();
        }
        catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}