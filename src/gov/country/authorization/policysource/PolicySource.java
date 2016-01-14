package gov.country.authorization.policysource;

import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;

/**
 * Created by yasiru on 1/14/16.
 */
public interface PolicySource {
    PermissionCollection getPermissions(ProtectionDomain protectionDomain);
    PermissionCollection getPermissions(CodeSource codeSource);
    void refresh();
}
