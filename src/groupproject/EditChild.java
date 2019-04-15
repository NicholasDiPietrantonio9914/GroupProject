package groupproject;

import groupproject.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class contains the GUI logic for the Edit Page where the user can edit
 * their individual login information
 *
 * @author Phuong Cam
 */
public class EditChild {

    public void editChild(Stage stageEditChild) {

        Label lblTitle = new Label("Edit Login Information");
        lblTitle.setId("title");

        LoggedOn loggedOn = new LoggedOn();
        ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();

        Label lblLog = new Label("Login:");
        TextField txtEditLog = new TextField();
        txtEditLog.setText(arrayMasterAccount.getLogged().getEditChild().getLogin());

        Label lblUsername = new Label("Username:");
        TextField txtEditUser = new TextField();
        txtEditUser.setText(arrayMasterAccount.getLogged().getEditChild().getUserName());

        Label lblPassword = new Label("Password:");
        TextField txtEditPass = new TextField();
        txtEditPass.setText(arrayMasterAccount.getLogged().getEditChild().getPassword());

        Label lblOther = new Label("Other:");
        TextField txtEditOther = new TextField();
        txtEditOther.setText(arrayMasterAccount.getLogged().getEditChild().getOther());

        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> {
            arrayMasterAccount.getLogged().setEditChild(null);
            loggedOn.loggedOn(stageEditChild);
        });

        Button btnSave = new Button("Save");
        btnSave.setOnAction(event -> {
            try {
                arrayMasterAccount.getLogged().editEditChild(txtEditLog.getText(),
                        txtEditUser.getText(), txtEditPass.getText(), txtEditOther.getText());
                arrayMasterAccount.createJson();
                arrayMasterAccount.getLogged().setEditChild(null);
                loggedOn.loggedOn(stageEditChild);
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account Edit Error");
                alert.setHeaderText("Login Edit Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }

        });

        HBox btns = new HBox(btnBack, btnSave);
        btns.setSpacing(5);
        btns.setAlignment(Pos.CENTER_RIGHT);

        VBox vbInfo = new VBox();
        vbInfo.getChildren().addAll(lblTitle, lblLog, txtEditLog, lblUsername, txtEditUser,
                lblPassword, txtEditPass, lblOther, txtEditOther, btns);
        vbInfo.setAlignment(Pos.CENTER_LEFT);
        vbInfo.setSpacing(10);

        StackPane root = new StackPane(vbInfo);
        root.setPadding(new Insets(200));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("custom.css");

        stageEditChild.setScene(scene);
        stageEditChild.setTitle("DC Password Organizer: Edit");
        stageEditChild.show();
    }

}
