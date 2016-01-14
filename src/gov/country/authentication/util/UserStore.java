package gov.country.authentication.util;

import java.util.HashMap;

/**
 * Created by yasiru on 1/12/16.
 */
public class UserStore {
    private HashMap<String, String> users;
    private static UserStore instance;
    public static UserStore getInstance() {
        if(instance == null)
            instance = new UserStore();
        return instance;
    }
    private UserStore() {
        users = new HashMap<>();
        users.put("john", "test");
        users.put("harry", "test");
    }
    public String getPassword(String user) {
        return users.get(user);
    }

}
