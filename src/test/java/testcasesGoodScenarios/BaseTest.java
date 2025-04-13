package testcasesGoodScenarios;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest extends AbstractTestNGCucumberTests {
    //public static WebDriver driver;--->Before Parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //كده كل Thread (يعني كل Test Case بتشتغل في نفس الوقت) بيكون ليه نسخة مستقلة من الـ WebDriver
    //وبنستخدمها من خلال:
    // public static WebDriver getDriver() {
    //    return driver.get();}

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static String downloadPath = System.getProperty("user.dir") + File.separator + "downloads";
    File file = new File(downloadPath);

    public static ChromeOptions chromeOption() {
        File file = new File(downloadPath);
        if (!file.exists()) {
            file.mkdir();
        }

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();

        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("safebrowsing.enabled", true);
        chromePrefs.put("plugins.always_open_pdf_externally", true); // لفتح الـ PDF برا المتصفح

        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-features=DownloadBubble"); // يمنع البابل الجديدة بتاعت التحميل
        options.setAcceptInsecureCerts(true);
        return options;
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setUpDriver(@Optional("chrome") String browserName) {
        WebDriver localDriver;
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            localDriver = new ChromeDriver(chromeOption());
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            localDriver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("headlessChrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new"); // Use --headless=new for latest chrome
            options.addArguments("--disable-gpu");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--allow-insecure-localhost");
            WebDriverManager.chromedriver().setup();
            localDriver = new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        driver.set(localDriver);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get("https://demowebshop.tricentis.com/");
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }

    // Take screenshot when the test case fails and add it to the screenshot folder
    @AfterMethod
    public void takeScreenshotWhenTestFails(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed!");
            System.out.println("Taking screenshot.....");
            Helper.captureScreenShot(getDriver(), result.getName());
        }
    }

    // لو بتستنى ملف معين باسمه
    public boolean waitForDownloadToComplete(String downloadPath, String fileName, int timeoutInSeconds) {
        File downloadedFile = new File(downloadPath + File.separator + fileName);
        int timeElapsed = 0;

        while (!downloadedFile.exists() && timeElapsed < timeoutInSeconds) {
            try {
                Thread.sleep(1000);
                timeElapsed++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return downloadedFile.exists();
    }

    // الميثود الجديدة اللي بتستنى أي ملف PDF يتحمل
    public boolean waitForAnyPdfDownload(String downloadPath, int timeoutInSeconds) {
        int timeElapsed = 0;
        File folder = new File(downloadPath);
        File[] listOfFiles;

        while (timeElapsed < timeoutInSeconds) {
            listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
            if (listOfFiles != null && listOfFiles.length > 0) {
                return true;
            }
            try {
                Thread.sleep(1000);
                timeElapsed++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
