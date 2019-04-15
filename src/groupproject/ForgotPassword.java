package groupproject;

import groupproject.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * This class contains the GUI logic for the Forgot Password Scene
 *
 * @author Phuong Cam
 */
public class ForgotPassword {

    ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();

    public void forgotPasswordUser(Stage stagePassword) {

        Main main = new Main();

        ImageView dpImg = new ImageView(new Image("images/forgotpass.png"));
        dpImg.setFitWidth(225);
        dpImg.setFitHeight(225);

        Line line = new Line();
        line.setStartX(0.0);
        line.setStartY(150.0);
        line.setEndX(0.0);
        line.setEndY(-150.0);
        line.setStroke(Color.LIGHTGREY);

        HBox displayImgs = new HBox(dpImg, line);
        displayImgs.setAlignment(Pos.CENTER_LEFT);
        displayImgs.setPadding(new Insets(0, 50, 0, 0));

        Text textTitle = new Text("Forgot your password?");
        textTitle.setId("title");

        Text textInstruction = new Text("Please enter your username");
        VBox header = new VBox(textTitle, textInstruction);
        header.setSpacing(5);

        Label lblUsername = new Label("Username");
        TextField txtUsername = new TextField();

        Button btnBack = new Button("Back");

        btnBack.setOnAction(event -> main.start(stagePassword));

        Button btnNext = new Button("Next");

        btnNext.setOnAction(event
                -> {
            if (setAccount(txtUsername.getText())) {
                forgotPasswordSecurity(stagePassword);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Username Entered");
                alert.setHeaderText("Enter a Valid Username");
                alert.showAndWait();
            }

        }
        );

        HBox btns = new HBox(btnBack, btnNext);
        btns.setAlignment(Pos.CENTER);
        btns.setPadding(new Insets(0, 0, 0, 150));
        btns.setSpacing(5);

        VBox input = new VBox(lblUsername, txtUsername);
        input.setPadding(new Insets(10, 25, 10, 0));
        input.setAlignment(Pos.CENTER_LEFT);

        VBox vbInfo = new VBox(header, input, btns);
        vbInfo.setSpacing(25);
        vbInfo.setAlignment(Pos.CENTER_LEFT);

        HBox centrePane = new HBox(displayImgs, vbInfo);
        centrePane.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(centrePane);
        root.setPadding(new Insets(0, 50, 0, 50));

        Scene scene = new Scene(root, 800, 600);

        stagePassword.setTitle(
                "DC Password Organizer: Forgot Password");
        stagePassword.setScene(scene);

        scene.getStylesheets().add("custom.css");
        stagePassword.show();
    }

    /**
     * This method checks whether if the username already exists in the master
     * account arraylist
     *
     * @param username the username associated with the forgotten password
     * @return true if account exists, false otherwise
     */
    private boolean setAccount(String username) {
        for (int i = 0; i < arrayMasterAccount.getMasterAccounts().size(); i++) {
            if (arrayMasterAccount.getMasterAccounts().get(i).getUserName().equals(
                    username)) {
                arrayMasterAccount.setLogged(arrayMasterAccount.getMasterAccounts().get(i));
                return true;
            }
        }
        return false;
    }

    /**
     * This class contains the GUI logic for the verification of the security
     * question
     *
     * @param stagePassword Stage object which is passed to the
     * forgotPasswordSecurity method of the scene class
     */
    public void forgotPasswordSecurity(Stage stagePassword) {

        Main main = new Main();

        ImageView dpImg = new ImageView(new Image("images/forgotpass1.png"));
        dpImg.setFitWidth(225);
        dpImg.setFitHeight(225);

        Line line = new Line();
        line.setStartX(0.0);
        line.setStartY(150.0);
        line.setEndX(0.0);
        line.setEndY(-150.0);
        line.setStroke(Color.LIGHTGREY);

        HBox displayImgs = new HBox(dpImg, line);
        displayImgs.setAlignment(Pos.CENTER_LEFT);
        displayImgs.setSpacing(20);

        Text textQ = new Text("Enter your security answer");
        textQ.setTextAlignment(TextAlignment.CENTER);
        textQ.setId("title");

        VBox header = new VBox(textQ);
        header.setSpacing(5);
        header.setPadding(new Insets(0, 0, 0, 50));

        Label lblSecurityQuestion = new Label(arrayMasterAccount.getLogged().getSecurityQuestion());
        TextField txtSecAns = new TextField();

        Button btnGet = new Button("Get Password");
        btnGet.setOnAction(event -> {
            if (txtSecAns.getText().equals(arrayMasterAccount.getLogged().getSecurityAnswer())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Forgot Password");
                alert.setHeaderText("Your Password is:");
                alert.setContentText(arrayMasterAccount.getLogged().getPassword());
                alert.showAndWait();
                arrayMasterAccount.setLogged(null);
                main.start(stagePassword);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Security Answer");
                alert.setHeaderText("The Answer to Your Security Question is Invalid:");
                alert.showAndWait();
            }
        });

        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event -> {
            arrayMasterAccount.setLogged(null);
            main.start(stagePassword);
        });

        VBox input = new VBox(header, lblSecurityQuestion, txtSecAns);
        input.setPadding(new Insets(0, 50, 10, 50));
        input.setAlignment(Pos.CENTER_LEFT);

        HBox btns = new HBox(btnCancel, btnGet);
        btns.setAlignment(Pos.CENTER);
        btns.setPadding(new Insets(0, 0, 0, 150));
        btns.setSpacing(5);

        VBox info = new VBox(header, input, btns);
        info.setSpacing(25);
        info.setAlignment(Pos.CENTER_LEFT);

        HBox centrePane = new HBox(displayImgs, info);
        centrePane.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(centrePane);
        root.setPadding(new Insets(0, 50, 0, 50));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("custom.css");

        stagePassword.setTitle("DC Password Organizer: Forgot Password");
        stagePassword.setScene(scene);
        stagePassword.show();
    }
}
