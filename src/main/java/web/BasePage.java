package web;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import io.qameta.allure.Step;

public class BasePage {
    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected Locator getLocator(String selector) {
        return page.locator(selector);
    }

    @Step("Navigate to {url}")
    protected void navigateTo(String url) {
        page.navigate(url);
    }

    @Step("Wait for element to be visible: {selector}")
    protected void waitForVisibility(String selector) {
        getLocator(selector).waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    @Step("Click on element: {selector}")
    protected void click(String selector) {
        // Дожидаемся видимости элемента перед кликом
        waitForVisibility(selector);
        getLocator(selector).click();
    }

    @Step("Type text in to element: {selector}")
    public void type(String selector, String text) {
        waitForVisibility(selector);
        getLocator(selector).fill(text);
    }

    @Step("Get text from element: {selector}")
    public String getText(String selector) {
        waitForVisibility(selector);
        return getLocator(selector).innerText();
    }

    public static String extractExactPrice(String text) {
        if (text == null) {
            return "";
        }

        if (!text.contains(",") && !text.contains(" ")) {
            return text.trim();
        }

        return text.replaceAll("[^\\d]", "");
    }

    @Step("Select option from dropdown: {selector}")
    public void selectOption(String selector, String label) {
        waitForVisibility(selector);
        getLocator(selector).selectOption(new SelectOption().setLabel(label));
    }


}
