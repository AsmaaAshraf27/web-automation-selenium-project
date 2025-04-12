package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
        jse=(JavascriptExecutor) driver;
        action=new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @FindBy(linkText = "Register")
    private WebElement registerLink;
    @FindBy(linkText = "Log in")
    private WebElement loginLink;
    @FindBy(css = ".header-links li a.account")
    private WebElement muAccountLink;
    @FindBy(linkText = "Contact us")
    private WebElement contactUsLink;
    @FindBy(xpath= "//ul[@class='top-menu']//a[normalize-space()='Computers']")
    private WebElement computersMenu ;
    @FindBy(xpath = "//ul[@class='top-menu']//a[normalize-space()='Notebooks']" )
    private WebElement noteBooksMenu ;

    public void openRegisterPage()
    {
       // registerLink.click();
        clickOnButton(registerLink);

    }

    public void openLoginPage()
    {

        clickOnButton(loginLink);

    }
    public void openMyAccountPage()
    {
        clickOnButton(muAccountLink);
    }
    public void openContactUsPage()
    {
        scrollToBottom();
        clickOnButton(contactUsLink);
    }
    public void selectNotebooksMenu()
    {
        action.moveToElement(computersMenu).moveToElement(noteBooksMenu).click().build().perform();

    }
}
