package simpleplaywrighttests;

import com.microsoft.playwright.*;

public class AlertsTest {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        // for https://the-internet.herokuapp.com/digest_auth .setHttpCred
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));
        Page page = browserContext.newPage();
        String url = "https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo";
        page.navigate(url);

        Locator buttons = page.locator("text='Click Me'");

        //accept alert
        page.onceDialog(alert -> {
            String message = alert.message();
            System.out.println(message);
            alert.accept();
        });
        buttons.first().click();
        //page.waitForTimeout(3000); // Thread.sleep

        //dismiss allert
        page.onceDialog(alert -> {
            String message = alert.message();
            System.out.println(message);
            alert.dismiss();
        });
        buttons.nth(1).click();
        System.out.println(page.locator("#confirm-demo").textContent());

        //enter text in alert
        page.onceDialog(alert -> {
            String message = alert.message();
            System.out.println(message);
            System.out.println(alert.defaultValue());

            alert.accept("entered test in box");
        });
        buttons.last().click();
        System.out.println(page.locator("#prompt-demo").textContent());

        // onceDialog - handle dialog once, then destroy object
        // onDialog - global setting executed for every dialog, handle dialog, dont destroy object

        page.navigate("https://the-internet.herokuapp.com/digest_auth");


        playwright.close();
    }
}
