package gov.country.authorization.policysource;

import gov.country.authentication.principals.PresidentPrincipal;
import gov.country.permissions.LaunchCodePermission;


import java.security.*;


/**
 * Created by yasiru on 1/14/16.
 */
public class InMemoryGovtPolicySource implements PolicySource {
    private CodeSource authorizedCodeSource;

    private Principal[] authorizedPrincipals;
    private PermissionCollection governmentPermissions;
    private PermissionCollection presidentialPermissions;

    public InMemoryGovtPolicySource() {
        authorizedCodeSource = getClass().getProtectionDomain().getCodeSource();
        authorizedPrincipals = new Principal[1];
        governmentPermissions = new Permissions();
        presidentialPermissions = new Permissions();

        presidentialPermissions.add(new LaunchCodePermission("icbm", "prepare,launch,abort"));

        //authorize Subjects holding the PresidentPrincipal or a Principal that implies PresidentPrincipal
        authorizedPrincipals[0] = new PresidentPrincipal("john");
        /*
        grant principal gov.country.authentication.principals.PresidentPrincipal "john" {
            permission javax.security.auth.PrivateCredentialPermission "* gov.country.authentication.principals.PresidentPrincipal \"john\"", "read";
            permission javax.security.auth.AuthPermission "modifyPrivateCredentials";
            permission gov.country.permissions.LaunchCodePermission "icbm";
            permission gov.country.military.permissions.MissileLaunchPermission "icbm", "prepare,launch,abort";
        };*/
        presidentialPermissions.add(new javax.security.auth.PrivateCredentialPermission("* gov.country.authentication.principals.PresidentPrincipal \"john\"", "read"));
        presidentialPermissions.add(new javax.security.auth.AuthPermission("modifyPrivateCredentials"));
        presidentialPermissions.add(new gov.country.permissions.LaunchCodePermission("icbm"));
        presidentialPermissions.add(new gov.country.military.permissions.MissileLaunchPermission("icbm", "prepare,launch,abort"));

    }


    @Override
    public PermissionCollection getPermissions(ProtectionDomain protectionDomain) {
        Principal[] principals = protectionDomain.getPrincipals();

        if (!protectionDomain.getCodeSource().getLocation().getPath().equals(authorizedCodeSource.getLocation().getPath())) {
            //this code is not in our auth'd codesource
            return null;
        }
        for (Principal principal : principals) {
            if (principal.equals(authorizedPrincipals[0])) {
                //john is one of the principals,
                //lazily return all his permissions.
                return presidentialPermissions;
            }
        }

        //someone other than the president is logged in, give him rest of the permissions.
        return getPermissions(protectionDomain.getCodeSource());
    }

    @Override
    public PermissionCollection getPermissions(CodeSource codeSource) {
        return governmentPermissions;
    }

    @Override
    public void refresh() {
        // :P
    }
}
