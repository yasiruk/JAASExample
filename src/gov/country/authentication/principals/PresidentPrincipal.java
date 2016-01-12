package gov.country.authentication.principals;

import javax.security.auth.PrivateCredentialPermission;
import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Created by yasiru on 1/12/16.
 */
public class PresidentPrincipal implements Principal {
    private String name;

    public PresidentPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean implies(Subject subject) {
        for(Principal p :subject.getPrincipals()) {
            if(p instanceof PresidentPrincipal && this.name.equals(p.getName()))
                return true; //we return true if the the subject is a president and his name is the same as in this Principal
        }

        return false;
    }
}
