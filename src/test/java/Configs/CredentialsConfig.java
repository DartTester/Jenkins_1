package Configs;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configs/credentials.properties")
public interface CredentialsConfig extends Config {
    String login();
    String password();
    String urlCloudSelenoid();
}
