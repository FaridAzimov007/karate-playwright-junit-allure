package web.config;

import com.microsoft.playwright.Page;
import config.PlaywrightManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    protected Page page;

    @BeforeEach
    public void setUp() {
        PlaywrightManager.init(false, null);
        page = PlaywrightManager.getPage();
    }

    @AfterEach
    public void tearDown() {
        PlaywrightManager.close();
    }
}
