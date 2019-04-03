
package groupproject;

import groupproject.model.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

        PasswordField txtNewPswd = new PasswordField();

        Button btnChange = new Button("Change Password");
        btnChange.setOnAction(event -> {
            arrayMasterAccount.getLogged().setPassword(txtNewPswd.getText());
            loggedOn.loggedOn(stageChangePassword);
        });

        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> loggedOn.loggedOn(stageChangePassword));

        root.getChildren().addAll(txtNewPswd, btnChange, btnBack);

        Scene scene = new Scene(root, 800, 600);
        stageChangePassword.setTitle("DC Password Organizer: Change Password Of "
                + arrayMasterAccount.getLogged().getUserName());
        stageChangePassword.setScene(scene);
        stageChangePassword.show();
    }
}
