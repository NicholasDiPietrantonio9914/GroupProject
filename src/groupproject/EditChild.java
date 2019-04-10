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
public class EditChild {

    public void editChild(Stage stageEditChild) {

        LoggedOn loggedOn = new LoggedOn();
        ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();

        VBox root = new VBox();

        Label lblLog = new Label("Login:");
        TextField txtEditLog = new TextField();
        txtEditLog.setText(arrayMasterAccount.getLogged().getEditChild().getLogin());

        Label lblUser = new Label("Username:");
        TextField txtEditUser = new TextField();
        txtEditLog.setText(arrayMasterAccount.getLogged().getEditChild().getLogin());

        Label lblPass = new Label("Password:");
        TextField txtEditPass = new TextField();
        txtEditLog.setText(arrayMasterAccount.getLogged().getEditChild().getLogin());

        Label lblOther = new Label("Other:");
        TextField txtEditOther = new TextField();
        txtEditLog.setText(arrayMasterAccount.getLogged().getEditChild().getLogin());

        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> {
            arrayMasterAccount.getLogged().setEditChild(null);
            loggedOn.loggedOn(stageEditChild);
        });

        Button btnSave = new Button("Save Changes");
        btnSave.setOnAction(event -> {
            try {
                arrayMasterAccount.getLogged().editEditChild(txtEditLog.getText(),
                        txtEditUser.getText(), txtEditPass.getText(), txtEditOther.getText());
                
                arrayMasterAccount.createJson();
                loggedOn.loggedOn(stageEditChild);
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account Edit Error");
                alert.setHeaderText("Login Edit Error");
                alert.setContentText(ex.toString());
                Optional<ButtonType> result = alert.showAndWait();
            }
            arrayMasterAccount.getLogged().setEditChild(null);

        });

        root.getChildren().addAll(lblLog, txtEditLog, lblUser, txtEditUser,
                lblPass, txtEditPass, lblOther, txtEditOther, btnBack, btnSave);
        Scene scene = new Scene(root);

        stageEditChild.setScene(scene);
        stageEditChild.setTitle("DC PASSWORD ORGANIZER");
        stageEditChild.show();
    }

}
