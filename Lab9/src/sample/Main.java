package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    GridPane panel;


    private TextField textField;
    private Button sendButton;
    private TextArea textArea;

    public void initialization() {
        panel = new GridPane();
        textField = new TextField();
        textField.setPromptText("Wpowadz teks");
        sendButton = new Button("Send");
        textArea = new TextArea();
        textArea.setEditable(false);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        initialization();

        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textField.getText();
                textArea.appendText("Otrzymano: - " + text+"\n");
            }
        });

        panel.setVgap(5);
        panel.setHgap(10);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(10);
        panel.getRowConstraints().add(row1);

        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(10);
        panel.getRowConstraints().add(row2);

        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(60);
        panel.getRowConstraints().add(row3);

        GridPane.setHalignment(textField, HPos.CENTER);
        GridPane.setHalignment(sendButton,HPos.CENTER);
        GridPane.setHalignment(textArea,HPos.CENTER);

        panel.add(textField,0,0);
        panel.add(sendButton,0,1);
        panel.add(textArea,0,2);

        Scene scene = new Scene(panel,800,400);
        primaryStage.setScene(scene);


        primaryStage.show();
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
//        primaryStage.setTitle("Lab9");
//        Scene scene = new Scene(root, 300, 275);
//        primaryStage.setScene(scene);
        //primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
