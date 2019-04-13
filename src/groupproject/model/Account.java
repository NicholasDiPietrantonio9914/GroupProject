package groupproject.model;

/**
 * An interface that describes a user account with a password
 *
 * @author Nicholas Di Pietrantonio
 */
public interface Account {

    public String getUserName();

    public String getPassword();

    public void setPassword(String password);
}
