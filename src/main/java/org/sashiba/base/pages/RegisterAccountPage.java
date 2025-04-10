package org.sashiba.base.pages;

import com.microsoft.playwright.Page;

public class RegisterAccountPage {
    private Page page;

    public RegisterAccountPage(Page page) {
        this.page = page;
    }

    public void clickContinue() {
        page.locator("input[value='Continue']").click();
    }

    public boolean isWarningVisible() {
        return page.locator("div.alert.alert-danger").isVisible();
    }

    public int areMandatoryWarningMessageVisible() {
        return page.locator("div.text-danger").count();
    }

    public void registerUserAccount(String firstName, String lastName, String email, String phone, String password) {
        page.getByLabel("First Name").fill(firstName);
        page.getByLabel("Last Name").fill(lastName);
        page.getByLabel("E-Mail").fill(email);
        page.getByLabel("Telephone").fill(phone);
        page.getByLabel("Password", new Page.GetByLabelOptions().setExact(true)).fill(password);
        page.getByLabel("Password Confirm").fill(password);
        page.locator("label[for='input-agree']").click();
        clickContinue();
    }
}
