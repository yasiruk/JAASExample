package gov.country.authorization.policy;

import gov.country.authorization.policysource.PolicySource;
import sun.security.provider.PolicyFile;

import java.net.URL;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.util.ArrayList;

/**
 * Created by yasiru on 1/14/16.
 */
public class CustomPolicy extends PolicyFile {
    private PolicySource policySource;

    public CustomPolicy(PolicySource policySources) {
        super();
        this.policySource = policySources;
    }

    public CustomPolicy(PolicySource policySources, URL fallbackPolicyFile) {
        super(fallbackPolicyFile);
        this.policySource = policySources;
    }

    public CustomPolicy(URL url) {
        super(url);
        policySource = null;
    }

    @Override
    public void refresh() {
        super.refresh();
        policySource.refresh();
    }


    @Override
    public PermissionCollection getPermissions(ProtectionDomain protectionDomain) {
        PermissionCollection permissionCollection = null;
        if (policySource != null)
            permissionCollection = policySource.getPermissions(protectionDomain);

        if (permissionCollection != null)
            return permissionCollection;
        else //fallback
            return super.getPermissions(protectionDomain);
    }

    @Override
    public PermissionCollection getPermissions(CodeSource codeSource) {
        PermissionCollection permissionCollection = null;
        if (policySource != null)
            permissionCollection = policySource.getPermissions(codeSource);
        if (permissionCollection != null)
            return permissionCollection;
        else //fallback
            return super.getPermissions(codeSource);
    }

    public void setPolicySource(PolicySource policySource) {
        this.policySource = policySource;
    }
}
