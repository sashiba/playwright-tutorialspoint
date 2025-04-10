package simpleplaywrighttests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.sashiba.base.Driver;
import org.sashiba.base.PlaywrightConnection;
import org.sashiba.base.pages.HeaderComponent;
import org.sashiba.base.pages.RegisterAccountPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

public class PageObjectTest extends PlaywrightConnection {
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
        HeaderComponent headerComponent = new HeaderComponent(page);
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(page);
        try {
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            page.waitForLoadState(LoadState.LOAD);

            headerComponent.clickRegister();
            registerAccountPage.clickContinue();

            boolean warningVisible = registerAccountPage.isWarningVisible();
            if (warningVisible) {
                super.setTestStatus("passed", "visible", page);
            } else {
                super.setTestStatus("failed", "not visible", page);
            }

            int count = registerAccountPage.areMandatoryWarningMessageVisible();
            if (count > 0) {
                super.setTestStatus("passed", "visible", page);
            } else {
                super.setTestStatus("failed", "not visible", page);
            }

            registerAccountPage.registerUserAccount("testFirstname", "testLastname", "test1234@exmaple.com",
                    "12312312313", "Pswd123");

        } catch (Exception err) {
            super.setTestStatus("failed", err.getMessage(), page);
            err.printStackTrace();
        }
    }
}
