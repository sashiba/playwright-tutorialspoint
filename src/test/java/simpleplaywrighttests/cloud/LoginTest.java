package simpleplaywrighttests.cloud;

import com.google.gson.JsonObject;
import com.microsoft.playwright.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        JsonObject capabilities = new JsonObject();
        JsonObject ltOptions = new JsonObject();

        String user = "test";
        String accessKey = "123";

        capabilities.addProperty("browsername", "Chrome");
        ltOptions.addProperty("browserVersion", "latest");
        ltOptions.addProperty("platform", "Windows 10");
        ltOptions.addProperty("name", "Playwright Text");
        ltOptions.addProperty("build", "Playwright Java Build 2");
        ltOptions.addProperty("user", user);
        ltOptions.addProperty("accessKey", accessKey);
        capabilities.add("LT:Options", ltOptions);

        //Playwright Test
        Playwright playwright = Playwright.create();
        BrowserType chromium = playwright.chromium();
        String caps = URLEncoder.encode(capabilities.toString(), "utf-8");
        String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + caps;
        Browser browser = chromium.connect(cdpUrl);
        Page page = browser.newPage();

        try {
            String selectUrl = "https://www.lambdatest.com/selenium-playground/select-dropdown-demo";
            page.navigate(selectUrl);
            Locator selectLocator = page.locator("select#select-demo");
            String title = page.title();

            if (title.equals("Lambda")) {
                setTestStatus("passed", "Title matched", page);
            } else {
                setTestStatus("failed", "Title does not match", page);
            }

        } catch (Exception err) {
            setTestStatus("failed", err.getMessage(), page);
        }
        browser.close();
        playwright.close();
    }

    public static void setTestStatus(String status, String message, Page page) {

        page.evaluate("_=> {}", "lambdatest_action: { \"action\": ");
    }
}
