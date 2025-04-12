package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage{
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id="FullName")
    private WebElement fullNameField;
    @FindBy(id = "Email")
    WebElement emailField;
    @FindBy(id="Enquiry")
    WebElement enquiryField;
    @FindBy(className = "contact-us-button")
    WebElement submitButton;
    @FindBy(css = "div.result")
    public WebElement successMessage;

    public void contactUs(String fullName,String email,String enquiry)
    {


        sendText(fullNameField,fullName);
        sendText(emailField,email);
        sendText(enquiryField,enquiry);
        clickOnButton(submitButton);
    }
}
