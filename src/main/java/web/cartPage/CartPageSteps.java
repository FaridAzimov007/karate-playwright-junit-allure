package web.cartPage;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import web.BasePage;

import static web.cartPage.CartPageElements.PRODUCT_PRICE_IN_CART;
import static web.cartPage.CartPageElements.PRODUCT_TITLE_IN_CART;

public class CartPageSteps extends BasePage {

    private final String expectedProductTitle;
    private final String expectedProductPrice;
    public CartPageSteps(Page page, String expectedProductTitle, String expectedProductPrice) {
        super(page);
        this.expectedProductTitle = expectedProductTitle;
        this.expectedProductPrice = expectedProductPrice;

    }

    @Step("Verify product title matches in cart")
    public CartPageSteps verifyProductTitleInCart() {
        String actualTitle = getText(PRODUCT_TITLE_IN_CART);
        if (!actualTitle.equals(expectedProductTitle)) {
            throw new AssertionError(
                    "Expected product title: " + expectedProductTitle + " but got: " + actualTitle
            );
        }
        return this; // Для цепочки вызовов
    }

    @Step("Verify product price matches in cart")
    public CartPageSteps verifyProductPriceInCart() {
        String actualPrice = getText(PRODUCT_PRICE_IN_CART);
        if (!actualPrice.equals(expectedProductPrice)) {
            throw new AssertionError(
                    "Expected product price: " + expectedProductPrice + " but got: " + actualPrice
            );
        }
        return this; // Для цепочки вызовов
    }
}
