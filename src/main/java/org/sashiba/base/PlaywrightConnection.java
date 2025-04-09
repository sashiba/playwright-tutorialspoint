package org.sashiba.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PlaywrightConnection {
    protected Driver createConnection() throws UnsupportedEncodingException {
        try {

            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
//            Playwright playwright = Playwright.create();
//            BrowserType chromium = playwright.chromium();
//            String caps = URLEncoder.encode(LTCapability.getDefaultTestCapability().toString(), "utf-8");
//            String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + caps;
//            Browser browser = chromium.connect(cdpUrl);
//            Page page = browser.newPage();
            return new Driver(playwright, browser, page);
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    protected void closeConnection(Driver driver) {
        driver.getPage().close();
        driver.getBrowser().close();
        driver.getPlaywright().close();
    }

    protected static void setTestStatus(String status, String message, Page page) {

        page.evaluate("_=> {}", "lambdatest_action: { \"action\": ");
    }
}
