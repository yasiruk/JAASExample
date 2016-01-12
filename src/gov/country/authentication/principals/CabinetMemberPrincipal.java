package gov.country.authentication.principals;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Created by yasiru on 1/12/16.
 */
public class CabinetMemberPrincipal implements Principal {
    private String name;

    public CabinetMemberPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean implies(Subject subject) {
        for(Principal p :subject.getPrincipals()) {
            if(p instanceof CabinetMemberPrincipal)
                return true; //we return true for any cabinet member, regardless of their name, this is our own policy.
        }
        return false;
    }
}
