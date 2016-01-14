# JAASExample

### Branches
* **custom_login_config** - this branch has an implementation with our own login configuration
* **master** - has the default JAAS configuration implementation
* **custom_policy_config** - this branch has an implementation with our own security Policy class that loads configuration from run-time as well as from the default security policy file.

### How to run 

If you are running the default JAAS implementation from master branch then include these JVM parameters.   

    -Djava.security.auth.login.config=gov_jaas.config -Djava.security.manager -Djava.security.policy=security.policy

If you are running the JAAS implementation from **cusom_login_config** branch then include these JVM parameters.   

    -Djava.security.manager -Djava.security.policy=security.policy

If you are running the JAAS implementation from **cusom_policy_config** branch then **do not** include any of these JVM parameters.

    -Djava.security.manager -Djava.security.policy=security.policy
