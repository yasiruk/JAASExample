package gov.country.permissions;

import java.security.BasicPermission;
import java.security.Permission;

/**
 * Created by yasiru on 1/12/16.
 */
public class LaunchCodePermission extends BasicPermission{
    public LaunchCodePermission(String name) {
        super(name);
    }

    public LaunchCodePermission(String name, String actions) {
        super(name, actions);
    }

    @Override
    public boolean implies(Permission permission) {
        return (permission instanceof AllGovernmentPermissions) || equals(permission); //one to one relation
    }


    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getActions() {
        return null;
    }
}
