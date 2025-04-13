package testcasesGoodScenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class ProductHoverMenuTest extends BaseTest {
    HomePage homePage;

    @Test
    public void goToNotebooksPage() {
        homePage = new HomePage(getDriver());
        homePage.selectNotebooksMenu();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("notebooks"));

    }
}
