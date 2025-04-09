package simpleplaywrighttests;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LaunchBrowserTest {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        Locator myAccount = page.locator("//a[contains(., 'My account')][@role='button']");
        myAccount.hover();
        page.click("//a[contains(., 'Login')]");

        assertThat(page).hasTitle("Account Login");

        page.getByPlaceholder("E-Mail Address").type("koushik350@gmail.com");
        page.getByPlaceholder("Password").type("Pass123$");
        page.locator("//input[@value='Login']").click();

        assertThat(page).hasTitle("My Account");

        page.close();
        browser.close();
        playwright.close();
    }
}

