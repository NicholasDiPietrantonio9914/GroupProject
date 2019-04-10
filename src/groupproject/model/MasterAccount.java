package groupproject.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class MasterAccount implements Account {

    private String userName;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private ArrayList<ChildAccount> childAccounts = new ArrayList<>();
    private static ChildAccount editChild;

    public ChildAccount getEditChild() {
        return editChild;
    }

    public void setEditChild(ChildAccount editChild) {
        this.editChild = editChild;
    }

    public MasterAccount(String userName, String password,
            String securityQuestion, String securityAnswer) {
        if (verifyPassOrUser(password)) {
            throw new IllegalArgumentException("Invalid password entered");
        } else if (verifyPassOrUser(userName)) {
            throw new IllegalArgumentException("Invalid username entered");
        } else if (securityAnswer.equals("")) {
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
        for (int i = 0; i < childAccounts.size(); i++) {
            if (childAccounts.get(i).getLogin().equals(childAccount.getLogin())) {
                throw new IllegalArgumentException("Can not have two child accounts "
                        + "with the same login name");
            }
        }
        childAccounts.add(childAccount);
    }
    
    public void editEditChild (String txtEditLog, String txtEditUser,
            String txtEditPass, String txtEditOther) {
        for (int i = 0; i < childAccounts.size(); i++) {
            if (childAccounts.get(i).getLogin().equals(txtEditLog)) {
                throw new IllegalArgumentException("Can not have two child accounts "
                        + "with the same login name");
            }
        }
        editChild.setLogin(txtEditLog);
        editChild.setUserName(txtEditUser);
        editChild.setPassword(txtEditPass);
        editChild.setOther(txtEditOther);
    }

    public boolean verifyPassOrUser(String passOrUser) {

        if ((passOrUser.length() < 4) || (passOrUser.length() > 12)) {
            return true;
        }
        for (int i = 0 ; i < passOrUser.length() ; i++) {
            if(passOrUser.charAt(i) == ' ') {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setPassword(String password) {
        if ((verifyPassOrUser(password)) || (this.password.equals(password))) {
            throw new IllegalArgumentException("Invalid password entered");
        } else {
            this.password = password;
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
