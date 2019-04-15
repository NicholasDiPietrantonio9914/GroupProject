package groupproject;

import groupproject.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.BOTTOM_CENTER;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class represents the GUI for the homepage
 *
 * @author Phuong Cam
 */
public class LoggedOn {

    private ArrayMasterAccount arrayMasterAccount = new ArrayMasterAccount();
    private final ObservableList<ChildAccount> data = FXCollections.observableArrayList();
    private TableView<ChildAccount> table = new TableView<>();
    private TextField loginTxt;
    private TextField usernameTxt;
    private TextField otherInfoTxt;
    private TextField passwordTxt;

    public void loggedOn(Stage stageLoggedOn) {

        Main main = new Main();
        EditChild editChild = new EditChild();
        ChangePassword changePassword = new ChangePassword();

        display();

        MenuItem menu1 = new MenuItem("Change Master Password");
        MenuItem menu2 = new MenuItem("Logout");

        menu1.setOnAction(event -> changePassword.changePassword(stageLoggedOn));

        menu2.setOnAction(event -> {
            main.start(stageLoggedOn);
            arrayMasterAccount.setLogged(null);
        });

        Text txtTitle = new Text("My Logins");
        txtTitle.setId("title");

        ImageView dpImg = new ImageView(new Image("images/dc2.png"));
        dpImg.setFitWidth(30);
        dpImg.setFitHeight(45);

        ImageView displayImg2 = new ImageView(new Image("images/remove.png"));
        ImageView displayImg3 = new ImageView(new Image("images/edit.png"));
        ImageView displayImg4 = new ImageView(new Image("images/add.png"));

        MenuButton btnMenu = new MenuButton("Menu", null, menu1, menu2);

        HBox topPane = new HBox(dpImg, txtTitle, btnMenu);
        topPane.setSpacing(225);
        topPane.setAlignment(Pos.CENTER);

        table.setEditable(true);

        TableColumn loginCol = new TableColumn("LOGIN");
        loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));
        TableColumn usernameCol = new TableColumn("USERNAME");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        TableColumn passwordCol = new TableColumn("PASSWORD");
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        TableColumn otherInfoCol = new TableColumn("OTHER INFO");
        otherInfoCol.setCellValueFactory(new PropertyValueFactory<>("other"));

        Button btnRemove = new Button("", displayImg2);
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

        Button btnEdit = new Button("", displayImg3);
        btnEdit.setOnAction(event -> {
            if (!(table.getSelectionModel().getSelectedIndex() == -1)) {
                for (int i = 0; i < arrayMasterAccount.getLogged().getChildAccounts().size(); i++) {
                    if (data.get(table.getSelectionModel().getSelectedIndex()).getLogin().equals(
                            arrayMasterAccount.getLogged().getChildAccounts().get(i).getLogin())) {

                        arrayMasterAccount.getLogged().setEditChild(
                                arrayMasterAccount.getLogged().getChildAccounts().get(i));
                        editChild.editChild(stageLoggedOn);
                    }
                }
            }
        });

        loginCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        usernameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        passwordCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        otherInfoCol.prefWidthProperty().bind(table.widthProperty().multiply(0.24));

        loginCol.setResizable(true);
        usernameCol.setResizable(true);
        passwordCol.setResizable(true);
        otherInfoCol.setResizable(true);

        table.setItems(data);
        table.getColumns().addAll(loginCol, usernameCol, passwordCol, otherInfoCol);

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
        otherInfoTxt.setPrefWidth(150);

        Button addbtn = new Button("", displayImg4);
        addbtn.setOnAction(event -> add());

        HBox hbInput = new HBox();
        hbInput.getChildren().addAll(loginTxt, usernameTxt, passwordTxt,
                otherInfoTxt, addbtn, btnEdit, btnRemove);
        hbInput.setSpacing(3);
        hbInput.setAlignment(Pos.CENTER_LEFT);

        VBox centrePane = new VBox();
        centrePane.setSpacing(5);
        centrePane.setPadding(new Insets(25, 25, 65, 25));
        centrePane.setAlignment(BOTTOM_CENTER);
        centrePane.getChildren().addAll(hbInput, table);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setCenter(centrePane);
        root.setTop(topPane);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("custom.css");
        stageLoggedOn.setTitle("DC Password Organizer: "
                + arrayMasterAccount.getLogged().getUserName());
        stageLoggedOn.setScene(scene);
        stageLoggedOn.show();
    }

    /**
     * This method obtains the login data from the JSON array and displays it in
     * the tableview
     */
    private void display() {
        data.clear();
        for (int i = 0; i < arrayMasterAccount.getLogged().getChildAccounts().size(); i++) {
            data.add(arrayMasterAccount.getLogged().getChildAccounts().get(i));
        }
    }

    /**
     * This method takes the user data input and stores it in ChildAccount
     * arraylist
     */
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
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}
