package gov.country.military.permissions;

import gov.country.permissions.AllGovernmentPermissions;

import javax.security.auth.PrivateCredentialPermission;
import java.security.BasicPermission;
import java.security.Permission;

/**
 * Created by yasiru on 1/12/16.
 */


public class MissileLaunchPermission extends Permission {
    private String actions;
    private static final int LAUNCH =  0b00111;
    private static final int ABORT =   0b00011;
    private static final int PREPARE = 0b00001;
    private int mask;
    public MissileLaunchPermission(String name) {
        super(name);
        mask = 0;

    }

    public MissileLaunchPermission(String name, String actions) {
        super(name);
        this.actions = actions;
        mask = 0;

    }

    @Override
    public boolean implies(java.security.Permission p) {
        if(p instanceof AllGovernmentPermissions)
            return true;

        if(p instanceof MissileLaunchPermission) {
            MissileLaunchPermission perm = (MissileLaunchPermission) p;
            if(mask == 0)
                try {
                    mask = setMask(this.actions);
                } catch (MalformedMissleActions malformedMissleActions) {
                    malformedMissleActions.printStackTrace();
                    return false;
                }

            if ((perm.getMask() & this.getMask()) == perm.getMask()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MissileLaunchPermission) {
            MissileLaunchPermission mlp = (MissileLaunchPermission)obj;
            if(this.getName() == mlp.getName() && this.actions == mlp.actions) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (getName() != null) ? getName().hashCode() : 0;
    }

    @Override
    public String getActions() {
        return "prepare,launch,abort";
    }

    @Override
    public String toString() {
        return "MissleLaunchPermission " + getName() + " [" + actions + "]";
    }


    private static int setMask(String actions) throws MalformedMissleActions {
        int mask = 0;

        if(actions.matches("(launch|abort|prepare)(,launch|,abort|,prepare)?(,launch|,abort|,prepare)?")) {
            for(String action : actions.split(",")){
                switch (action) {
                    case "launch":
                        mask |= LAUNCH;
                        break;
                    case "abort":
                        mask |= ABORT;
                        break;
                    case "prepare":
                        mask |= PREPARE;
                        break;
                    default:
                        throw new MalformedMissleActions("Invalid actions: " + actions);
                }
            }
        }
        else {
            throw new MalformedMissleActions("Invalid actions: " + actions);
        }
        return mask;
    }
    private int getMask() {
        if(mask == 0)
            try {
                mask = setMask(this.actions);
            } catch (MalformedMissleActions malformedMissleActions) {
                malformedMissleActions.printStackTrace();
            }
        return mask;
    }
    public static class MalformedMissleActions extends Exception {
        public MalformedMissleActions(String message) {
            super(message);
        }
    }
}
