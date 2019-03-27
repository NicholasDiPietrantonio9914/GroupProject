package groupproject;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



/**
 *
 * @author Joey
 */
public class Main extends Application{
    
    JSONArray masterAccounts = new JSONArray();
    private Object password;
    private Object userName;
    
    Scene scene1, scene2, scene3;
      
    @Override
    public void start(Stage primaryStage){
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,50,50,50));
        
        // username, password, login, hyperlinks
        Label lblUsername = new Label("Username:");
        Label lblPassword = new Label("Password:"); 
        TextField txtUsername = new TextField();
        TextField txtPassword = new TextField();
        Button btnLogin = new Button("Login");
        
        btnLogin.setOnAction(event -> stage.setScene(scene2));
//        {
//                if (login()) {
//                    System.out.println("yes");
//                    //primaryStage.setScene(scene2);
//                } else {
//                    Alert alert = new Alert(AlertType.ERROR);
//                }
//        });
                
        Hyperlink createAccLink = new Hyperlink("Create account");
        
        createAccLink.setOnAction(event -> createAccount());
        Hyperlink forgotPwdLink = new Hyperlink("Forgot your password?");
        btnLogin.setMaxWidth(Double.MAX_VALUE);
        
        // Subsitute for logo or whatever
        Rectangle rectangle = new Rectangle();
        rectangle.setX(50);        
        rectangle.setY(50);  
        rectangle.setHeight(200);  
        rectangle.setWidth(350);  
        
        Text text = new Text("Logo??");
        text.setFill(Color.WHITESMOKE);
        text.setTextAlignment(TextAlignment.CENTER);
        
        //Gridpane for username, password, login btn
        GridPane gridPane = new GridPane();        
        GridPane.setHalignment(btnLogin, HPos.RIGHT);
        gridPane.setPadding(new Insets(20,20,20,20)); 
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        
        // Adding nodes to GridPane
        gridPane.add(lblUsername, 0, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(btnLogin, 1, 3);
        
  
        StackPane stackPane = new StackPane(rectangle, text);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.getChildren().add(createAccLink);
        vBox.getChildren().add(forgotPwdLink);
        
        // Adding to BorderPane Layout
        borderPane.setCenter(gridPane);
        borderPane.setBottom(vBox);
        borderPane.setTop(stackPane);
        
        // Login page
        Scene scene1 = new Scene(borderPane, 800, 600);
        
        
        
        // Main page after logging in
        Scene scene2 = new Scene();
        
        
        // Create an Account
        
        
        Scene scene3 = new Scene();

        primaryStage.setTitle("DC Password Organizer");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }

    private boolean login() {
            for (int i = 0; i < masterAccounts.size() ; i++) {
                JSONObject masterAccount = (JSONObject) masterAccounts.get(i);
                
                if (masterAccount.get("userName") == userName && masterAccount.get("password") == password) {
                    return true;
                }
            }
            return false;
    }

    private void createAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
