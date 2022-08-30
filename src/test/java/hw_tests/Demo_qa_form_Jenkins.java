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

    @DisplayName("Проверка корректного заполнения формы")
    @Test
    @Owner("Daniil")
    @Severity(SeverityLevel.TRIVIAL)
    @Feature("Оформление страховки")
    @Story("Отправка формы")
    @Description("Чекаем работу лямбда степов")
    @Link(name = "demoqa", url = "https://demoqa.com")
    void successfulTest() {
        String name = "Daniil";

        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем страницу с формой и блочим рекламу", () -> {
            open("/automation-practice-form");
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });

        // Text
        step("Вводим имя", () -> {
            $("[id=firstName]").setValue(name);
        });
        step("Вводим фамилию", () -> {
            $("[id=lastName]").setValue("Davydov");
        });
        step("Вводим адрес почты", () -> {
            $("[id=userEmail]").setValue("da@da.ru");
        });

        // Radio-button
        step("Выбираем пол", () -> {
            $(byText("Male")).click();
        });
        step("Вводим номер телефона", () -> {
            $("[id=userNumber]").setValue("0123456789");
        });

        // Date
        step("Выбираем дату рождения", () -> {
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
        step("Выбираем предмет", () -> {
            $("#subjectsInput").sendKeys("m");
            $(byText("Maths")).click();
        });

        // Check-box
        step("Выбираем предмет", () -> {
            $(byText("Sports")).click();
            $(byText("Reading")).click();
            $(byText("Music")).click();
        });

        // Picture
        step("Выбрать файл", () -> {
            $("#uploadPicture").uploadFromClasspath("pic.png");
        });

        // Text
        step("Вводим адрес", () -> {
            $("[id=currentAddress]").setValue("Adress");
            Allure.getLifecycle().addAttachment(
                    "Screenshot1", "image/png", "png",
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            );
        });

        // Drop-down list
        step("Выбираем штат и город", () -> {
            $("#state").click();
            $(byText("NCR")).click();
            $("#city").click();
            $(byText("Delhi")).click();
        });

        step("Нажимаем разместить", () -> {
            $("[id=submit]").click();
        });

        // Result:
        step("Проверяем результат", () -> {
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

