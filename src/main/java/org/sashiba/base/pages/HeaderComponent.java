package org.sashiba.base.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HeaderComponent {
    private Page page;

    public HeaderComponent(Page page) {
        this.page = page;
    }

    private Locator getMyAccount() {
        return this.page.locator("//a[contains(., 'My account')]").last();
    }

    public void clickLogin() {
        getMyAccount().hover();
        page.locator("//span[text()[normalize-space()='Login']]").click();
    }

    public void clickRegister() {
        getMyAccount().hover();
        page.locator("//span[text()[normalize-space()='Register']]").click();
    }
}
