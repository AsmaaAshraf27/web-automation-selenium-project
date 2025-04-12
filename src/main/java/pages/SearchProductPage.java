package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchProductPage extends BasePage{
    public SearchProductPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "small-searchterms")
    private WebElement searchField;
    @FindBy(xpath = "//ul[@id='ui-id-1']/li/a")
    public List<WebElement> productList;


    @FindBy(className = "search-box-button")
    private WebElement searchButton;
    @FindBy(css = "h2.product-title")
    public WebElement productLink;
    public void productSearch(String productName)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(searchField));
        wait.until(ExpectedConditions.elementToBeClickable(searchField));

        sendText(searchField,productName);
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        clickOnButton(searchButton);

    }
    public void openProductDetailsPage()
    {
        clickOnButton(productLink);
    }
    public void searchUsingAutoSuggest(String searchText)
    {
        sendText(searchField,searchText);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        WebElement firstItem = wait.until(ExpectedConditions.elementToBeClickable(productList.get(0)));
        firstItem.click();




    }


}
