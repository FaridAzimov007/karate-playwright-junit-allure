package config;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaywrightManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaywrightManager.class);
    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    private PlaywrightManager() {
    }

    public static void init(boolean headless, String proxy) {
        if (playwrightThreadLocal.get() == null) {
            LOGGER.info("Initializing Playwright and Browser for thread: {}", Thread.currentThread().getName());
            try {
                // Создаём новый Playwright и сохраняем в ThreadLocal
                Playwright playwright = Playwright.create();
                playwrightThreadLocal.set(playwright);

                // Настраиваем опции браузера
                BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                        .setHeadless(headless) // Режим headless
                        .setProxy(proxy == null ? null : new Proxy(proxy));

                // Создаем браузер
                Browser browser = playwright.chromium().launch(options);
                browserThreadLocal.set(browser);

                // Создаем новый контекст браузера с настройкой
                BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize(1920, 1080) // Размер экрана
                        .setIgnoreHTTPSErrors(true) // Игнорирование HTTPS ошибок
                );
                contextThreadLocal.set(context);

                // Создаем новую вкладку (страницу)
                Page page = context.newPage();
                pageThreadLocal.set(page);

                LOGGER.info("Playwright initialized successfully for thread: {}", Thread.currentThread().getName());
            } catch (Exception e) {
                LOGGER.error("Failed to initialize Playwright for thread: {}", Thread.currentThread().getName(), e);
                throw new RuntimeException("Failed to initialize Playwright", e);
            }
        }
    }

    public static Page getPage() {
        if (pageThreadLocal.get() == null) {
            init(false, null); // Если `init` не вызывался, инициализация происходит автоматически
        }
        return pageThreadLocal.get();
    }

    public static void close() {
        try {
            LOGGER.info("Closing Playwright resources for thread: {}", Thread.currentThread().getName());

            // Закрываем контекст и браузер, относящийся к текущему потоку
            if (contextThreadLocal.get() != null) {
                contextThreadLocal.get().close();
            }
            if (browserThreadLocal.get() != null) {
                browserThreadLocal.get().close();
            }
            if (playwrightThreadLocal.get() != null) {
                playwrightThreadLocal.get().close();
            }

            // Удаляем ThreadLocal значения
            playwrightThreadLocal.remove();
            browserThreadLocal.remove();
            contextThreadLocal.remove();
            pageThreadLocal.remove();

            LOGGER.info("Playwright resources closed successfully for thread: {}", Thread.currentThread().getName());
        } catch (Exception e) {
            LOGGER.error("Failed to close Playwright resources for thread: {}", Thread.currentThread().getName(), e);
        }
    }
}


