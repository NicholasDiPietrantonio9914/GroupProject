package groupproject;

import groupproject.model.*;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * This class contains the GUI logic for the Create an Account scene
 *
 * @author Phuong Cam
 */
public class CreateAccount {

    private MasterAccount masterAccount;
    private ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();

    /**
     * Entry point for the create an account scene
     *
     * @param stageCreate The create an account stage which the application
     * scene can be set
     */
    public void createAccount(Stage stageCreate) {

        Main main = new Main();

        Label lblTitle = new Label("Create an Account");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setId("title");

        Line line = new Line();
        line.setStartX(100.0);
        line.setStartY(0.0);
        line.setEndX(600.0);
        line.setEndY(0.0);
        line.setStroke(Color.LIGHTGREY);

        Label lblUsername = new Label("Username ");
        Label lblPassword = new Label("Password ");

        TextField txtUserName = new TextField();
        PasswordField txtPassword = new PasswordField();

        Label lblQn = new Label("Security Question ");
        Label lblAns = new Label("Answer ");

        ComboBox secureQuestions = new ComboBox();
        secureQuestions.getItems().addAll("What is the name of your first pet?",
                "Is your name Paul?");
        secureQuestions.setValue("What is the name of your first pet?");
        TextField txtAns = new TextField();

        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> {
            main.start(stageCreate);
        });

        Button btnCreate = new Button("Create");
        btnCreate.setOnAction((ActionEvent event) -> {
            if (createAcc(txtUserName.getText(), txtPassword.getText(),
                    secureQuestions.getValue().toString(), txtAns.getText())) {
                main.start(stageCreate);
            }
        });

        VBox vbInfo = new VBox();
        vbInfo.getChildren().addAll(lblUsername, txtUserName, lblPassword, txtPassword,
                lblQn, secureQuestions, lblAns, txtAns);
        vbInfo.setSpacing(10);
        vbInfo.setPadding(new Insets(10, 225, 10, 225));

        HBox btns = new HBox(300);
        btns.setSpacing(5);
        btns.setPadding(new Insets(0, 225, 0, 0));
        btns.getChildren().addAll(btnBack, btnCreate);
        btns.setAlignment(Pos.CENTER_RIGHT);

        VBox header = new VBox(lblTitle, line);
        header.setAlignment(Pos.TOP_CENTER);
        header.setSpacing(20);
        header.setPadding(new Insets(50, 0, 20, 0));

        VBox centrePane = new VBox();
        centrePane.getChildren().addAll(header, vbInfo, btns);
        centrePane.setAlignment(Pos.TOP_CENTER);
        centrePane.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setCenter(centrePane);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("custom.css");

        stageCreate.setTitle("DC Password Organizer: Create Account");
        stageCreate.setScene(scene);
        stageCreate.show();
    }

    /**
     * This method creates a new master account
     *
     * @param userName the username of the master account
     * @param password the password of the master account
     * @param securityQuestion the master account security question
     * @param securityAnswer the master account security answer
     * @return true and creates a master account, false and returns an alert
     */
    public boolean createAcc(String userName, String password,
            String securityQuestion, String securityAnswer) {
        try {
            masterAccount = new MasterAccount(userName, password,
                    securityQuestion, securityAnswer);
            arrayMasterAccount.accountExists(masterAccount);
            return true;
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Create Account Error");
            alert.setHeaderText("Entered Account Information Not Valid");
            alert.setContentText(ex.getMessage());
            Optional<ButtonType> result = alert.showAndWait();
            return false;
        }
    }

}
