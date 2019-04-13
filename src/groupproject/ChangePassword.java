package groupproject;

import groupproject.model.*;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class contains the GUI logic for the Change password scene
 *
 * @author Nicholas Di Pietrantonio
 */
public class ChangePassword {

    /**
     * An entry point for the application
     *
     * @param stageChangePassword stage where the scene is set
     */
    public void changePassword(Stage stageChangePassword) {

        ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();
        LoggedOn loggedOn = new LoggedOn();

        Text textCurrentPwd = new Text(arrayMasterAccount.getLogged().getPassword());
        Text lblCurrentPwd = new Text("Your current password: ");

        Label lblPass = new Label("New Password:");
        PasswordField txtNewPwd = new PasswordField();

        Button btnChange = new Button("Change Password");
        btnChange.setOnAction(event -> {
            try {
                arrayMasterAccount.getLogged().setPassword(txtNewPwd.getText());
                arrayMasterAccount.createJson();
                loggedOn.loggedOn(stageChangePassword);
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Password");
                alert.setHeaderText("Entered Password Not Valid");
                alert.setContentText(ex.getMessage());
                Optional<ButtonType> result = alert.showAndWait();
            }
        });

        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> loggedOn.loggedOn(stageChangePassword));

        HBox btns = new HBox(btnBack, btnChange);
        btns.setSpacing(5);
        btns.setAlignment(Pos.CENTER_RIGHT);

        HBox displayPwd = new HBox(lblCurrentPwd, textCurrentPwd);
        displayPwd.setId("text");

        Label lblTitle = new Label("Edit Account Password");
        lblTitle.setId("title");

        VBox vbInput = new VBox();
        vbInput.getChildren().addAll(
                lblTitle, displayPwd, lblPass, txtNewPwd, btns);
        vbInput.setSpacing(10);

        StackPane root = new StackPane(vbInput);
        vbInput.setPadding(new Insets(200));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("custom.css");

        stageChangePassword.setTitle("DC Password Organizer: Change Password For "
                + arrayMasterAccount.getLogged().getUserName());
        stageChangePassword.setScene(scene);
        stageChangePassword.show();
    }
}
