package groupproject.model;

import javafx.scene.control.Button;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class ChildAccount implements Account {

    private String login;
    private String userName;
    private String password;
    private String other;
    private Button btnEdit;
    private Button btnRemove;

    public ChildAccount(String login, String userName, String password, String other) {
        if (login.trim().equals("")) {
            throw new IllegalArgumentException("Login field must contain information at a minimum");
        } else {
            this.login = login;
            this.userName = userName;
            this.password = password;
            this.other = other;
            this.btnEdit = new Button("Edit");
            this.btnRemove = new Button("Remove");
        }
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
