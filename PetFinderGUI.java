import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.sql.*;

/**
 * This program will open a GUI and allow the user to search for a pet
 * to adopt that is listed in the PetDB
 */

public class PetFinderGUI extends Application {
    //Database URL
    final String DB_URL = "jdbc:derby:PetDB";

    Label findType;
    ComboBox<String> petTypeBox;
    TextArea resultTextArea;
    MenuBar menu;
    Menu fileMenu;
    MenuItem exitItem;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Pet Adoption Site");

        //build the menu bar
        menu = new MenuBar();
        fileMenu = new Menu("File");
        exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> primaryStage.close());
        fileMenu.getItems().addAll(exitItem);
        menu.getMenus().addAll(fileMenu);

        /**
         * build the main gui page
         */
        //label describing site
        Label descriptionLabel = new Label("Let's look for your favorite animal to adopt!");
        descriptionLabel.setStyle("-fx-font-family: \"Comic Sans MS\";\n" +
                "    -fx-text-fill: green;\n" +
                "    -fx-font-size: 14pt");


        // Label asking user what pet they are looking for
        findType = new Label("What type of pet are you looking for?");
        findType.setPadding(new Insets(10));
        findType.setAlignment(Pos.CENTER_LEFT);

        //combobox giving user a choice of pet type
        petTypeBox = new ComboBox<>();
        petTypeBox.getItems().addAll("Dog", "Cat", "Snake", "Ferret", "Hamster" );

        //build the query results area
        resultTextArea = new TextArea("You will find your new pet here!");
        resultTextArea.setPrefColumnCount(4);
        resultTextArea.setPrefRowCount(10);
        resultTextArea.setPadding(new Insets(15));

        //button to send search criteria to database
        Button findPet = new Button("Find My Pet!");
        findPet.setOnAction(event -> {
            resultTextArea.setText("Looking for your pet " + petTypeBox.getValue());

            try {
                //create a connection to the database
                Connection conn = DriverManager.getConnection(DB_URL);

                //create a Statement object
                Statement stmt = conn.createStatement();

                //determine which sqlstatement to use
                String sqlStatement = "SELECT * FROM Pet WHERE Type = '" + petTypeBox.getValue() + "'";

                //add sqlstatement to the query
                ResultSet resultSet = stmt.executeQuery(sqlStatement);

                //get the result metadata
                ResultSetMetaData meta = resultSet.getMetaData();

                // create the string to hold the results
                String output = "";

                //get the results
                while (resultSet.next()){
                    //get all of the columns in a row
                    for (int i = 1; i <= meta.getColumnCount(); i++){
                        output += resultSet.getString(i);
                        output += '\t';
                    }
                    output +='\n';
                }
                //display the results
                resultTextArea.setText(output);

                //close the connection
                stmt.close();
                conn.close();

            }
            catch (SQLException e){
                e.printStackTrace();
                System.exit(0);
            }

        });


        //add findtype, pettypebox and findPet button to an hbox
        HBox descHBox = new HBox(10, descriptionLabel);
        descHBox.setAlignment(Pos.CENTER);
        descHBox.setPadding(new Insets(10));
        HBox hBox = new HBox(10, findType, petTypeBox);
        HBox hBox2 = new HBox(10, findPet);
        hBox.setPadding(new Insets(25));
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setPadding(new Insets(10));

        VBox vbox = new VBox(10, descHBox, hBox, hBox2);


        //add elements to a border pane
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menu);
        borderPane.setCenter(vbox);
        borderPane.setBottom(resultTextArea);

        //create scene and display it
        Scene scene = new Scene(borderPane, 700, 500);
        scene.getStylesheets().add("PetFinderStyles.css");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
