import java.sql.*;

/**
 * This program creates a Pet Adoption database that will be accessed by the
 * PetFinderGUI program
 */

public class CreatePetDB {

    public static void main(String[] args) {
        //assign a String to the database path
        final String DB_URL = "jdbc:derby:PetDB;create=true";

        try {
            //create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL);

            //create a statement object
            Statement stmt = conn.createStatement();

            //create the Pet Table
            System.out.println("Creating the Pet Table...");
            stmt.execute("CREATE TABLE Pet (" +
                    "PetID CHAR(10) NOT NULL PRIMARY KEY, " +
                    "Type CHAR(30), " +
                    "Name CHAR(30)," +
                    "Description CHAR(200))");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'10001', " +
                    "'Dog', " +
                    "'Surf', " +
                    "'Surf is a senior female dachsund. Very territorial and loyal. Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'10002', " +
                    "'Dog', " +
                    "'Tucker', " +
                    "'Tucker is a senior male Cavalier King Charles Spaniel. Very passive and loving. Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'10003', " +
                    "'Dog', " +
                    "'Bailey', " +
                    "'Bailey is a young female terrier mix. Very smart and loyal. Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'20001', " +
                    "'Cat', " +
                    "'Tazz', " +
                    "'Tazz is a senior male brown spotted bengal cat. Show quality with beautiful rosettes. Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'20002', " +
                    "'Cat', " +
                    "'Sawyer', " +
                    "'Sawyer is a senior male snow bengal cat with blue eyes. A little skittish. Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'20003', " +
                    "'Cat', " +
                    "'Edison', " +
                    "'Edison is a black kitten that is super smart, playful and loving. Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'20004', " +
                    "'Cat', " +
                    "'Damon', " +
                    "'Damon is a black kitten that is very playful and loving. He is my sweet baby! Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'30001', " +
                    "'Snake', " +
                    "'Fang', " +
                    "'Fang is a 12 year old corn snake. Eats frozen rats. Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'40001', " +
                    "'Ferret', " +
                    "'Rascal', " +
                    "'Rascal is a typical ferret. He smells. Bonded with Leslie. Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'40002', " +
                    "'Ferret', " +
                    "'Leslie', " +
                    "'Leslie is a typical ferret. She smells. Bonded with Rascal. Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'10004', " +
                    "'Dog', " +
                    "'Norman', " +
                    "'Norman is a young male mix dog. Very quiet and never barks unlike Surf. Great addition to any family' )");

            // Insert pet.
            stmt.execute("INSERT INTO Pet VALUES ( " +
                    "'50001', " +
                    "'Hamster', " +
                    "'Hammy', " +
                    "'Hammy is a fake hamster. Great addition to any family' )");

            //CLOSE THE statement and connection
            stmt.close();
            conn.close();
            System.out.println("Done");
        }
        catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}