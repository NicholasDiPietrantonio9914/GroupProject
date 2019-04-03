package groupproject.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class ArrayMasterAccount {

    private static ArrayList<MasterAccount> masterAccounts = new ArrayList<>(0);
    private static MasterAccount logged;

    public void addMaster(MasterAccount masterAccount) {
        masterAccounts.add(masterAccount);
        createJson();
    }

    public void accountExists(MasterAccount masterAccount) {
        for (int i = 0; i < masterAccounts.size(); i++) {
            if (passwordExists(masterAccount) || userNameExists(masterAccount)) {
                throw new IllegalArgumentException("Account already exists");
            }
        }
        addMaster(masterAccount);
    }

    public boolean passwordExists(MasterAccount masterAccount) {
        for (int i = 0; i < masterAccounts.size(); i++) {
            if (masterAccounts.get(i).getPassword().equals(masterAccount.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public boolean userNameExists(MasterAccount masterAccount) {
        for (int i = 0; i < masterAccounts.size(); i++) {
            if (masterAccounts.get(i).getUserName().equals(masterAccount.getUserName())) {
                return true;
            }
        }
        return false;
    }

    public boolean login(String userName, String password) {
        for (int i = 0; i < masterAccounts.size(); i++) {
            if ((masterAccounts.get(i).getUserName().equals(userName))
                    && (masterAccounts.get(i).getPassword().equals(password))) {
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

    public void createJson() {
        JSONObject root = new JSONObject();

        JSONArray jSONMasterAccounts = new JSONArray();

        for (int i = 0; i < masterAccounts.size(); i++) {
            JSONObject masterAccount = new JSONObject();
            masterAccount.put("userName", masterAccounts.get(i).getUserName());
            masterAccount.put("password", masterAccounts.get(i).getPassword());
            masterAccount.put("securityQuestion", masterAccounts.get(i).getSecurityQuestion());
            masterAccount.put("securityAnswer", masterAccounts.get(i).getSecurityAnswer());
            JSONArray jSONChildAccounts = new JSONArray();
            
            for (int j = 0; j < masterAccounts.get(i).getChildAccounts().size(); j++) {
                JSONObject childAccount = new JSONObject();
                childAccount.put("login", masterAccounts.get(i).getChildAccounts().get(j).getLogin());
                childAccount.put("userName", masterAccounts.get(i).getChildAccounts().get(j).getLogin());
                childAccount.put("password", masterAccounts.get(i).getChildAccounts().get(j).getLogin());
                childAccount.put("other", masterAccounts.get(i).getChildAccounts().get(j).getLogin());
                jSONChildAccounts.add(childAccount);
            }
            masterAccount.put("childAccounts", jSONChildAccounts);
            jSONMasterAccounts.add(masterAccount);
        }
        
        root.put("masterAccounts", jSONMasterAccounts);

        System.out.println(root.toJSONString());
        
        try (FileWriter file = new FileWriter("src/groupproject/JSON/GroupProject.json")) {
 
            file.write(root.toJSONString());
            file.flush();
 
        } catch (IOException ex) {
            System.out.println(ex);
        }
    

    }

    public void loadJson() {
        
    }
}
