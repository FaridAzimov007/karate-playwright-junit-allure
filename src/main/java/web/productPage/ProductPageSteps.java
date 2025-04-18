package web.productPage;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import web.BasePage;
import web.cartPage.CartPageSteps;

import static web.productPage.ProductPageElements.*;

public class ProductPageSteps extends BasePage {
    public ProductPageSteps(Page page) {
        super(page);
    }

    private String productTitle;
    private String productPrice;

    @Step("E2E: Add product to cart and verify details in cart")
    public CartPageSteps addProductToCartAndVerify() {
        productTitle = getText(PRODUCT_TITLE_IN_PRD_PAGE);
        productPrice = getText(PRODUCT_PRICE_IN_PRD_PAGE);
        click(ADD_TO_CART_BUTTON);
        verifyCartCount(1);
        click(CART_LINK);
        return new CartPageSteps(page, productTitle, productPrice);

    }




    public void addProductToCart() {
        click(ADD_TO_CART_BUTTON);
    }

    public void verifyCartCount(int expectedCount) {
        String badgeText = getText(CART_BADGE);
        int actualCount = Integer.parseInt(badgeText);
        if (actualCount != expectedCount) {
            throw new AssertionError(
                    "Expected cart count: " + expectedCount + "but got: " + actualCount
            );
        }
    }

    @Step("Adding product to cart and verifying the count of cart badge")
    public void verifyAddingProductToCart() {
        addProductToCart();
        verifyCartCount(1);
    }


}
