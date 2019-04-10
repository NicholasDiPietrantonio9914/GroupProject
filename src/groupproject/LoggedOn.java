package groupproject;

import groupproject.model.*;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.BOTTOM_CENTER;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private TableView<ChildAccount> table = new TableView<ChildAccount>();
    private TextField loginTxt;
    private TextField usernameTxt;
    private TextField otherInfoTxt;
    private TextField passwordTxt;

    public void loggedOn(Stage stageLoggedOn) {

        Main main = new Main();
        EditChild editChild = new EditChild();
        ChangePassword changePassword = new ChangePassword();

        display();

        MenuItem menuItem1 = new MenuItem("Change Master Password");
        MenuItem menuItem2 = new MenuItem("Logout");

        menuItem1.setOnAction(event -> changePassword.changePassword(stageLoggedOn));

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

        Button btnRemove = new Button("Remove");
        btnRemove.setOnAction(event -> {
            if (!(table.getSelectionModel().getSelectedIndex() == -1)) {
                for (int i = 0; i < arrayMasterAccount.getLogged().
                        getChildAccounts().size(); i++) {
                    if (data.get(table.getSelectionModel().getSelectedIndex()).getLogin().equals(
                            arrayMasterAccount.getLogged().getChildAccounts().get(i).getLogin())) {
                        arrayMasterAccount.getLogged().getChildAccounts().remove(i);
                    }
                }
                table.getItems().remove(table.getSelectionModel().getSelectedIndex());
                arrayMasterAccount.createJson();
            }
        });

        Button btnEdit = new Button("Edit");
        btnEdit.setOnAction(event -> {
            if (!(table.getSelectionModel().getSelectedIndex() == -1)) {
                arrayMasterAccount.getLogged().setEditChild(
                        data.get(table.getSelectionModel().getSelectedIndex()));
                editChild.editChild(stageLoggedOn);
            }
        });

        loginCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        usernameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        passwordCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        otherInfoCol.prefWidthProperty().bind(table.widthProperty().multiply(0.40));

        table.setItems(data);
        table.getColumns().addAll(loginCol, usernameCol, passwordCol, otherInfoCol);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.setAlignment(BOTTOM_CENTER);

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

        hb.getChildren().addAll(loginTxt, usernameTxt, passwordTxt, otherInfoTxt,
                addbtn, btnRemove, btnEdit);
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

    private void display() {
        data.clear();
        for (int i = 0; i < arrayMasterAccount.getLogged().getChildAccounts().size(); i++) {
            data.add(arrayMasterAccount.getLogged().getChildAccounts().get(i));
        }
    }

    private void add() {
        try {
            arrayMasterAccount.getLogged().addChildAccount(new ChildAccount(loginTxt.getText(), usernameTxt.getText(), passwordTxt.getText(),
                    otherInfoTxt.getText()));
            loginTxt.clear();
            usernameTxt.clear();
            passwordTxt.clear();
            otherInfoTxt.clear();
            arrayMasterAccount.createJson();
            display();
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Account Addition Error");
            alert.setHeaderText("Entered Account Information Not Valid");
            alert.setContentText(ex.toString());
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
}
