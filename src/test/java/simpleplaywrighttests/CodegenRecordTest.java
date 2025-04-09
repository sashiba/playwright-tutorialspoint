package simpleplaywrighttests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CodegenRecordTest {
    public static void main(String[] args) {
        //mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://ecommerce-playground.lambdatest.io/"
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("./videos/"))
                    .setRecordVideoSize(1280,720));
            //context.close();
            Page page = context.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" My account")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-Mail Address")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-Mail Address")).fill("koushik350@gmail.com");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("Pass123$");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Edit your account")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name*")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name*")).fill("ParmarTest");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
            Locator successMessage = page.getByText("Success: Your account has");
            assertThat(successMessage).isVisible();
//            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout").setExact(true)).click();
//            Locator accountLogout = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(" Account Logout"));
//            assertThat(accountLogout).isVisible();
        }
    }
}
