package UserInteraction;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by Eric on 10/30/2017.
 */
public class GUI extends Application {
    private Button submit = new Button("Submit");
    private Label request = new Label("Request: ");
    private String requestText;
    private TextArea responses = new TextArea();
    private TextArea input = new TextArea();
    private TextField terminal = new TextField();
    private RequestParser rp = new RequestParser();
    private ShellGUI shell = new ShellGUI(rp);
    private Font defaultFont = new Font("Helvetica", 14);

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane b = new BorderPane();
        TabPane tb = new TabPane();
        Tab tab = new Tab();
        tab.setText("Flight Reservation");
        tb.getTabs().add(tab);
        terminal.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
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
            public void handle(ActionEvent event) {
                requestText = terminal.getText();
                callShell();
            }
        });
        request.setFont(new Font("Helvetica", 14));
        //b.setTop(tb);
        Button newWindow = new Button("New Window");
        newWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                try{
                    GUI anotherInstance = new GUI();
                    anotherInstance.start(stage);
                }catch (Exception e){}
            }
        });
        b.setTop(newWindow);
        b.setBottom(buildBottom());
        Scene scene = new Scene(b);
        primaryStage.setTitle("AFRS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox buildBottom(){
        HBox hb = new HBox();
        VBox vb = new VBox();
        HBox.setHgrow(terminal,Priority.ALWAYS);
        hb.getChildren().addAll(terminal,submit);
        vb.getChildren().addAll(request,hb);
        return vb;
    }

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
        subHB.getChildren().addAll(yourRequests, input);
        subHB1.getChildren().addAll(yourResponses, responses);
        hb.getChildren().addAll(subHB, subHB1);
        return hb;

    }

    private void callShell(){
        String inputText = input.getText();
        inputText += requestText + '\n';
        terminal.clear();
        input.setText(inputText);
        responses.setText(shell.displayLine(shell.outputForInput(requestText)));
    }

/**
    @Override
    public void init() throws Exception{

    }
    **/
    public static void main(String[] args){
        Application.launch(args);
    }
}
