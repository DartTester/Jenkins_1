package hw_tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class Demo_qa_form_Jenkins extends TestBase {

    @DisplayName("Checking the correct filling of the form")
    @Test
    @Owner("Daniil")
    @Severity(SeverityLevel.TRIVIAL)
    @Feature("Insurance registration")
    @Story("Form submission")
    @Description("Checking the work of lambda steps")
    @Link(name = "demoqa", url = "https://demoqa.com")
    void successfulTest() {
        String name = "Daniil";

        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open the page with the form and block the ad", () -> {
            open("/automation-practice-form");
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });

        // Text
        step("Enter name", () -> {
            $("[id=firstName]").setValue(name);
        });
        step("Enter last name", () -> {
            $("[id=lastName]").setValue("Davydov");
        });
        step("Enter the email address", () -> {
            $("[id=userEmail]").setValue("da@da.ru");
        });

        // Radio-button
        step("Choose gender", () -> {
            $(byText("Male")).click();
        });
        step("Enter the phone number", () -> {
            $("[id=userNumber]").setValue("0123456789");
        });

        // Date
        step("Choose the date of birth", () -> {
            $("[id=dateOfBirthInput]").click();
            $(".react-datepicker__month-select").selectOption("August");
            $(".react-datepicker__year-select").selectOption("1993");
            $("[aria-label='Choose Tuesday, August 10th, 1993']").click();
            Allure.getLifecycle().addAttachment(
                    "Screenshot", "image/png", "png",
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            );
        });

        // Text
        step("Choosing the subject", () -> {
            $("#subjectsInput").sendKeys("m");
            $(byText("Maths")).click();
        });

        // Check-box
        step("Choosing the hobby", () -> {
            $(byText("Sports")).click();
            $(byText("Reading")).click();
            $(byText("Music")).click();
        });

        // Picture
        step("Select File", () -> {
            $("#uploadPicture").uploadFromClasspath("pic.png");
        });

        // Text
        step("Enter address", () -> {
            $("[id=currentAddress]").setValue("Adress");
            Allure.getLifecycle().addAttachment(
                    "Screenshot1", "image/png", "png",
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            );
        });

        // Drop-down list
        step("Select state and city", () -> {
            $("#state").click();
            $(byText("NCR")).click();
            $("#city").click();
            $(byText("Delhi")).click();
        });

        step("Click submit", () -> {
            $("[id=submit]").click();
        });

        // Result:
        step("Checking the result", () -> {
            $(".modal-body").shouldHave(text("Daniil Davydov"),
                    text("da@da.ru"),
                    text("Male"),
                    text("0123456789"),
                    text("10 August,1993"),
                    text("Maths"),
                    text("Sports, Reading, Music"),
                    text("pic.png"),
                    text("Adress"),
                    text("NCR Delhi")
            );
        });

    }
}

