package groupproject;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
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



/**
 *
 * @author Joey
 */
public class Login {
    
    private ArrayList<MasterAccount> masterAccounts = new ArrayList<>();
      
    public Scene Login(){
        
        CreateAccount createAccount = new CreateAccount();
        ForgotPassword forgotPassword = new ForgotPassword();
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,50,50,50));
        
        // username, password, login, hyperlinks
        Label lblUsername = new Label("Username:");
        Label lblPassword = new Label("Password:"); 
        TextField txtUsername = new TextField();
        TextField txtPassword = new TextField();
        Button btnLogin = new Button("Login");
        
        btnLogin.setOnAction(event -> {
                if (login(txtUsername.getText(), txtPassword.getText())) {
                    System.out.println("yes");
                    //primaryStage.setScene(scene2);
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                }
        });
                
        Hyperlink createAccLink = new Hyperlink("Create account");
        createAccLink.setOnAction(event -> createAccount.createAccount(primaryStage));
        
        Hyperlink forgotPwdLink = new Hyperlink("Forgot your password?");
        forgotPwdLink.setOnAction(event -> forgotPassword.forgotPassword(primaryStage));
        
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
        Scene scene = new Scene(borderPane, 800, 600);

        primaryStage.setTitle("DC Password Organizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean login(String userName, String password) {
        System.out.println(masterAccounts);
        System.out.println(masterAccounts.size());
            for (int i = 0; i < masterAccounts.size() ; i++) {
                if (masterAccounts.get(i).getUserName() == userName &&
                        masterAccounts.get(i).getPassword() == password) {
                    return true;
                }
            }
            return false;
    }
    
    public void addMaster(MasterAccount masterAccount) {
        masterAccounts.add(masterAccount);
    }

}

