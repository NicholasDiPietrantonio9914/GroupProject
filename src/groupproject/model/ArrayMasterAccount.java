package groupproject.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class stores account information
 *
 * @author Nicholas Di Pietrantonio
 */
public class ArrayMasterAccount {

    private static ArrayList<MasterAccount> masterAccounts = new ArrayList<>(0);
    private static MasterAccount logged;
    private JSONObject root;

    /**
     * This method adds a master account
     *
     * @param masterAccount a new user's master account username
     */
    public void addMaster(MasterAccount masterAccount) {
        masterAccounts.add(masterAccount);
        createJson();
    }

    public static ArrayList<MasterAccount> getMasterAccounts() {
        return masterAccounts;
    }

    /**
     * This method verifies if a master account exists
     *
     * @param masterAccount the user's master account username
     */
    public void accountExists(MasterAccount masterAccount) {
        for (int i = 0; i < masterAccounts.size(); i++) {
            if (masterAccounts.get(i).getUserName().equals(masterAccount.getUserName())) {
                throw new IllegalArgumentException("Account already exists");
            }
        }
        addMaster(masterAccount);
    }

    /**
     * This method verifies that the username and password match
     *
     * @param userName user's username
     * @param password user's password
     * @return true if user name and match, false otherwise
     */
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

    /**
     * This method logs the user into their account
     *
     * @param masterAccount the user's master account
     */
    public void setLogged(MasterAccount masterAccount) {
        logged = masterAccount;
    }

    /**
     * This method gets the account that is currently logged in
     *
     * @return the master account's login/child account information
     */
    public MasterAccount getLogged() {
        return logged;
    }

    /**
     * This method creates a JSON array in the JSON Object which adds and stores
     * master account information and child accounts object
     */
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

        try (FileWriter file = new FileWriter("src/groupproject/JSON/GroupProject.json")) {

            file.write(root.toJSONString());
            file.flush();

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    /**
     * This method loads and reads the JSON file
     */
    public void loadJson() {
        try {
            JSONParser parser = new JSONParser();
            //Object obj = new JSONParser().parse(new FileReader("src/groupproject/JSON/GroupProject.json")); 
            JSONObject root = (JSONObject) new JSONParser().parse(
                    new FileReader("src/groupproject/JSON/GroupProject.json"));
            JSONArray masterAccs = (JSONArray) root.get("masterAccounts");

            for (int i = 0; i < masterAccs.size(); i++) {
                JSONObject masterAccount = (JSONObject) masterAccs.get(i);
                JSONArray childAccs = (JSONArray) masterAccount.get("childAccounts");
                MasterAccount masterAcc = new MasterAccount(
                        (String) masterAccount.get("userName"),
                        (String) masterAccount.get("password"),
                        (String) masterAccount.get("securityQuestion"),
                        (String) masterAccount.get("securityAnswer"));

                for (int j = 0; j < childAccs.size(); j++) {
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
