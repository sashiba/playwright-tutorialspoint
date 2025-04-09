package simpleplaywrighttests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.sashiba.base.Driver;
import org.sashiba.base.PlaywrightConnection;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

public class TestNGDemoTest extends PlaywrightConnection {
    Driver driver;

    @BeforeMethod
    public void setup() throws UnsupportedEncodingException {
        driver = super.createConnection();
    }

    @AfterMethod
    public void tearDown() {
        super.closeConnection(driver);
    }

    @Test
    public void loginTest() {
        Page page = driver.getPage();
        try {
            page.navigate("https://www.duckduckgo.com");
            Locator locator = page.locator("[name=\"q\"]");
            locator.click();
            locator.fill("LambdaTest");
            page.keyboard().press("Enter");
            String title = page.title();

            if (title.equals("LambdaTest DuckDuckGo")) {
                super.setTestStatus("passed", "yes", page);
            } else {
                super.setTestStatus("failed", "no", page);
            }
        } catch (Exception err) {
            super.setTestStatus("failed", err.getMessage(), page);
            err.printStackTrace();
        }
    }
}
