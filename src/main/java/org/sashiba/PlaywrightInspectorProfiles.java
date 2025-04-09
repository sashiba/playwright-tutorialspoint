package org.sashiba;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlaywrightInspectorProfiles {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext();

        Page page = browserContext.newPage();
        String url = "https://ecommerce-playground.lambdatest.io/index.php?route=account/login";
        page.navigate(url);

        page.pause(); //playwright inspector

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" My account")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-Mail Address")).fill("koushik350@gmail.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("Pass123$");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Edit your account")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name*")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name*")).fill("ParmarTest");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
        Locator successMessage = page.getByText("Success: Your account has");
        assertThat(successMessage).isVisible();


        playwright.close();
//        $env:PLAYWRIGHT_JAVA_SRC="src/main/java"
//        $env:PWDEBUG=1
//        mvn test -P debug
    }
}
