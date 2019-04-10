package groupproject;

import groupproject.model.*;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class CreateAccount {

    private MasterAccount masterAccount;
    private ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();

    public void createAccount(Stage stageCreate) {

        Main main = new Main();

        VBox root = new VBox();

        TextField txtUserName = new TextField();
        PasswordField txtPassword = new PasswordField();
        ComboBox secureQuestions = new ComboBox();
        secureQuestions.getItems().addAll("What is the name of your first pet?", 
                "Is your name Paul?");
        secureQuestions.setValue("What is the name of your first pet?");
        TextField txtSecAns = new TextField();
        Button btnBack = new Button("Back");

        btnBack.setOnAction(event -> {
            main.start(stageCreate);
        });

        Button btnCreate = new Button("Create");
        btnCreate.setOnAction((ActionEvent event) -> {
            if (createAcc(txtUserName.getText(), txtPassword.getText(), 
            secureQuestions.getValue().toString(), txtSecAns.getText())) {
                main.start(stageCreate);
            }
        });

        root.getChildren().addAll(txtUserName, txtPassword, secureQuestions,
                txtSecAns, btnCreate, btnBack);
        Scene scene = new Scene(root, 800, 600);
        stageCreate.setTitle("DC Password Organizer: Create Account");
        stageCreate.setScene(scene);
        stageCreate.show();
    }

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
            alert.setContentText(ex.toString());
            Optional<ButtonType> result = alert.showAndWait();
            return false;
        }
    }

}
