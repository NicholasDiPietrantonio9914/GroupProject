package groupproject;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class CreateAccount {
    
    private MasterAccount masterAccount;
    Scene scene;
    
    public void createAccount(Stage stageCreate) {
        Main main = new Main();
        VBox root = new VBox();

        TextField txtUserName = new TextField();
        TextField txtPassword = new TextField();
        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> {
            main.start(stageCreate);
        });
        
        Button btnCreate = new Button("Create");
        btnCreate.setOnAction((ActionEvent event) -> {
            createAcc(txtUserName.getText(), txtPassword.getText());
            main.addMaster(masterAccount);
            main.start(stageCreate);
        });
        
        root.getChildren().addAll(txtUserName, txtPassword, btnCreate, btnBack);
        scene = new Scene(root);
        stageCreate.setTitle("DC Password Organizer: Forgot Password");
        stageCreate.setScene(scene);
        stageCreate.show();
    }

    public void createAcc(String userName, String password) {
        masterAccount = new MasterAccount(userName, password, "a", "a");
    }
    
}
