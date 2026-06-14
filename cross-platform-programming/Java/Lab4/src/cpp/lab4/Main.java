package cpp.lab4;

import cpp.lab4.gui.LibraryScene;
import cpp.lab4.logic.Library;
import cpp.lab4.logic.MainThread;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainThread mainThread = new MainThread(new Library());
        LibraryScene libraryScene = new LibraryScene(mainThread);
        primaryStage.setTitle("Lab4");
        primaryStage.setScene(libraryScene.getScene());
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
