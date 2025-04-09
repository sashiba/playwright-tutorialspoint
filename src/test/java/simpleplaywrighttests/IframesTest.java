package simpleplaywrighttests;

import com.microsoft.playwright.*;

import java.util.List;

public class IframesTest {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        String url = "https://www.lambdatest.com/selenium-playground/iframe-demo/";
        page.navigate(url);

        FrameLocator frameLocator = page.frameLocator("#iFrame1");
        Locator bodyLocator = frameLocator.locator("//div[@contenteditable]");
        bodyLocator.click();
        bodyLocator.clear();
        bodyLocator.fill("test text in iFrame");

        //nested frames
        String urlNested = "https://letcode.in/frame";
        page.navigate(urlNested);
        List<Frame> frames = page.frames();
        frames.forEach(f->System.out.println(f.url()));

        FrameLocator firstFrame = page.frameLocator("#firstFr");
        firstFrame.getByPlaceholder("Enter name").type("TestName");

        FrameLocator nestedFrame = firstFrame.frameLocator("//iframe[@src='innerframe']");
        nestedFrame.getByPlaceholder("Enter email").type("text@example.com");

        //teardown
        playwright.close();
    }
}
