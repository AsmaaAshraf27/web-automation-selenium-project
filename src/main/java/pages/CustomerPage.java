package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerPage extends BasePage{
    public CustomerPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(linkText = "Change password")
    private WebElement changePasswordLink;
    @FindBy(id = "OldPassword")
    private WebElement oldPasswordField;
    @FindBy(id = "NewPassword")
    private WebElement newPasswordField;
    @FindBy(id = "ConfirmNewPassword")
    private WebElement confirmNewPasswordField;
    @FindBy(className = "change-password-button")
    private WebElement changePasswordButton;
    @FindBy(css="div.result")
    public WebElement changePasswordResultMessage;
    public void openChangePasswordPage()
    {
        clickOnButton(changePasswordLink);
    }
    public void changePassword(String oldPassword,String newPassword)
    {

        sendText(oldPasswordField,oldPassword);
        sendText(newPasswordField,newPassword);
        sendText(confirmNewPasswordField,newPassword);
        clickOnButton(changePasswordButton);

    }


}
