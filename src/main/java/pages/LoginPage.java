package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "Email")
    private WebElement emailField;
    @FindBy(id = "Password")
    private WebElement passwordField;
//    @FindBy(id = "RememberMe")
//    private WebElement rememberMeCheckBox;
    @FindBy(className= "login-button")
    private WebElement LoginButton;
    public void userLogin(String email,String password)
    {
        sendText(emailField,email);
        sendText(passwordField,password);
        clickOnButton(LoginButton);
    }


}
