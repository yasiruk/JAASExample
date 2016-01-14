package gov.country.authorization;

import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Vector;

/**
 * Created by yasiru on 1/14/16.
 */
public class GovtPermissionCollection extends PermissionCollection {
    Vector<Permission> permissions;

    public GovtPermissionCollection() {
        permissions = new Vector<>();
    }

    @Override
    public void add(Permission permission) {
        permissions.add(permission);
    }

    @Override
    public boolean implies(Permission permission) {
        for (Permission p :
                permissions) {
            if (p.implies(permission))
                return true;
        }

        return false;
    }

    @Override
    public Enumeration<Permission> elements() {
        return permissions.elements();
    }
}
