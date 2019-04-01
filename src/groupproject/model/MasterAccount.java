
package groupproject.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class MasterAccount implements Account{
    
    private String userName;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private ArrayList <ChildAccount> childAccounts = new ArrayList<>();

    public MasterAccount(String userName, String password, 
            String securityQuestion, String securityAnswer) {
        this.userName = userName;
        this.password = password;
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
    
    public void addChildAccount(ChildAccount childAccount) {
        childAccounts.add(childAccount);
    }

    public void setSecurityQuestion() {
        this.securityQuestion = securityQuestion;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    
    public boolean verifyPasswordLength(){
        
        if ((getPassword().length() < 8) || (getPassword().length() > 16)) {
            System.out.println("password must be between 8 and 16 chars in length");
        }
        return true;
    }
    
    @Override
    public void setPassword(String password) {
        this.password = password;
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
