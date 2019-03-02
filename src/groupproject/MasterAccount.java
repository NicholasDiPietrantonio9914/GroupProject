
package groupproject;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class MasterAccount extends Account{
    
    private String securityQuestion;
    private String securityAnswer;
    private ArrayList <ChildAccount> childAccounts;

    public MasterAccount(String userName, String password, 
            String securityQuestion, String securityAnswer) {
        super(userName, password);
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public ArrayList<ChildAccount> getChildAccounts() {
        return childAccounts;
    }

    public void chooseSecurityQuestion() {
        this.securityQuestion = securityQuestion;
    }

    public void setSecurityAnswer(String securityAnswer) {
        
    }
    
    public boolean loginVerify(String passwordEntered, String userNameEntered) {
        return true;
    }
    
    public boolean verifyPasswordLength(){
        
        if ((getPassword().length() < 8) || (getPassword().length() > 16)) {
            System.out.println("password must be between 8 and 16 chars in length");
        }
        return true;
    }
    
    @Override
    public void setPassword(String password) {
        
    }

    @Override
    public void setUserName(String userName) {
        
    }

}
