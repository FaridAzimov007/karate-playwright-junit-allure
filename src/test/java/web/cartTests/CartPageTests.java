package web.cartTests;

import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.auth.BaseWebAuth;
import web.config.TestBase;

@DisplayName("Cart Page Tests")
public class CartPageTests extends TestBase {

    @Tag("SmokeUI")
    @Owner("Farid Azimov - SDET")
    @Feature(value = "Products")
    @Description("Verifying that a product has been added to the basket and that the name and price match")
    @DisplayName("Add product to the cart and verify matches of price and title")
    @Link(name = "JIRA Ticket", type = "jira", url = "https://your.jira.instance/browse/TICKET-123")
    @Test
    public void addProductToCartAndVerifyMatches() {
        new BaseWebAuth(page)
                .login()
                .addProductToCartAndVerify()
                .verifyProductTitleInCart()
                .verifyProductPriceInCart();
    }
}
