package simpleplaywrighttests;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BrowserContextTest {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext();

        Page page = browserContext.newPage();
        String url = "https://ecommerce-playground.lambdatest.io/index.php?route=account/login";
        page.navigate(url);
        page.getByPlaceholder("E-Mail Address").fill("koushik350@gmail.com");
        page.getByPlaceholder("Password").fill("Pass123$");
        page.locator("//input[@value='Login']").click();

        Locator editMyAcc = page.getByText("Edit your account information");
        assertThat(editMyAcc).isVisible();

        Page newTabOne = browserContext.newPage();
        newTabOne.navigate(url);
        assertThat(newTabOne.getByText("Edit your account information")).isVisible();
        Page newTabTwo = page.context().newPage();

        //new browser
        BrowserContext browserContextTwo = browser.newContext();
        Page userPage = browserContextTwo.newPage();
        userPage.navigate(url);
        assertThat(userPage.getByText("Edit your account information")).not().isVisible();

        Browser firefox = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        firefox.newPage();

        //teardown
        browser.close();
        firefox.close();
        playwright.close();
    }
}
