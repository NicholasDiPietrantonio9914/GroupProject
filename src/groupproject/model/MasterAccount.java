
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
    private ArrayMasterAccount update = new ArrayMasterAccount();

    public MasterAccount(String userName, String password, 
            String securityQuestion, String securityAnswer) {
        if ((verifyPassOrUserLength(password)) || (password.contains(" "))) {
            throw new IllegalArgumentException("Invalid password entered");
        } else if ((verifyPassOrUserLength(userName)) || (password.contains(" "))) {
            throw new IllegalArgumentException("Invalid username entered");
        } else if ((securityAnswer.contains(" ")) || (securityAnswer.equals(""))) {
            throw new IllegalArgumentException("Invalid security answer entered");
        } else {
            this.userName = userName;
            this.password = password;
            this.securityQuestion = securityQuestion;
            this.securityAnswer = securityAnswer;
        }
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
        update.createJson();
    }
    
    public boolean verifyPassOrUserLength(String passOrUser){
        
        if ((passOrUser.length() < 4) || (passOrUser.length() > 12)) {
            return true;
        }
        return false;
    }
    
    @Override
    public void setPassword(String password) {
        if ((verifyPassOrUserLength(password)) || (password.contains(" ")) ||
                (this.password.equals(password))) {
            throw new IllegalArgumentException("Invalid password entered");
        } else {
            this.password = password;
            update.createJson();
        }
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
