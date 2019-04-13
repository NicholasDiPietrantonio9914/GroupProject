package groupproject;

import groupproject.model.*;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class contains the GUI logic for the Login Page
 *
 * @author Joey
 */
public class Main extends Application {

    /**
     * The main entry point for the Application
     *
     * @param primaryStage primary stage for this application where application
     * scene is set
     */
    @Override
    public void start(Stage primaryStage) {

        ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();
        CreateAccount createAccount = new CreateAccount();
        ForgotPassword forgotPassword = new ForgotPassword();
        LoggedOn loggedOn = new LoggedOn();

        ImageView dpImg = new ImageView(new Image("images/dc2.png"));

        Label lblUsername = new Label("Username ");
        Label lblPassword = new Label("Password ");
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

        // display logo
        StackPane sp = new StackPane(dpImg);
        sp.setPadding(new Insets(10, 0, 0, 0));

        VBox vbUsername = new VBox(lblUsername, txtUsername);
        VBox vbPassword = new VBox(lblPassword, txtPassword);

        HBox hbBtn = new HBox(btnLogin);
        hbBtn.setAlignment(Pos.CENTER_RIGHT);

        VBox vbLoginField = new VBox(vbUsername, vbPassword, hbBtn);
        vbLoginField.setSpacing(10);
        vbLoginField.setPadding(new Insets(20, 150, 20, 175));
        vbLoginField.setAlignment(Pos.CENTER);

        BorderPane centerPane = new BorderPane();
        centerPane.setTop(sp);
        centerPane.setCenter(vbLoginField);
        centerPane.setId("root");

        VBox bottomPane = new VBox();
        bottomPane.setAlignment(Pos.CENTER_RIGHT);
        bottomPane.getChildren().addAll(createAccLink, forgotPwdLink);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(75, 100, 100, 100));
        root.setCenter(centerPane);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("custom.css");

        primaryStage.setTitle("DC Password Organizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This is the main method which launches the GUI
     *
     * @param args the command line options
     */
    public static void main(String[] args) {
        ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();
        arrayMasterAccount.loadJson();
        launch(args);
    }
}
