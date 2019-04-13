package groupproject.model;

/**
 * This class represent a login(username, password, other info) in the
 * application
 *
 * @author Nicholas Di Pietrantonio
 */
public class ChildAccount implements Account {

    private String login;
    private String userName;
    private String password;
    private String other;
    private int uniqueIdentifier;

    /**
     * This is a 4-arg constructor that will initialize the data fields
     *
     * @param login login name
     * @param userName user's username for the login
     * @param password user's password for the login
     * @param other user's "other" info for the login
     */
    public ChildAccount(String login, String userName, String password, String other) {
        if (login.trim().equals("")) {
            throw new IllegalArgumentException(
                    "Information must be entered in the Login Field");
        } else {
            this.login = login;
            this.userName = userName;
            this.password = password;
            this.other = other;
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
        if (login.trim().equals("")) {
            throw new IllegalArgumentException(
                    "Information must be entered in the Login Field");
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
