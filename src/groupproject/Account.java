
package groupproject;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public abstract class Account {
    
    private String userName;
    private String password;
    
    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    public abstract void setPassword(String password);
    public abstract void setUserName(String userName);
}
