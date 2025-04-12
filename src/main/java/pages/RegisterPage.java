package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "gender-female")
    private WebElement gender;
    @FindBy(id = "FirstName")
    private WebElement fName;
    @FindBy(id = "LastName")
    private WebElement lName;
    @FindBy(id = "Email")
    private WebElement email;
    @FindBy(id = "Password")
    private WebElement password;
    @FindBy(id = "ConfirmPassword")
    private WebElement passwordConfirmation;
    @FindBy(id = "register-button")
    private WebElement registerButton;
    @FindBy(className = "result")
    public WebElement successRegisterResult;
    @FindBy(className ="register-continue-button")
    private WebElement continueButton;
    @FindBy(linkText = "Log out")
    public WebElement logOutLink;

    public void userRegistration(String firstName, String lastName, String Email, String pass) {
//        fName.sendKeys(firstName);
//        lName.sendKeys(lastName);
//        email.sendKeys(Email);
//        password.sendKeys(pass);
//        passwordConfirmation.sendKeys(pass);
//        registerButton.click();
        clickOnButton(gender);
        sendText(fName, firstName);
        sendText(lName, lastName);
        sendText(email, Email);
        sendText(password, pass);
        sendText(passwordConfirmation, pass);
        clickOnButton(registerButton);
    }
    public void GoBackToTheHomePage()
    {

        clickOnButton(continueButton);
    }
    public void userLogout()
    {
        clickOnButton(logOutLink);
    }

}
