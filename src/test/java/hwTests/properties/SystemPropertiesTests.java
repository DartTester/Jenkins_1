package hwTests.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {
    @Test
    void someTest1() {
        String browser = System.getProperty("browser");
        System.out.println(browser); // null
    }

    @Test
    void someTest2() {
        System.setProperty("browser", "safari");
        String browser = System.getProperty("browser");
        System.out.println(browser); // safari
    }

    @Test
    void someTest3() {
        String browser = System.getProperty("browser", "opera");
        System.out.println(browser); // opera
    }

    @Test
    void someTest4() {
        System.setProperty("browser", "safari");
        String browser = System.getProperty("browser", "opera");
        System.out.println(browser); // safari
    }

    @Test
    void someTest5() {
        System.setProperty("anyValue", "any text");
        String text = System.getProperty("anyValue", "some text");
        System.out.println(text); //
    }

    @Test
    @Tag("test6")
    void someTest6() {
        String browser = System.getProperty("browser", "chrome");
        String version = System.getProperty("version", "101");
        String browserSize = System.getProperty("browserSize", "1920x1080");

        System.out.println(browser); //
        System.out.println(version); //
        System.out.println(browserSize); //

        /*
            From idea
                chrome
                101
                1920x1080
            gradle clean properties_test6
                chrome
                101
                1920x1080
            gradle clean properties_test6 -Dbrowser=opera
                opera
                101
                1920x1080
            gradle clean properties_test6 -Dbrowser=opera -Dversion=102
                opera
                102
                1920x1080
         */
    }

    @Test
    @Tag("hello")
    void someTest7() {
        System.out.println("Hello " + System.getProperty("anyText"));

        // gradle clean hello_test -DanyText=world!
        //     Hello world!
    }
}
