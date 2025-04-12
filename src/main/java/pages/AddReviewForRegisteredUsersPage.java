package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddReviewForRegisteredUsersPage extends BasePage {
    public AddReviewForRegisteredUsersPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "AddProductReview_Title")
    private WebElement reviewTitleField;
    @FindBy(id = "AddProductReview_ReviewText")
    private WebElement reviewTextField ;
    @FindBy(id = "addproductrating_3")
    private WebElement productRatingRadioButton;
    @FindBy(className = "write-product-review-button")
    private WebElement submitReviewButton;
    @FindBy(css="div.result")
    public WebElement reviewSuccessMessage;
    public void registeredUserCanAddReview(String reviewTitle, String reviewText)
    {
        sendText(reviewTitleField,reviewTitle);
        sendText(reviewTextField,reviewText);
        clickOnButton(productRatingRadioButton);
        clickOnButton(submitReviewButton);
    }
}
