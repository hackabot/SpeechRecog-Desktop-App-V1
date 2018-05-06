package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;

public class Controller {
    @FXML
    public Button button1;

    @FXML
    public Label statusLabel;

    @FXML
    public TextArea recognizedText;

    @FXML
    public void onButtonPressed(Event e){
        if(Main.started){
            Main.started = false;
            button1.setText("START Listening");
            statusLabel.setText("Recognizing...");
            Main.recognize1();
//            String rcsTxt = Main.recognize();
//            recognizedText.setText(rcsTxt);
//            statusLabel.setText("Not Listening");
            statusLabel.setTextFill(Paint.valueOf("#f30a0a"));
        }
        else {
            Main.started = true;
            button1.setText("STOP Listening");
            statusLabel.setText("Listening...");
            statusLabel.setTextFill(Paint.valueOf("#049556"));
            Main.record1();
        }
    }


}
