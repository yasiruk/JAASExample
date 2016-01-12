package gov.country;

import gov.country.authentication.credentials.NuclearLaunchCodes;
import gov.country.authentication.util.StandardCallbackHandler;
import gov.country.military.assets.ICBMMissle;


import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Set;

/**
 * Created by yasiru on 1/12/16.
 */

public class Government {
    public static void main(String[] args) {
        LoginContext lc = null;

        try {
            lc = new LoginContext("government", new StandardCallbackHandler());
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            lc.login();
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Subject subject = lc.getSubject();


        System.out.println("Logged in");

        //add the launchcode private credential to the private credentials set of the subject
        try {
            Subject.doAsPrivileged(subject, new PrivilegedExceptionAction<Set>() {
                @Override
                public Set run() throws Exception {
                    Set privateCredentials = subject.getPrivateCredentials();
                    privateCredentials.add(new NuclearLaunchCodes(1234));
                    System.out.println(privateCredentials.size());
                    return privateCredentials;
                }
            }, null);
        } catch (PrivilegedActionException e) {
            e.printStackTrace();
        }

        /* MISSLE LAUNCH */
        ICBMMissle bill = new ICBMMissle();

        Subject.doAsPrivileged(subject, new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                bill.prepare();

                try {
                    bill.launch();
                    bill.selfDestruct();
                } catch (ICBMMissle.ICBMExecption icbmExecption) {
                    icbmExecption.printStackTrace();
                }
                return null;
            }
        }, null);

    }
}
