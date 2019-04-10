package groupproject;

import groupproject.model.*;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class ForgotPassword {

    ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();

    public void forgotPasswordUser(Stage stagePassword) {

        Main main = new Main();
        VBox root = new VBox();

        TextField txtUserName = new TextField();

        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> main.start(stagePassword));

        Button btnNext = new Button("Next");
        btnNext.setOnAction(event -> {
            if (setAccount(txtUserName.getText())) {
                forgotPasswordSecurity(stagePassword);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Username Entered");
                alert.setHeaderText("Enter a Valid Username");
                Optional<ButtonType> result = alert.showAndWait();
            }

        });

        root.getChildren().addAll(txtUserName, btnBack, btnNext);

        Scene scene = new Scene(root, 800, 600);
        stagePassword.setTitle("DC Password Organizer: Forgot Password");
        stagePassword.setScene(scene);
        stagePassword.show();
    }

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

    public void forgotPasswordSecurity(Stage stagePassword) {

        Main main = new Main();
        VBox root = new VBox();

        Label lblSecurityQuestion = new Label(arrayMasterAccount.getLogged().getSecurityQuestion());
        TextField txtSecAns = new TextField();

        Button btnGet = new Button("Get Password");
        btnGet.setOnAction(event -> {
            if (txtSecAns.getText().equals(arrayMasterAccount.getLogged().getSecurityAnswer())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Forgot Password");
                alert.setHeaderText("Your Password is:");
                alert.setContentText(arrayMasterAccount.getLogged().getPassword());
                Optional<ButtonType> result = alert.showAndWait();
                arrayMasterAccount.setLogged(null);
                main.start(stagePassword);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Security Answer");
                alert.setHeaderText("The Answer to Your Security Question is Invalid:");
                Optional<ButtonType> result = alert.showAndWait();
            }
        });

        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> {
            arrayMasterAccount.setLogged(null);
            main.start(stagePassword);
        });
        root.getChildren().addAll(lblSecurityQuestion, txtSecAns, btnGet, btnBack);

        Scene scene = new Scene(root, 800, 600);
        stagePassword.setTitle("DC Password Organizer: Forgot Password");
        stagePassword.setScene(scene);
        stagePassword.show();
    }
}
