package hw_tests;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class Demo_qa_form_Jenkins extends TestBase {

    @Test
    void successfulTest1() {
        String name = "Alex";

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        // Text
        $("[id=firstName]").setValue("Daniil"); //  $("#firstName").setValue("Daniil");
        $("[id=lastName]").setValue("Davydov"); //  $("#firstName").setValue("Davydov");
        $("[id=userEmail]").setValue("da@da.ru"); //  $("#firstName").setValue("da@da.ru");

        // Radio-button
        $(byText("Male")).click(); // $("[for='gender-radio-1']").click(); - аналогично
        $("[id=userNumber]").setValue("0123456789"); // $("#userNumber").setValue("0123456789");

        // Date
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("August");
        $(".react-datepicker__year-select").selectOption("1993");
        $("[aria-label='Choose Tuesday, August 10th, 1993']").click();

        // Text
        $("#subjectsInput").sendKeys("m");
        $(byText("Maths")).click();
        // $("#subjectsInput").sendKeys("Maths");
        // $("#subjectsInput").pressEnter();

        // Check-box
        $(byText("Sports")).click(); // $("[for='hobbies-checkbox-1']").click();  - аналогично
        $(byText("Reading")).click(); // $("#hobbiesWrapper").$(byText("Sports")).click();
        $(byText("Music")).click();

        // Picture
        // $("#uploadPicture").uploadFile (new File("src/test/resources/pic.png"));
        $("#uploadPicture").uploadFromClasspath("pic.png");

        // Text
        $("[id=currentAddress]").setValue("Adress"); // $("#currentAddress").setValue("Adress");

        // Drop-down list
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        $("[id=submit]").click();

        // Result:
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
    }
}

