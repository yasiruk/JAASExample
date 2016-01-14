package gov.country.military.assets;

import gov.country.military.permissions.MissileLaunchPermission;

/**
 * Created by yasiru on 1/12/16.
 */
public class ICBMMissle {
    private boolean prepared;
    private boolean launched;

    public ICBMMissle() {
        prepared = false;
        launched = false;

    }

    public void launch() throws ICBMExecption {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new MissileLaunchPermission("icbm", "launch"));
            System.out.println("Launching ICBM Missle");
        } else
            throw new ICBMExecption("Launch aborted. No security manager configured");
    }
    public void selfDestruct() {
        SecurityManager sm = System.getSecurityManager();
        if(sm != null) {
            sm.checkPermission(new MissileLaunchPermission("icbm", "abort"));
        }
        if(launched)
            System.out.println("ICBM Self Destructing...");
        else
            System.out.println("ICBM Launch Aborted");
    }
    public void prepare() {
        SecurityManager sm = System.getSecurityManager();

        if(sm != null) {
            sm.checkPermission(new MissileLaunchPermission("icbm", "prepare"));
        }
        System.out.println("ICBM Prepared");
    }

    public static class ICBMExecption extends Exception {
        public ICBMExecption() {
        }

        public ICBMExecption(String message) {
            super(message);
        }
    }
}
