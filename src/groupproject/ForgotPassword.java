package groupproject;

import javafx.application.Application;
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
public class ForgotPassword {
    
    public void forgotPassword(Stage stagePassword) {
        
        Main main = new Main();
        VBox root = new VBox();
        
        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> main.start(stagePassword));
        root.getChildren().addAll(btnBack);
        
        Scene scene = new Scene(root, 800, 600);
        stagePassword.setTitle("DC Password Organizer: Forgot Password");
        stagePassword.setScene(scene);
        stagePassword.show();
    }
}