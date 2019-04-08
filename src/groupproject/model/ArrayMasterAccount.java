package groupproject.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author IKTCFUUTJFHMX
 */
public class ArrayMasterAccount {

    private static ArrayList<MasterAccount> masterAccounts = new ArrayList<>(0);
    private static MasterAccount logged;
    private JSONObject root;

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
        root = new JSONObject();

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
                childAccount.put("userName", masterAccounts.get(i).getChildAccounts().get(j).getUserName());
                childAccount.put("password", masterAccounts.get(i).getChildAccounts().get(j).getPassword());
                childAccount.put("other", masterAccounts.get(i).getChildAccounts().get(j).getOther());
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
        try {
            JSONParser parser = new JSONParser();
            //Object obj = new JSONParser().parse(new FileReader("src/groupproject/JSON/GroupProject.json")); 
            JSONObject root = (JSONObject) new JSONParser().parse(
                    new FileReader("src/groupproject/JSON/GroupProject.json")); 
            JSONArray masterAccs = (JSONArray) root.get("masterAccounts");
            
            for (int i = 0 ; i < masterAccs.size() ; i++) {
                JSONObject masterAccount = (JSONObject) masterAccs.get(i);
                JSONArray childAccs = (JSONArray) masterAccount.get("childAccounts");
                MasterAccount masterAcc = new MasterAccount(
                        (String) masterAccount.get("userName"), 
                        (String) masterAccount.get("password"), 
                        (String) masterAccount.get("securityQuestion"), 
                        (String) masterAccount.get("securityAnswer"));
                
                for (int j = 0 ; j < childAccs.size() ; j++) {
                    JSONObject childAccount = (JSONObject) childAccs.get(j);
                    ChildAccount childAcc = new ChildAccount(
                        (String) childAccount.get("login"), 
                        (String) childAccount.get("userName"), 
                        (String) childAccount.get("password"), 
                        (String) childAccount.get("other"));
                    masterAcc.getChildAccounts().add(childAcc);
                }
                masterAccounts.add(masterAcc);
            
            }
        } catch (ParseException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    
    }
}
