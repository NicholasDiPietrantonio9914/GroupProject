package groupproject.model;

import java.util.Random;
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
    private int uniqueIdentifier;

    public ChildAccount(String login, String userName, String password, String other) {
        if (login.trim().equals("")) {
            throw new IllegalArgumentException("Login field must contain information at a minimum");
        } else {
            this.login = login;
            this.userName = userName;
            this.password = password;
            this.other = other;
        }
    }

    public int getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(int uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
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
        if (login.trim().equals("")) {
            throw new IllegalArgumentException("Login field must contain information at a minimum");
        } else {
            this.login = login;
        }
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
