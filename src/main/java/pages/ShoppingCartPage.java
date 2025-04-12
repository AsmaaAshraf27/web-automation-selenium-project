package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage{
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css="h1")
    public WebElement shoppingCartHeader;
    @FindBy(name = "removefromcart")
    private WebElement removeFromCartCheckButton;
    @FindBy(name = "updatecart")
    private WebElement updateCartButton;
    @FindBy(name = "continueshopping")
    private WebElement continueShoppingButton;
    @FindBy(className = "product-name")
    public WebElement productName;
    @FindBy(className = "order-summary-content")
    public WebElement shoppingCartIsEmptyMessage;
    @FindBy(className = "qty-input")
    public WebElement itemQuantityField;
    @FindBy(className = "product-subtotal")
    public WebElement totalPrice;
    @FindBy(id = "checkout")
    private WebElement checkOutButton;
    @FindBy(id = "termsofservice")
    private WebElement agreeTermsOfServiceCheckButton;
    @FindBy(className = "checkout-as-guest-button")
    private WebElement checkOUtAsAGuestButton;
    public void removeProductFromCart()
    {
        clickOnButton(removeFromCartCheckButton);
        clickOnButton(updateCartButton);
    }
    public void updateProductQuantityFromShoppingCartPage(String itemQuantity)
    {
        clearElement(itemQuantityField);
        sendText(itemQuantityField,itemQuantity);
        clickOnButton(updateCartButton);
    }
    public void openCheckOutPageForRegisteredUsers()
    {
        clickOnButton(agreeTermsOfServiceCheckButton);
        clickOnButton(checkOutButton);
    }
    public void openCheckOutPageForGuestUsers()
    {
        clickOnButton(agreeTermsOfServiceCheckButton);
        clickOnButton(checkOutButton);
        clickOnButton(checkOUtAsAGuestButton);
    }



}
