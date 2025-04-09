package simpleplaywrighttests;

import com.microsoft.playwright.*;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WindowHandlingTest {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        String url = "https://www.lambdatest.com/selenium-playground/window-popup-modal-demo";
        page.navigate(url);

        //new window
        Page popupPage = page.waitForPopup(() -> {
            page.getByText("Follow On Twitter").click();
        });
        popupPage.waitForLoadState();
        assertThat(popupPage).hasTitle("LambdaTest (@lambdatesting) / X");
        System.out.println(popupPage.title());
        popupPage.getByText("Log in").click();
        popupPage.close();

        //multiple windows
        Page windows = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(p -> p.context().pages().size() == 3), () -> {
            page.getByText("Follow All").click();
        });
        List<Page> pages = windows.context().pages();
        System.out.println(pages.size());
        pages.forEach(p -> {
            p.waitForLoadState();
            System.out.println(p.title());
        });

        Page page1 = pages.get(0);
        Page page2 = pages.get(1);

        //teardown
        playwright.close();
    }
}
