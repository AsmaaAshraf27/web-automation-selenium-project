package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CompareProductsPage extends BasePage{
    public CompareProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Black & White Diamond Heart")
    public WebElement product1Name;
    @FindBy(css = "h1")
    public WebElement compareProductsHeader;
    @FindBy(linkText = "Diamond Pave Earrings")
    public WebElement product2Name;
    @FindBy(className = "clear-list")
    private WebElement clearListLink;
    @FindBy(className = "page-body")
    public WebElement noItemsToCompareMessage;
    @FindBy(className  ="compare-products-table")
    public WebElement compareProductsTable;
    @FindBy(tagName = "tr")
    public List<WebElement> allRows;
    @FindBy(tagName = "td")
    public List<WebElement> allColumns;
    public void removeAllComparedItems()
    {
        clickOnButton(clearListLink);
    }
    public void compareProducts()
    {
        System.out.println(allRows.size());
        for (WebElement row:allRows)
        {
            System.out.println(row.getText()+"\t");
            for (WebElement column:allColumns)
            {
                System.out.println(column.getText()+"\t");
            }
        }

    }



}
