package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.util.Optional;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loaderMain = new FXMLLoader();
        loaderMain.setLocation(this.getClass().getResource("/sample/sample.fxml"));
        StackPane stackPane = loaderMain.load();

        TextInputDialog dialog = new TextInputDialog("8091");
        dialog.setTitle("Wprowadz port");
        dialog.setHeaderText("Wprowadz port");
        dialog.setContentText("Please enter port number:");
        Optional<String> optStr = dialog.showAndWait();
        if(optStr.isPresent()) {
            Server.controller.server.port = Integer.parseInt(optStr.get());
            Server.controller.client.port = Integer.parseInt(optStr.get());
            Server.controller.translator.port = Integer.parseInt(optStr.get());
        }
        Server.controller.server.start();


        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Lab7");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
