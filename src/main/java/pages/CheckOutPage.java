package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage extends BasePage{
    public CheckOutPage(WebDriver driver) {
        super(driver);
    }
    String productname="Black & White Diamond Heart";
    //as a guest
    @FindBy(id = "BillingNewAddress_FirstName")
    private WebElement guestFirstName;
    @FindBy(id = "BillingNewAddress_LastName")
    private WebElement guestLastName;
    @FindBy(id = "BillingNewAddress_Email")
    private WebElement guestEmailField;
    //
    @FindBy(id = "BillingNewAddress_CountryId")
    private WebElement countryMenu;
    @FindBy(id = "BillingNewAddress_City")
    private WebElement cityField;
    @FindBy(id = "BillingNewAddress_Address1")
    private WebElement address1Field;
    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private WebElement postalCodeField;
    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private WebElement phoneNumberField;
    @FindBy(className = "new-address-next-step-button")
    private WebElement continueToShippingAddressButton;
    //
    @FindBy(className = "new-address-next-step-button")
    private WebElement  billingContinueButton;
    @FindBy(id ="billing-address-select" )
    private WebElement  countryListForBillingAddress;
    @FindBy(id ="shipping-address-select" )
    private WebElement  countryListForShippingAddress;
    @FindBy(xpath = "//div[@id='shipping-buttons-container']/input[@value='Continue']")
    private WebElement  shippingAddressContinueButton;
    @FindBy(id="shippingoption_0")
    private WebElement shippingOption;
    @FindBy(className="shipping-method-next-step-button")
    private WebElement shippingMethodContinueButton;
    @FindBy(id="paymentmethod_0")
    private WebElement paymentMethod;
    @FindBy(className = "payment-method-next-step-button")
    private WebElement paymentMethodContinueButton;
    @FindBy(css = "p")
    public WebElement paymentInfoMessage;
    @FindBy(className = "payment-info-next-step-button")
    private WebElement paymentInfoContinueButton;
    @FindBy(linkText = "Black & White Diamond Heart")
    public WebElement productName;
    @FindBy(className = "confirm-order-next-step-button")
    private WebElement confirmOrderButton;
    @FindBy(css = "h1")
    public WebElement thankYouMessage;
    @FindBy(css = "div.title")
    public WebElement successMessage;
    @FindBy(linkText = "Click here for order details.")
    private WebElement orderDetailsLink;
    @FindBy(className = "checkout-as-guest-button")
    private WebElement checkOUtAsAGuestButton;

    public void registeredUsersCanCheckOut(String city,String address1, String postalCode, String phoneNumber,String countryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(countryMenu));
        wait.until(ExpectedConditions.elementToBeClickable(countryMenu));
        select = new Select(countryMenu);
        select.selectByVisibleText(countryName);
        sendText(cityField, city);
        sendText(address1Field, address1);
        sendText(postalCodeField, postalCode);
        sendText(phoneNumberField, phoneNumber);
        clickOnButton(billingContinueButton);
        clickOnButton(shippingAddressContinueButton);
        clickOnButton(shippingOption);
        clickOnButton(shippingMethodContinueButton);
        clickOnButton(paymentMethod);
        clickOnButton(paymentMethodContinueButton);

    }

public void orderConfirmation()
{
    clickOnButton(paymentInfoContinueButton);

        clickOnButton(confirmOrderButton);


}
public void orderDetails()
{
    clickOnButton(orderDetailsLink);
}
public void guestUserCanCheckOUt(String firstName,String lastName,String email,String city,String address1, String postalCode, String phoneNumber,String countryName)
{
    select = new Select(countryMenu);
    select.selectByVisibleText(countryName);
    sendText(guestFirstName,firstName);
    sendText(guestLastName,lastName);
    sendText(guestEmailField,email);
    sendText(cityField, city);
    sendText(address1Field, address1);
    sendText(postalCodeField, postalCode);
    sendText(phoneNumberField, phoneNumber);
    clickOnButton(billingContinueButton);
    clickOnButton(shippingAddressContinueButton);
    clickOnButton(shippingOption);
    clickOnButton(shippingMethodContinueButton);
    clickOnButton(paymentMethod);
    clickOnButton(paymentMethodContinueButton);
}
}
