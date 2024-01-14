package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static final String AUTOMATE_USERNAME = "dummyusername";
    public static final String AUTOMATE_ACCESS_KEY = "dummypassword";
    public static final String browserStackURL = "https://dummyusername:dummypassword@hub-cloud.browserstack.com/wd/hub";
    public static WebDriver driver;
    public static DesiredCapabilities cap;
    public static UtilitiesClass ut = new UtilitiesClass();
    public static FileInputStream fis = null;
    public static Properties prop;
    public static ThreadLocal<WebDriver> tDriver = new ThreadLocal();
    public static final String betaProp = System.getProperty("user.dir") + "/src/test/java/Resources/staging.properties";

    public TestBase() {
    }

    public void initialization() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(betaProp);
        prop.load(fis);
        String browser = prop.getProperty("browser");
        String url = prop.getProperty("url");
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if (browser.equalsIgnoreCase("browserStack")) {
            cap = new DesiredCapabilities();
            cap.setCapability("os", "Windows");
            cap.setCapability("os_version", "10");
            cap.setCapability("browser", "Chrome");
            cap.setCapability("browser_version", "latest");
            cap.setCapability("name", "BStack-[Java] Sample Test");
            cap.setCapability("build", "BStack Build Number 1");
            cap.setCapability("project", "Sample sandbox project");

            try {
                driver = new RemoteWebDriver(new URL("https://dummyusername:dummypassword@hub-cloud.browserstack.com/wd/hub"), cap);
            } catch (MalformedURLException var5) {
                var5.printStackTrace();
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get(url);
        tDriver.set(driver);
    }

    public static synchronized WebDriver getDriver() {
        return (WebDriver)tDriver.get();
    }
}
