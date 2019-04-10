package groupproject;


import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author Joey
 */
public class Accounts {
 
        private final SimpleStringProperty userName;
        private final SimpleStringProperty password;
        private final SimpleStringProperty otherInfo;
        
        private Accounts(String uName, String pass, String oInfo) {
        this.userName = new SimpleStringProperty(uName);
        this.password = new SimpleStringProperty(pass);
        this.otherInfo = new SimpleStringProperty(oInfo);
    }
 
        public String getUserName() {
            return userName.get();
        }
 
        public void setUserName(String uName) {
            userName.set(uName);
        }
 
        public String getPassword() {
            return password.get();
        }
 
        public void setPassword(String uName) {
            password.set(uName);
        }
 
        public String getOtherInfo() {
            return otherInfo.get();
        }
 
        public void setOtherInfo(String uName) {
            otherInfo.set(uName);
        }
    }
