package simpleplaywrighttests;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InterractWithInputsTest {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");

        //page.locator("//*[@id='user-message']");
        page.locator("input#user-message").type("Helloworld123");
        page.locator("id=showInput").click();

        Locator messageElement = page.locator("#message");
        String message = messageElement.textContent();
        System.out.println(message);
        assertThat(messageElement).hasText("Helloworld123");

        //type vs fill
        //fill - clear and paste whole text (skip event listeners)
        //type - writes like user
        page.navigate("https://www.lambdatest.com/selenium-playground/generate-file-to-download-demo");
        page.locator("#textbox").fill("Data entered in the below textarea will be download with file name 'Lambdainfo.txt'." +
                "Data entered in the below textarea will be download with file name 'Lambdainfo.txt'.");

        //attributes, input value
        page.navigate("https://letcode.in/edit");
        String value = page.locator("#getMe").inputValue();
        System.out.println(value);

        String placeholder = page.locator("#fullName").getAttribute("placeholder");
        System.out.println(placeholder);

        Locator fullNameLocator = page.locator("#fullName");
        assertThat(fullNameLocator).hasAttribute("placeholder", "Enter first & last name"); //this is recommended way?

        //clear
        page.locator("id=clearMe").clear();

        //checkbox
        page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");

        Locator checkBoxLocator = page.locator("#isAgeSelected");
        assertThat(checkBoxLocator).not().isChecked();
        checkBoxLocator.check();
        assertThat(checkBoxLocator).isChecked();


        //teardown
        playwright.close();
    }
}
