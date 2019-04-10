package groupproject;

import groupproject.model.*;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
        
        Text txtC = new Text("Create an Account");
        txtC.setId("fancy");

        Label lblUserName = new Label("Username ");
        Label lblPassword = new Label("Password ");
        TextField txtUserName = new TextField();
        PasswordField txtPassword = new PasswordField();
        Label lblQn = new Label("Security Question ");
        Label lblAns = new Label("Answer ");
        ComboBox secureQuestions = new ComboBox();
        secureQuestions.getItems().addAll("What is the name of your first pet?", 
                "Is your name Paul?");
        secureQuestions.setValue("What is the name of your first pet?");
        TextField txtSecAns = new TextField();
        Hyperlink linkBack = new Hyperlink("< Back");

        linkBack.setOnAction(event -> { main.start(stageCreate);
        });

        Button btnCreate = new Button("Create");
        btnCreate.setOnAction((ActionEvent event) -> {
            System.out.println(secureQuestions.getValue().toString());
            if (createAcc(txtUserName.getText(), txtPassword.getText(), 
            secureQuestions.getValue().toString(), txtSecAns.getText())) {
                main.start(stageCreate);
            }
        });

//        VBox root = new VBox();
//        root.getChildren().addAll(lblUserName, txtUserName, lblPassword,
//                txtPassword, secureQuestions,txtSecAns, btnCreate, btnBack);
//        root.setAlignment(Pos.CENTER);

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        
        root.setHgap(55);
        root.setVgap(10);
        root.add(lblUserName, 0, 0);
        root.add(txtUserName, 1, 0);
        root.add(lblPassword, 0, 1);
        root.add(txtPassword, 1, 1);
        root.add(lblQn, 0, 2);
        root.add(secureQuestions, 1, 2);
        root.add(lblAns, 0, 3);
        root.add(txtSecAns, 1, 3);
        
        
        HBox hb = new HBox(300);
        hb.setPadding(new Insets(20));
        hb.getChildren().addAll(linkBack, btnCreate);
        hb.setAlignment(Pos.CENTER);
        
        VBox vb = new VBox();
        vb.getChildren().addAll(root, hb);
        //vb.setAlignment(Pos.CENTER);
        
        BorderPane bp = new BorderPane();
        //bp.setPadding(new Insets(50,50,50,50));
        bp.setCenter(vb);
        bp.setTop(txtC);
        
        Scene scene = new Scene(bp, 800, 600);
        scene.getStylesheets().add("custom.css");
        
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
            System.out.println(ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Create Account Error");
            alert.setHeaderText("Entered Account Information Not Valid");
            alert.setContentText(ex.toString());
            Optional<ButtonType> result = alert.showAndWait();
            return false;
        }
    }

}
