package notebook;

//JavaFX imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

//Main class to start the JavaFX app
public class Main extends Application {

    //start() is called automatically when the app runs
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Load the FXML layout file(UI design)
        Parent root = FXMLLoader.load(getClass().getResource("/views/notes_page.fxml"));

        //set window title
        primaryStage.setTitle("Notebook App");

        //create a scene (window contents) with a size
        primaryStage.setScene(new Scene(root, 600, 400));

        //show the main window
        primaryStage.show();
    }

    //Java main methods that launches the JavaFX app
    public static void main(String[] args) {
        launch(args);
    }
}

