package UserInteraction;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The GUI class which handles building GUI elements as well as the GUI's interactions with the system
 * Created by Eric Lin on 10/30/2017
 */
public class GUI extends Application {
    /**
     * The Submit button
     */
    private Button submit = new Button("Submit");
    /**
     * The "Request" label
     */
    private Label request = new Label("Request: ");
    /**
     * The label informing the user of the connection status
     */
    private Label connectionStatus = new Label("Connection Status: Disconnected");
    /**
     * The inputted request text
     */
    private String requestText;
    /**
     * The TextArea which will display all of the system's responses
     */
    private TextArea responses = new TextArea();
    /**
     * The TextArea which will display the history of commands
     */
    private TextArea input = new TextArea();
    /**
     * The TextField where the user will type and enter his/her commands
     */
    private TextField terminal = new TextField();
    /**
     * The request parser used by the shell to parse through a command
     */
    private RequestParser rp = new RequestParser();
    /**
     * The shell which commands will be sent to
     */
    private ShellGUI shell = new ShellGUI(rp);
    /**
     * The new window button
     */
    private Button newWindow = new Button("New Window");
    /**
     * Default font of text used in the GUI
     */
    private Font defaultFont = new Font("Helvetica", 14);

    /**
     * The start method used by the parent Application class
     * This class initializes the GUI and builds all of its components
     * @param primaryStage the GUI window
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane b = new BorderPane();
        terminal.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            /**
             * Treat the return key the same as pressing submit
             */
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    requestText = terminal.getText();
                    callShell();

                }
            }
        });
        b.setCenter(buildCenter());
        b.setRight(submit);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            /**
             * Event handler for the submit key, sends command to parser
             */
            public void handle(ActionEvent event) {
                requestText = terminal.getText();
                callShell();
            }
        });
        request.setFont(defaultFont);
        b.setTop(buildTop());
        newWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            /**
             * Event handler to start a new instance of GUI
             */
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                try{
                    GUI anotherInstance = new GUI();
                    anotherInstance.start(stage);
                }catch (Exception e){}
            }
        });
        b.setBottom(buildBottom());
        Scene scene = new Scene(b);
        primaryStage.setTitle("AFRS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Builds the bottom of the border pane used by the GUI
     * @return VBox with the request label, along with textfield and submit button
     */
    private VBox buildBottom(){
        HBox hb = new HBox();
        VBox vb = new VBox();
        HBox.setHgrow(terminal,Priority.ALWAYS);
        hb.getChildren().addAll(terminal,submit);
        vb.getChildren().addAll(request,hb);
        return vb;
    }

    /**
     * Builds the center of the border pane
     * @return HBox with the 'Your Request" and "Response" label as well as two text boxes
     */
    private HBox buildCenter(){
        HBox hb = new HBox();
        VBox subHB = new VBox();
        VBox subHB1 = new VBox();
        Label yourRequests = new Label("Your Requests:");
        Label yourResponses = new Label("Response:");
        yourResponses.setFont(defaultFont);
        yourRequests.setFont(defaultFont);
        HBox.setHgrow(input,Priority.ALWAYS);
        HBox.setHgrow(responses,Priority.ALWAYS);
        input.setEditable(false);
        responses.setEditable(false);
        VBox.setVgrow(input,Priority.ALWAYS);
        VBox.setVgrow(responses,Priority.ALWAYS); //grow textbox
        subHB.getChildren().addAll(yourRequests, input);
        subHB1.getChildren().addAll(yourResponses, responses);
        hb.getChildren().addAll(subHB, subHB1);
        return hb;

    }

    /**
     * Builds the top of the BorderPane with the New Window button and connection status
     * @return HBox
     */
    private HBox buildTop(){
        HBox hb = new HBox();
        Region region = new Region();
        HBox.setHgrow(region,Priority.ALWAYS);
        hb.getChildren().addAll(newWindow, region, connectionStatus);
        return hb;
    }

    /**
     * Sends commands to the shell and displays the responses in the GUI
     */
    private void callShell(){
        String inputText = input.getText();
        inputText += requestText + '\n';
        terminal.clear();
        input.setText(inputText);
        String display = shell.displayLine(shell.outputForInput(requestText));
        responses.setText(display);
        display = display.replaceAll("\n","");
        if(display.equals("connect")){
            connectionStatus.setText("Connection Status: Connected");
        }else if(display.equals("disconnect")){
            connectionStatus.setText("Connection Status: Disconnected");
        }
    }

    /**
     * The main function to start the gui
     * @param args unused
     */
    public static void main(String[] args){
        Application.launch(args);
    }
}
