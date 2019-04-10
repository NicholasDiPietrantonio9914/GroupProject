package groupproject;

import groupproject.model.*;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class ChangePassword {

    public void changePassword(Stage stageChangePassword) {

        ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();
        LoggedOn loggedOn = new LoggedOn();

        VBox root = new VBox();

        Label lblPass = new Label("New Password:");
        PasswordField txtNewPswd = new PasswordField();

        Button btnChange = new Button("Change Password");
        btnChange.setOnAction(event -> {
            try {
                arrayMasterAccount.getLogged().setPassword(txtNewPswd.getText());
                arrayMasterAccount.createJson();
                loggedOn.loggedOn(stageChangePassword);
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Password");
                alert.setHeaderText("Entered Password Not Valid");
                alert.setContentText(ex.toString());
                Optional<ButtonType> result = alert.showAndWait();
            }
        });

        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> loggedOn.loggedOn(stageChangePassword));

        root.getChildren().addAll(lblPass, txtNewPswd, btnChange, btnBack);

        Scene scene = new Scene(root, 800, 600);
        stageChangePassword.setTitle("DC Password Organizer: Change Password Of "
                + arrayMasterAccount.getLogged().getUserName());
        stageChangePassword.setScene(scene);
        stageChangePassword.show();
    }
}
