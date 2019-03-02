
package groupproject;

import java.util.ArrayList;

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
    
    public void verifyPasswordLength(String password){
        password = getPassword();
    }

    @Override
    public void changePassword() {
        
    }

    @Override
    public void changeUserName() {
        
    }

}
