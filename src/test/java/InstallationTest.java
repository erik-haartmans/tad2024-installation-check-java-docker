import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;

@Slf4j
public class InstallationTest {

    private RemoteWebDriver driver;
    private static BrowserWebDriverContainer<?> driverContainer;
    private static ChromeOptions chromeOptions;

    @BeforeAll
    public static void beforeAll() {
        chromeOptions = new ChromeOptions();
        driverContainer = new BrowserWebDriverContainer<>().withCapabilities(chromeOptions);
        driverContainer.start();
    }

    @BeforeEach
    public void beforeEach() {
        // create & start webdriver container
        driver = new RemoteWebDriver(driverContainer.getSeleniumAddress(), chromeOptions);
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    @AfterAll
    public static void afterAll() {
        driverContainer.stop();
    }

    @Test
    public void TabTitleGoogleTest() {
        driver.get("https://www.google.com");
        log.info(driver.getTitle());
    }

}
