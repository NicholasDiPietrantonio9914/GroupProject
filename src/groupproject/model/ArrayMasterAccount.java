
package groupproject.model;

import java.util.ArrayList;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class ArrayMasterAccount {

    private static ArrayList<MasterAccount> masterAccounts = new ArrayList<>(0);
    private static MasterAccount logged;
    
    public void addMaster(MasterAccount masterAccount) {
        masterAccounts.add(masterAccount);
    }
    
    public boolean login(String userName, String password) {
            for (int i = 0; i < masterAccounts.size() ; i++) {
                if ((masterAccounts.get(i).getUserName().equals(userName)) &&
                        (masterAccounts.get(i).getPassword().equals(password))) {
                    setLogged(masterAccounts.get(i));
                    return true;
                }
            }
            return false;
    }
    
    public void setLogged(MasterAccount masterAccount) {
        logged = masterAccount;
    }
    
    public MasterAccount getLogged() {
        return logged;
    }
}
