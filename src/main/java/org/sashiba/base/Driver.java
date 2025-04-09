package org.sashiba.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Driver {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    public Driver(Playwright playwright, Browser browser, Page page) {
        this.playwright = playwright;
        this.browser = browser;
        this.page = page;
    }

    public Playwright getPlaywright() {
        return playwright;
    }


    public Browser getBrowser() {
        return browser;
    }

    public Page getPage() {
        return page;
    }
}
