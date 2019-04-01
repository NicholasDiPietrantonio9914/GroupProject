package groupproject;

import groupproject.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.BOTTOM_CENTER;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class LoggedOn {

    private ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();
    private final ObservableList<ChildAccount> data = FXCollections.observableArrayList();
    TableView<ChildAccount> table = new TableView<ChildAccount>();
    private TextField loginTxt;
    private TextField usernameTxt;
    private TextField otherInfoTxt;
    private TextField passwordTxt;

    public void loggedOn(Stage stageLoggedOn) {

        Main main = new Main();

        display();
        
        MenuItem menuItem1 = new MenuItem("Change Master Password");
        MenuItem menuItem2 = new MenuItem("Logout");

        menuItem1.setOnAction(event -> changePassword(stageLoggedOn));

        menuItem2.setOnAction(event -> {
            main.start(stageLoggedOn);
            arrayMasterAccount.setLogged(null);
        });

        MenuButton menubtn = new MenuButton("Menu", null, menuItem1, menuItem2);

        HBox hbox = new HBox(menubtn);

        table.setEditable(true);

        TableColumn loginCol = new TableColumn("LOGIN");
        loginCol.setCellValueFactory(
                new PropertyValueFactory<ChildAccount, String>("login"));
        TableColumn usernameCol = new TableColumn("USERNAME");
        usernameCol.setCellValueFactory(
                new PropertyValueFactory<ChildAccount, String>("userName"));
        TableColumn passwordCol = new TableColumn("PASSWORD");
        passwordCol.setCellValueFactory(
                new PropertyValueFactory<ChildAccount, String>("password"));
        TableColumn otherInfoCol = new TableColumn("OTHER INFO");
        otherInfoCol.setCellValueFactory(
                new PropertyValueFactory<ChildAccount, String>("other"));
        
        
        TableColumn editCol = new TableColumn("EDIT");
        editCol.setCellValueFactory(
                new PropertyValueFactory<ChildAccount, String>("btnEdit"));
        TableColumn removeCol = new TableColumn("REMOVE");
        removeCol.setCellValueFactory(
                new PropertyValueFactory<ChildAccount, Button>("btnRemove"));
        

        
        loginCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        usernameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        passwordCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        otherInfoCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        editCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        removeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));

        loginCol.setResizable(true);
        usernameCol.setResizable(true);
        passwordCol.setResizable(true);
        otherInfoCol.setResizable(true);
        editCol.setResizable(true);
        removeCol.setResizable(true);

        table.setItems(data);
        table.getColumns().addAll(loginCol, usernameCol, passwordCol, otherInfoCol, editCol, removeCol);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.setAlignment(BOTTOM_CENTER);
        //vbox.setVgrow(table, Priority.ALWAYS);

        HBox hb = new HBox();

        loginTxt = new TextField();
        loginTxt.setPromptText("Login");
        loginTxt.setPrefWidth(150);
        usernameTxt = new TextField();
        usernameTxt.setPrefWidth(150);
        usernameTxt.setPromptText("Username");
        passwordTxt = new TextField();
        passwordTxt.setPrefWidth(150);
        passwordTxt.setPromptText("Password");
        otherInfoTxt = new TextField();
        otherInfoTxt.setPromptText("Other");
        otherInfoTxt.setPrefWidth(125);

        Button addbtn = new Button("Add");
        addbtn.setOnAction(event -> add());
        
        hb.getChildren().addAll(loginTxt, usernameTxt, passwordTxt, otherInfoTxt, addbtn);
        hb.setSpacing(3);
        hb.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(table, hb);

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(20, 20, 20, 20));
        bp.setCenter(vbox);
        bp.setRight(hbox);

        Scene scene = new Scene(bp, 800, 600);
        stageLoggedOn.setTitle("DC PASSWORD ORGANIZER");
        stageLoggedOn.setScene(scene);
        stageLoggedOn.show();
    }

    private void changePassword(Stage stageChangePassword) {

        VBox root = new VBox();

        TextField txtNewPswd = new TextField();

        Button btnChange = new Button("Change Password");
        btnChange.setOnAction(event -> {
            arrayMasterAccount.getLogged().setPassword(txtNewPswd.getText());
            loggedOn(stageChangePassword);
        });

        Button btnBack = new Button("Back");
        btnBack.setOnAction(event -> loggedOn(stageChangePassword));

        root.getChildren().addAll(txtNewPswd, btnChange, btnBack);

        Scene scene = new Scene(root, 800, 600);
        stageChangePassword.setTitle("DC Password Organizer: Change Password Of "
                + arrayMasterAccount.getLogged().getUserName());
        stageChangePassword.setScene(scene);
        stageChangePassword.show();
    }

    private void display() {
        data.clear();
        for (int i = 0 ; i < arrayMasterAccount.getLogged().getChildAccounts().size() ; i++) {
            data.add(arrayMasterAccount.getLogged().getChildAccounts().get(i));
        }
    }

    private void add() {
        arrayMasterAccount.getLogged().addChildAccount(new ChildAccount
        (loginTxt.getText(), usernameTxt.getText(), passwordTxt.getText(),
                otherInfoTxt.getText()));
        loginTxt.clear();
        usernameTxt.clear();
        passwordTxt.clear();
        otherInfoTxt.clear();
        display();
    }

}
