package simpleplaywrighttests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotCaret;
import com.microsoft.playwright.options.SelectOption;

import java.nio.file.Paths;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ScreenshotTest {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        String url = "https://www.lambdatest.com/selenium-playground/simple-form-demo";

        page.navigate(url);

        //screenshot
        //page.screenshot(); for report
        Page.ScreenshotOptions options = new Page.ScreenshotOptions();
        options.setPath(Paths.get("./screenshots/s1.png"));
        page.screenshot(options);

        //full page screenshot
        options.setFullPage(true);
        options.setPath(Paths.get("./screenshots/fullPage1.png"));
        page.screenshot(options);

        //locator screenshot
        Locator.ScreenshotOptions locatorOptions = new Locator.ScreenshotOptions();
        Locator buttonLocator = page.locator("//button[text()='Book a Demo']");
        locatorOptions.setPath(Paths.get("./screenshots/locator.png"));
        buttonLocator.screenshot(locatorOptions);

        //masking locator
        Locator inputLocator = page.locator("input#user-message");
        inputLocator.type("test 123");
        inputLocator.scrollIntoViewIfNeeded();
        options.setPath(Paths.get("./screenshots/input.png"));
        options.setFullPage(false);
        options.setMask(List.of(inputLocator));
        page.screenshot(options);

        //caret show/hide
        inputLocator.clear();
        inputLocator.click();
        options.setPath(Paths.get("./screenshots/caret.png"));
        options.setCaret(ScreenshotCaret.HIDE);
        page.screenshot(options);

        //teardown
        playwright.close();
    }
}
