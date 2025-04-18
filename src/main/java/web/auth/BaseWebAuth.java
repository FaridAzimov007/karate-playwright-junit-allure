package web.auth;

import com.microsoft.playwright.Page;
import config.PropertiesLoader;
import io.qameta.allure.Step;
import web.BasePage;
import web.productPage.ProductPageSteps;

import static web.productPage.ProductPageElements.TITLE;

public class BaseWebAuth extends BasePage {
    public BaseWebAuth(Page page) {
        super(page);
    }

    String baseUrl = PropertiesLoader.getProperties("base-url");
    String userLogin = PropertiesLoader.getProperties("user-login");
    String userPass = PropertiesLoader.getProperties("user-password");



    @Step("Getting test data and making an authorisation attempt")
    public ProductPageSteps login() {
        navigateTo(baseUrl);
        getLocator("#user-name").fill(userLogin);
        getLocator("#password").fill(userPass);
        getLocator("#login-button").click();
        getLocator(TITLE).isVisible();
        return new ProductPageSteps(page);
    }

}
