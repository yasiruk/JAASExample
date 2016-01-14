package gov.country.authorization.policy;

import gov.country.authorization.policysource.PolicySource;
import sun.security.provider.PolicyFile;

import java.net.URL;
import java.security.*;
import java.util.ArrayList;
import java.util.Enumeration;

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
        PermissionCollection outputPermissionCollection = new Permissions();
        PermissionCollection temp;
        Enumeration<Permission> tEnumerator = null;
        if (policySource != null) {
            temp = policySource.getPermissions(protectionDomain);
            if (temp != null)
                tEnumerator = temp.elements();
        }
        temp = super.getPermissions(protectionDomain);

        if (tEnumerator != null) {
            while (tEnumerator.hasMoreElements()) {
                outputPermissionCollection.add(tEnumerator.nextElement());
            }

            tEnumerator = temp.elements();

            while (tEnumerator.hasMoreElements()) {
                outputPermissionCollection.add(tEnumerator.nextElement());
            }
        } else {
            return temp;
        }
        return outputPermissionCollection;

    }

    @Override
    public PermissionCollection getPermissions(CodeSource codeSource) {
        PermissionCollection outputPermissionCollection = new Permissions();
        PermissionCollection temp;
        Enumeration<Permission> tEnumerator = null;
        if (policySource != null) {
            temp = policySource.getPermissions(codeSource);
            if (temp != null)
                tEnumerator = temp.elements();
        }
        ;
        temp = super.getPermissions(codeSource);

        if (tEnumerator != null) {
            while (tEnumerator.hasMoreElements()) {
                outputPermissionCollection.add(tEnumerator.nextElement());
            }

            tEnumerator = temp.elements();

            while (tEnumerator.hasMoreElements())
                outputPermissionCollection.add(tEnumerator.nextElement());

        } else {
            return temp;
        }

        return outputPermissionCollection;

    }

    public void setPolicySource(PolicySource policySource) {
        this.policySource = policySource;
    }
}
