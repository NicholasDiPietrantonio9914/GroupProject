
package groupproject;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class ChildAccount extends Account{
    
    private String login;

    public ChildAccount(String userName, String password, String login) {
        super(userName, password);
        this.login = login;
    }

    public String getAccountUse() {
        return login;
    }

    public void setAccountUse(String login) {
        this.login = login;
    }
    
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public void setPassword(String password) {
        
    }

    @Override
    public void setUserName(String userName) {
        
    }

}
