package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(className = "product-name")
    public WebElement productNameBreadCrumb;
    @FindBy(className = "email-a-friend-button")
    private  WebElement emailAFriendButton;
    @FindBy(linkText = "Add your review")
    private  WebElement addReviewLink;
    @FindBy(className = "add-to-wishlist-button")
    private WebElement addToWishListButton;
    @FindBy(className="ico-wishlist")
    private WebElement wishListLink;
    @FindBy(className = "add-to-compare-list-button")
    private WebElement addToCompareListButton;
    @FindBy(className="ico-cart")
    private WebElement shoppingCartLink;
    @FindBy(className="add-to-cart-button")
    private WebElement addToCartButton;
    @FindBy(id="addtocart_14_EnteredQuantity")
    private WebElement addToCartEnterQuantity;
    public void clickOnEmailAFriendButton()
    {
        clickOnButton(emailAFriendButton);
    }
    public void goToReviewPage()
    {
        clickOnButton(addReviewLink);
    }
    public void addProductToWishlist()
    {
        clickOnButton(addToWishListButton);
    }
    public void goToWishListPage()
    {
        clickOnButton(wishListLink);
    }
    public void addProductToCompareList()
    {
        clickOnButton(addToCompareListButton);
    }
    public void addProductToShoppingCart()
    {
        clickOnButton(addToCartButton);
    }
    public void goToShoppingCartPage()
    {
        clickOnButton(shoppingCartLink);
    }

}
