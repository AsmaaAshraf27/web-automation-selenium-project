package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderDetailsPage extends BasePage{
    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(className = "pdf-order-button")
    private WebElement pdfInvoiceLink;
    @FindBy(className = "print-order-button")
    private WebElement printInvoiceLink;

    public void orderDownloading()
    {
        clickOnButton(pdfInvoiceLink);
    }
    public void orderPrinting()
    {
        clickOnButton(printInvoiceLink);
    }
}
