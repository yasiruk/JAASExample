package gov.country.authentication.modules;

import gov.country.authentication.credentials.NuclearLaunchCodes;
import gov.country.authentication.principals.CabinetMemberPrincipal;
import gov.country.authentication.principals.PresidentPrincipal;
import gov.country.authentication.util.UserStore;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;

import java.util.Map;

/**
 * Created by yasiru on 1/12/16.
 */
public class BasicLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;
    private boolean success;
    private String username;
    private char[] password;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
        this.success = false;

    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Enter user name: ");
        callbacks[1] = new PasswordCallback("Enter password: ", true);
        UserStore userStore = UserStore.getInstance();

        try {
            callbackHandler.handle(callbacks);

            username = ((NameCallback) callbacks[0]).getName();
            password = ((PasswordCallback) callbacks[1]).getPassword();
            String strpassword = new String(password);

            if (password != null & strpassword.equals(userStore.getPassword(username))) {
                success = true;
                sharedState.put("username", username);
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        if (!success) //login has failed
            return false;

        if ("john".equals(sharedState.get("username"))) { //john is our president
            subject.getPrincipals().add(new PresidentPrincipal("john"));
            subject.getPrincipals().add(new CabinetMemberPrincipal("john"));

        }

        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
