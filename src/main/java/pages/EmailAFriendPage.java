package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailAFriendPage extends BasePage{
    public EmailAFriendPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "FriendEmail")
    private WebElement friendEmailField;
    @FindBy(id = "YourEmailAddress")
    private WebElement yourEmailAddressField;
    @FindBy(id = "PersonalMessage")
    private WebElement personalMessageField;
    @FindBy(className = "send-email-a-friend-button")
    private WebElement sendEmailAFriendButton;
    @FindBy(css="div.result")
    public WebElement successMessage;
    @FindBy(css = "div.validation-summary-errors")
    public WebElement errorMessage;
    public void registeredUserCanSendAnEmailToHisFriend(String friendEmail , String personalMessage)
    {
        sendText(friendEmailField,friendEmail);
        //sendText(yourEmailAddressField,userEmail);
        sendText(personalMessageField,personalMessage);
        clickOnButton(sendEmailAFriendButton);
    }
    public void nonRegisteredUserCanNotSendAnEmailToHisFriend(String friendEmail ,String yourEmail, String personalMessage)
    {
        sendText(friendEmailField,friendEmail);
        sendText(yourEmailAddressField,yourEmail);
        sendText(personalMessageField,personalMessage);
        clickOnButton(sendEmailAFriendButton);
    }




}
