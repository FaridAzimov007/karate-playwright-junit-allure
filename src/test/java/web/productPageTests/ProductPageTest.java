package web.productPageTests;

import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.auth.BaseWebAuth;
import web.config.TestBase;

@DisplayName("Product Page Tests")
public class ProductPageTest extends TestBase {

    @Tag("Sanity")
    @Owner("Farid Azimov - SDET")
    @Feature(value = "Products")
    @Description("Verification that the product added to the cart")
    @DisplayName("Add product to the cart")
    @Link(name = "JIRA Ticket", type = "jira", url = "https://your.jira.instance/browse/TICKET-123")
    @Test
    public void testLoginAndCartFlow() {
        new BaseWebAuth(page)
                .login()
                .verifyAddingProductToCart();
    }

}
