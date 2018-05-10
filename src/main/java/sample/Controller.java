package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;


import java.io.*;

public class Controller {
    @FXML
    public Button button1;

    @FXML
    public Label statusLabel;

    @FXML
    public TextArea recognizedText;

    @FXML
    public ChoiceBox langChoice;

    @FXML
    public ProgressBar volumeBar;

    @FXML
    public Button summarizeButton;

    @FXML
    public TextArea summarizedText;

    @FXML Label summarizeLabel;

    @FXML
    ImageView summarizeImage;

    @FXML
    ImageView recognizeImage;

    @FXML
    Label tagsText;

    @FXML
    Label recognizeLabel;

    @FXML
    Label tagsLabel;

    @FXML
    public void onButtonPressed(Event e){
        if(Main.started){
            Main.started = false;
            button1.setText("START Listening");
            statusLabel.setText("Recognizing...");
            Main.switchFiles();
            Main.recognize1();
//            String rcsTxt = Main.recognize();
//            recognizedText.setText(rcsTxt);
//            statusLabel.setText("Not Listening");
            statusLabel.setTextFill(Paint.valueOf("#f30a0a"));
        }
        else {
            Main.languagePref = (String) langChoice.getValue();
            Main.started = true;
            button1.setText("STOP Listening");
            statusLabel.setText("Listening...");
            statusLabel.setTextFill(Paint.valueOf("#049556"));
            Main.record1();
        }
    }

    @FXML
    public void onSummarizeButtonPressed(Event e){

//        File file = new File("input.txt");
        Main.summarize();
        Main.tagify();

    }




}
