package testcasesGoodScenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class ProductHoverMenuTest extends BaseTest {
    HomePage homePage;
    @Test
    public void goToNotebooksPage()
    {
        homePage=new HomePage(driver);
        homePage.selectNotebooksMenu();
        Assert.assertTrue(driver.getCurrentUrl().contains("notebooks"));

    }
}
