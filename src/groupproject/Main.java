package groupproject;

import groupproject.model.*;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class Main extends Application{
      
    @Override
    public void start(Stage primaryStage){
        
        ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();
        CreateAccount createAccount = new CreateAccount();
        ForgotPassword forgotPassword = new ForgotPassword();
        LoggedOn loggedOn = new LoggedOn();
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(100,100,100,100));
        
        // username, password, login, hyperlinks
        
        Image image = new Image("images/dc.png");
        ImageView displayImg = new ImageView(image);
        displayImg.setFitWidth(200);
        displayImg.setFitHeight(250);
        
        Label lblUsername = new Label("Username");
        Label lblPassword = new Label("Password"); 
        TextField txtUsername = new TextField();
        PasswordField txtPassword = new PasswordField();
        Button btnLogin = new Button("Login");
        
        btnLogin.setOnAction((ActionEvent event) -> {
            if (arrayMasterAccount.login(txtUsername.getText(), txtPassword.getText())) {
                loggedOn.loggedOn(primaryStage);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Entered Login Information Not Valid");
                alert.setContentText("Please enter valid login information");
                Optional<ButtonType> result = alert.showAndWait();
            }
        });
                
        Hyperlink createAccLink = new Hyperlink("Create account");
        createAccLink.setOnAction(event -> createAccount.createAccount(primaryStage));
        
        Hyperlink forgotPwdLink = new Hyperlink("Forgot your password?");
        forgotPwdLink.setOnAction(event -> forgotPassword.forgotPasswordUser(primaryStage));
        
        btnLogin.setMaxWidth(Double.MAX_VALUE);
        
        //Gridpane for username, password, login btn
        StackPane sp = new StackPane(displayImg);
        GridPane gridPane = new GridPane();        
        GridPane.setHalignment(btnLogin, HPos.RIGHT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        
        GridPane center = new GridPane();
        center.setPrefHeight(200);
        center.setPrefWidth(100);
        center.setAlignment(Pos.CENTER);
        center.add(sp, 0, 0);
        center.add(gridPane, 0, 1);
        center.setId("root");  
        
        // Adding nodes to GridPane
        gridPane.add(lblUsername, 0, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtUsername, 1, 0, 5, 1);
        gridPane.add(txtPassword, 1, 1, 5, 1);
        gridPane.add(btnLogin, 5, 3);
        
  
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.getChildren().add(createAccLink);
        vBox.getChildren().add(forgotPwdLink);

        
        // Adding to BorderPane Layout
        borderPane.setCenter(center);
        borderPane.setBottom(vBox);
        //borderPane.setPadding(new Insets(100,100,1000,0));
        
        // Login page
        Scene scene = new Scene(borderPane, 800, 600);
        scene.getStylesheets().add("custom.css");

        primaryStage.setTitle("DC Password Organizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();
        arrayMasterAccount.loadJson();
        launch(args);
    }
}
