package groupproject.model;

import java.util.ArrayList;

/**
 * This class represent a master account in the application
 *
 * @author Nicholas Di Pietrantonio
 */
public class MasterAccount implements Account {

    private String userName;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private ArrayList<ChildAccount> childAccounts = new ArrayList<>();
    private ChildAccount editChild;

    /**
     * This is a 4-arg constructor that will initialize the data fields
     *
     * @param userName user's username
     * @param password user's password
     * @param securityQuestion user's security question
     * @param securityAnswer user's security answer
     */
    public MasterAccount(String userName, String password,
            String securityQuestion, String securityAnswer) {
        if (verifyPassOrUser(password)) {
            throw new IllegalArgumentException("Invalid password entered "
                    + "Password cannot contain spaces and must be "
                    + "between 4 and 12 characters in length");
        } else if (verifyPassOrUser(userName)) {
            throw new IllegalArgumentException("Invalid username entered"
                    + "Username cannot contain spaces and"
                    + " must be between 4 and 12 characters in length");
        } else if ((securityAnswer.equals("")) || (securityAnswer.charAt(0) == (' '))
                || securityAnswer.charAt(securityAnswer.length() - 1) == (' ')) {
            throw new IllegalArgumentException("Security answer cannot be empty "
                    + "and cannot begin or end with a space");
        } else {
            this.userName = userName;
            this.password = password;
            this.securityQuestion = securityQuestion;
            this.securityAnswer = securityAnswer;
        }
    }

    public ChildAccount getEditChild() {
        return editChild;
    }

    public void setEditChild(ChildAccount editChild) {
        this.editChild = editChild;
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

    /**
     * This method adds a child account (login)
     *
     * @param childAccount the login entered
     */
    public void addChildAccount(ChildAccount childAccount) {
        for (int i = 0; i < childAccounts.size(); i++) {
            if (childAccounts.get(i).getLogin().equals(childAccount.getLogin())) {
                throw new IllegalArgumentException(
                        "Login already exists");
            }
        }
        childAccounts.add(childAccount);
    }

    /**
     * This method allows modification to a child account
     *
     * @param txtEditLog the login
     * @param txtEditUser the user's username
     * @param txtEditPass the user's password
     * @param txtEditOther the user's other information
     */
    public void editEditChild(String txtEditLog, String txtEditUser,
            String txtEditPass, String txtEditOther) {
        for (int i = 0; i < childAccounts.size(); i++) {
            if ((childAccounts.get(i).getLogin().equals(txtEditLog))
                    && (!childAccounts.get(i).getLogin().equals(editChild.getLogin()))) {
                throw new IllegalArgumentException("Login already exists");   
            }
        }
        editChild.setLogin(txtEditLog);
        editChild.setUserName(txtEditUser);
        editChild.setPassword(txtEditPass);
        editChild.setOther(txtEditOther);
    }

    /**
     * This method verifies the password length and input
     *
     * @param passOrUser the user's choice of password
     * @return true if password meet requirement, false otherwise
     */
    public boolean verifyPassOrUser(String passOrUser) {

        if ((passOrUser.length() < 4) || (passOrUser.length() > 12)) {
            return true;
        }
        for (int i = 0; i < passOrUser.length(); i++) {
            if (passOrUser.charAt(i) == ' ') {
                return true;
            }
        }
        return false;
    }

    /**
     * This method stores a password for the user's master account
     *
     * @param password the user's password
     */
    @Override
    public void setPassword(String password) {
        if ((verifyPassOrUser(password)) || this.password.equals(password)) {
            throw new IllegalArgumentException("Invalid password entered, password"
                    + " cannot contain spaces, must be between 4 and 12 characters in length, and"
                    + " cannot be the same as your current password");
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
