package gov.country.authentication.credentials;

import gov.country.permissions.LaunchCodePermission;

/**
 * Created by yasiru on 1/12/16.
 */
public class NuclearLaunchCodes {
    private int launchcode;

    public NuclearLaunchCodes(int launchcode) {
        SecurityManager sm = System.getSecurityManager();
        if(sm != null)
            sm.checkPermission(new LaunchCodePermission("icbm"));
        this.launchcode = launchcode;
    }

    public int getLaunchcode() {
        SecurityManager sm = System.getSecurityManager();
        if(sm != null)
            sm.checkPermission(new LaunchCodePermission("icbm"));
        return launchcode;
    }

}
