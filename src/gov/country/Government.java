package gov.country;

import gov.country.authentication.credentials.NuclearLaunchCodes;
import gov.country.authentication.util.LoginConfig;
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
        Subject subject = null;
        try {
            //a fresh subject instance is parsed and a cusom login config instance is passed
            lc = new LoginContext("government", subject, new StandardCallbackHandler(), new LoginConfig());
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

        subject = lc.getSubject();


        System.out.println("Logged in");


        //add the launchcode private credential to the private credentials set of the subject
        try {
            final Subject finalSubject = subject;
            Subject.doAsPrivileged(subject, new PrivilegedExceptionAction<Set>() {
                @Override
                public Set run() throws Exception {
                    Set privateCredentials = finalSubject.getPrivateCredentials();
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
