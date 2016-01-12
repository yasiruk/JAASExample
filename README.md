# JAASExample

### Branches
* **custom_login_config** - this branch has an implementation with our own login configuration
* **master** - has the default JAAS configuration implementation


### How to run 

If you are running the default JAAS implementation from master branch then include these JVM parameters.   

    -Djava.security.auth.login.config=gov_jaas.config -Djava.security.manager -Djava.security.policy=security.policy
