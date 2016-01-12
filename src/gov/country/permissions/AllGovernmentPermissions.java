package gov.country.permissions;


import java.security.Permission;

/**
 * Created by yasiru on 1/12/16.
 */


public class AllGovernmentPermissions extends java.security.Permission {
    /**
     * Constructs a permission with the specified name.
     *
     * @param name name of the Permission object being created.
     */
    public AllGovernmentPermissions(String name) {
        super(name);
    }

    @Override
    public boolean implies(Permission permission) {
        return this.equals(permission);
    }

    @Override
    public boolean equals(Object obj) {
        return this.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.getName() != null ? super.getName().hashCode() : 0;
    }

    @Override
    public String getActions() {
        return null;
    }
}
