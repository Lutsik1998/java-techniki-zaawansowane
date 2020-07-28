package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField textField;

    @FXML
    private Button sendButton;

    @FXML
    private TextArea textArea;


    @FXML
    void onSendClick(ActionEvent event) {

        String text = textField.getText();
        textArea.appendText("Otrzymano: - " + text+"\n");


    }




}
