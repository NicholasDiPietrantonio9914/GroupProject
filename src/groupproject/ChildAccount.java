
package groupproject;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class ChildAccount extends Account{
    
    private String information;

    public ChildAccount(String userName, String password, String information) {
        super(userName, password);
        this.information = information;
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
