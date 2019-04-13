package groupproject;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class represent a Master Account in the application
 *
 * @author Nicholas Di Pietrantonio
 */
public class Accounts {

    private final SimpleStringProperty userName;
    private final SimpleStringProperty password;
    private final SimpleStringProperty otherInfo;

    /**
     * This is a 3-arg constructor that will initialize the data fields
     *
     * @param uName user's username
     * @param pass user's password
     * @param oInfo user's other information
     */
    private Accounts(String uName, String pass, String oInfo) {
        this.userName = new SimpleStringProperty(uName);
        this.password = new SimpleStringProperty(pass);
        this.otherInfo = new SimpleStringProperty(oInfo);
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String uName) {
        userName.set(uName);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String uName) {
        password.set(uName);
    }

    public String getOtherInfo() {
        return otherInfo.get();
    }

    public void setOtherInfo(String uName) {
        otherInfo.set(uName);
    }
}
