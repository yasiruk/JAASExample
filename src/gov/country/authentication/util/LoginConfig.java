package gov.country.authentication.util;

import gov.country.authentication.modules.BasicLoginModule;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.util.HashMap;

/**
 * Created by yasiru on 1/12/16.
 */
public class LoginConfig extends Configuration {
    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        AppConfigurationEntry[] configurationEntries = new AppConfigurationEntry[1];
        configurationEntries[0] = new AppConfigurationEntry(BasicLoginModule.class.getName(),
                AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
                new HashMap<>()
                );

        return configurationEntries;
    }
}
