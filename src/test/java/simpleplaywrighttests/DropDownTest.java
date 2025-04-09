package simpleplaywrighttests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DropDownTest {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        String selectUrl = "https://www.lambdatest.com/selenium-playground/select-dropdown-demo";
        String jQueryUrl = "https://www.lambdatest.com/selenium-playground/jquery-dropdown-search-demo";

        page.navigate(selectUrl);

        Locator selectLocator = page.locator("select#select-demo");
        Locator selectedValue = page.locator("p.selected-value");
        //select by value (attribute, @value)
        selectLocator.selectOption("Monday");
        System.out.println(selectedValue.textContent());
        assertThat(selectedValue).containsText("Monday");

        //select by label
        selectLocator.selectOption(new SelectOption().setValue("Friday"));
        System.out.println(selectedValue.textContent());
        assertThat(selectedValue).containsText("Friday");

        //select by index
        selectLocator.selectOption(new SelectOption().setIndex(3));
        System.out.println(selectedValue.textContent());
        assertThat(selectedValue).containsText("Tuesday");

        //select multiple
        Locator multipleSelectLocator = page.locator("#multi-select");
        multipleSelectLocator.selectOption(new String[]{"New Jersey", "Texas"});

        Locator options = multipleSelectLocator.locator("option");
        List<String> allOptionsText = options.allInnerTexts();
        System.out.println(options.count());
        allOptionsText.forEach(System.out::println);

        //select jQuery
        page.navigate(jQueryUrl);
        Locator countryDropdown = page.locator("span.select2-container").first();
        countryDropdown.click();
        Locator result = page.locator("span.select2-results ul li", new Page.LocatorOptions().setHasText("Japan"));
        result.click();

        //optGroup
        Locator files = page.locator("select[name='files']");
        files.selectOption("Ruby");

        //teardown
        playwright.close();
    }
}
