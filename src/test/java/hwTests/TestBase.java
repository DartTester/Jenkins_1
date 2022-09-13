package hwTests;

import Configs.CredentialsConfig;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
//import helpers.Attach;
import hwHelpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        // Для добавления видео в отчет:
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser","chrome");
        Configuration.browserVersion = System.getProperty("version","100");
        Configuration.browserSize = System.getProperty("browserSize", "3840x2160");

        //Логин и пароль получаем из файла, которого нет в Гите и который мы создаем прямо в Дженкинсе
        CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);
        Configuration.remote = String.format("https://%s:%s@%s", credentialsConfig.login(), credentialsConfig.password(),
                credentialsConfig.urlCloudSelenoid());
    }
        /*Configuration.remote = "https://" + credentialsConfig.login() + ":" + credentialsConfig.password() + "@" +
                credentialsConfig.urlCloudSelenoid();*/

        // Configuration.browserSize = "1920x1080";

        // Надо указать, чтобы тесты запускались не в локальном браузере, а в Selenoid:
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
