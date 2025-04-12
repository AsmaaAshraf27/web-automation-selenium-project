package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage{
    public WishListPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "td.product")
    public WebElement productCell;
    @FindBy(css = "h1")
    public WebElement wishListHeader;
    @FindBy(className = "update-wishlist-button")
    private WebElement updateWishlistButton;
    @FindBy(name="removefromcart")
    private WebElement removeFromWishlistCheckButton;
    @FindBy(css="div.wishlist-content")
    public WebElement EmptyCartLabel;
    public void removeProductFromwishList()
    {

        clickOnButton(removeFromWishlistCheckButton);
        clickOnButton(updateWishlistButton);
    }


}
