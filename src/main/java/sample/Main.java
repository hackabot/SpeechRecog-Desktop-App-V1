package sample;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.microphone.MicrophoneAnalyzer;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.speech.v1p1beta1.*;
import com.google.common.collect.Lists;
import javaFlacEncoder.FLACFileWriter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import static javax.sound.sampled.AudioSystem.*;

import com.google.cloud.speech.v1p1beta1.RecognitionConfig.AudioEncoding;
import com.google.protobuf.ByteString;


public class Main extends Application {

    public static CredentialsProvider credentialsProvider;
    public static GoogleCredentials credentials;
    public static SpeechSettings speechSettings;
    public static SpeechClient speechClient;
    public static String buttonLabel = "Start Listening";
    public static String recognizedText = "Recognized Text";
    public static boolean started = false;

    public static Controller ControllerRef =  null;
    public static boolean initialized = false;

    @Override
    public void start(final Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpeechRecognizer.fxml"));
        Parent root = loader.load();
                //FXMLLoader.load(getClass().getResource("/SpeechRecognizer.fxml"));
        primaryStage.setTitle("Speech Recogniser");

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        ControllerRef = loader.getController();
        Text t1 = new Text("You can display what the line test. We don't have the label is a test test smashing Uline characters in it. Then deliver was up to a new line, whatever the new line character it if the label has Bostick set to 2 and there's not enough space to display single 9 of 10 in the label was lost a text to a new line."+
                "You can display what the line test. We don't have the label is a test test smashing Uline characters in it. Then deliver was up to a new line, whatever the new line character it if the label has Bostick set to 2 and there's not enough space to display single 9 of 10 in the label was lost a text to a new line.");
//        t1.wrappingWidthProperty().bind(ControllerRef.recognizedText.widthProperty());
//        t1.setWrappingWidth(200);
//        ControllerRef.recognizedText
//        ControllerRef.recognizedText.setContent(new Text("You can display what the line test. We don't have the label is a test test smashing Uline characters in it. Then deliver was up to a new line, whatever the new line character it if the label has Bostick set to 2 and there's not enough space to display single 9 of 10 in the label was lost a text to a new line."));
        ControllerRef.recognizedText.setText("Click on START Listening to get started");

//        Main.initialise();

        Label lbl = (Label) loader.getNamespace().get("statusLabel");
        lbl.setText("Initialising...");
        Main.initialise1();

//        while(!initialized){
//            Thread.sleep(50);
//        }
//
//        lbl.setText("Not Listening");

        Button button = (Button) loader.getNamespace().get("button1");


//        Thread bgThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //        buildUI(primaryStage);
//                System.out.println("Started");
////        ambientListening2();
//                initialise();
//                record();
//                recognizedText = recognize();
//                Main.buttonLabel = "Stop Listening";
//
//            }
//        });

//        bgThread.start();

    }



    public static void initialise() {
        try {

//            CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(ServiceAccountCredentials.fromStream(new FileInputStream("C:\\Users\\Somnath\\Documents\\GitHub\\SpeechRecog\\SpeechRecogv1-180506121731.json")));
            credentialsProvider = FixedCredentialsProvider.create(ServiceAccountCredentials.fromStream(new FileInputStream("SpeechRecogv1-180506121731.json")));

            credentials = GoogleCredentials.fromStream(new FileInputStream("SpeechRecogv1-180506121731.json"))
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));


            speechSettings = SpeechSettings
                    .newBuilder()
                    .setCredentialsProvider(credentialsProvider)
                    .build();
            speechClient = SpeechClient.create(speechSettings);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void buildUI(Stage stage){
        //creating label email
        Text text1 = new Text("Status");



        //Creating Text Filed for email
        TextField textField1 = new TextField();

        Label label1 = new Label();
        label1.setText("Not Listening");

        //Creating Buttons
        Button button1 = new Button("Start Listening");


        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(400, 200);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(text1, 0, 0);
//        gridPane.add(textField1, 1, 0);
        gridPane.add(label1, 1, 0);
        gridPane.add(button1, 0, 2);


        //Styling nodes
        button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("Speech recognizer");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private static void ambientListening() throws Exception{

        String filename = "tarunaudio.wav";//Your Desired FileName
        MicrophoneAnalyzer mic = new MicrophoneAnalyzer(AudioFileFormat.Type.WAVE);
        mic.open();
        mic.captureAudioToFile(filename);
        final int THRESHOLD = 10;//YOUR THRESHOLD VALUE.
        int ambientVolume = mic.getAudioVolume();//
        System.out.println("Ambient volume: " + ambientVolume);
        int speakingVolume = -2;
        boolean speaking = false;
        float seconds = 0;
        while(true){
            int volume = mic.getAudioVolume();
            System.out.println(volume);
//            if(volume>ambientVolume+THRESHOLD){
//                speakingVolume = volume;
//                speaking = true;
//                Thread.sleep(1000);
//                System.out.println("SPEAKING");
//            }
//            if(speaking && volume+THRESHOLD<speakingVolume){
//                break;
//            }
            Thread.sleep(200);//Your refreshRate
            seconds += 0.2;

           //Recording for 5 seconds
            System.out.println("Seconds : " + seconds);
            if(seconds > 1)
                break;
        }
        mic.close();
        //You can also measure the volume across the entire file if you want
        //to be resource intensive.
//        if(!speaking){
//            ambientListening();
//        }
        Recognizer rec = new Recognizer(Recognizer.Languages.ENGLISH_US, "AIzaSyC7W1jE3C1S4LrEaHSsyzagqqgNVzst82w");
        GoogleResponse out = rec.getRecognizedDataForWave(filename);
        System.out.println(out.getResponse());
        ambientListening();
    }

    public static void ambientListening2(){
        // Mixer.Info[] infoArray = AudioSystem.getMixerInfo();
        // for(Mixer.Info info : infoArray) {
        //    System.out.println("info: " + info.toString());
        // }
        AudioFileFormat.Type[] typeArray = getAudioFileTypes();
        for(AudioFileFormat.Type type : typeArray) {
            System.out.println("type: " + type.toString());
        }

        Microphone mic = new Microphone(FLACFileWriter.FLAC);
        File file = new File ("testfile2.flac");	//Name your file whatever you want
        try {
            mic.captureAudioToFile (file);
        } catch (Exception ex) {
            //Microphone not available or some other error.
            System.out.println ("ERROR: Microphone is not availible.");
            ex.printStackTrace ();
        }

    /* User records the voice here. Microphone starts a separate thread so do whatever you want
     * in the mean time. Show a recording icon or whatever.
     */
        try {
            System.out.println ("Recording...");
            Thread.sleep (5000);	//In our case, we'll just wait 5 seconds.
            mic.close ();
        } catch (InterruptedException ex) {
            ex.printStackTrace ();
        }

        mic.close ();		//Ends recording and frees the resources
        System.out.println ("Recording stopped.");

        Recognizer recognizer = new Recognizer (Recognizer.Languages.ENGLISH_US, "");
        //Although auto-detect is available, it is recommended you select your region for added accuracy.
        try {
            int maxNumOfResponses = 4;
            System.out.println("Sample rate is: " + (int) mic.getAudioFormat().getSampleRate());
            GoogleResponse response = recognizer.getRecognizedDataForFlac (file, maxNumOfResponses, (int) mic.getAudioFormat().getSampleRate ());
            System.out.println ("Google Response: " + response.getResponse ());
            System.out.println ("Google is " + Double.parseDouble (response.getConfidence ()) * 100 + "% confident in" + " the reply");
            System.out.println ("Other Possible responses are: ");
            for (String s:response.getOtherPossibleResponses ()) {
                System.out.println ("\t" + s);
            }
        }
        catch (Exception ex) {
            // TODO Handle how to respond if Google cannot be contacted
            System.out.println ("ERROR: Google cannot be contacted");
            ex.printStackTrace ();
        }

        file.deleteOnExit ();	//Deletes the file as it is no longer necessary.
    }

    public static String recognize() {

        // The path to the audio file to transcribe
        String fileName = "testfile2.flac";

            // Reads the audio file into memory
            Path path = Paths.get(fileName);
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ByteString audioBytes = ByteString.copyFrom(data);

        // Builds the sync recognize request
        RecognitionConfig config = RecognitionConfig.newBuilder()
                .setEncoding(AudioEncoding.FLAC)
                .setSampleRateHertz(8000)
                .setLanguageCode("en-US")
                .build();
        RecognitionAudio audio = RecognitionAudio.newBuilder()
                .setContent(audioBytes)
                .build();


        // Performs speech recognition on the audio file
        RecognizeResponse response = speechClient.recognize(config, audio);
        List<SpeechRecognitionResult> results = response.getResultsList();

        for (SpeechRecognitionResult result : results) {
            // There can be several alternative transcripts for a given chunk of speech.
            // Just use the
            // first (most likely) one here.
//            for(SpeechRecognitionAlternative alternative: result.getAlternativesList()){
//                System.out.printf("Transcription: %s%n", alternative.getTranscript());
//            }
            System.out.println(result.getAlternativesList().get(0).getTranscript());
            return  result.getAlternativesList().get(0).getTranscript();
        }

        return "Retry";
    }

    public static void record(){
        AudioFileFormat.Type[] typeArray = getAudioFileTypes();
        for(AudioFileFormat.Type type : typeArray) {
            System.out.println("type: " + type.toString());
        }

        Microphone mic = new Microphone(FLACFileWriter.FLAC);
        File file = new File ("testfile2.flac");	//Name your file whatever you want
        try {
            mic.captureAudioToFile (file);
        } catch (Exception ex) {
            //Microphone not available or some other error.
            System.out.println ("ERROR: Microphone is not availible.");
            ex.printStackTrace ();
        }

    /* User records the voice here. Microphone starts a separate thread so do whatever you want
     * in the mean time. Show a recording icon or whatever.
     */
        while (started){
            try {
                Thread.sleep (50);	//In our case, we'll just wait 5 seconds.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        mic.close ();		//Ends recording and frees the resources
        System.out.println ("Recording stopped.");
    }

    public static void record1(){
        Thread bgThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //        buildUI(primaryStage);
                System.out.println("Recording ");
                record();
//                recognizedText = recognize();
//                Main.buttonLabel = "Stop Listening";

            }
        });

        bgThread.start();
    }

    public static void initialise1(){
        Thread bgThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Initialising ...");
                initialise();
                initialized = true;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ControllerRef.statusLabel.setText("Initialized");
                    }
                });
                System.out.println("Initialized ");
            }
        });

        bgThread.start();
    }

    public static void recognize1(){
        Thread bgThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Recognizing ...");
                final String recog = recognize();

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ControllerRef.recognizedText.setText(recog);
                        ControllerRef.statusLabel.setText("Click the button to Start Listening");
                    }
                });
                System.out.println("Recognized ");
            }
        });

        bgThread.start();
    }

}

