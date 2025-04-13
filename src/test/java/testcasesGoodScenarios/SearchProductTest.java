package testcasesGoodScenarios;

import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.SearchProductPage;

import java.io.IOException;
import java.util.List;

public class SearchProductTest extends BaseTest {
    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;

    @DataProvider(name = "searchData")
    public Object[][] provideSearchData() throws IOException {
        List<String[]> data = ExcelReader.getSearchData();
        Object[][] testData = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            testData[i][0] = data.get(i)[0]; // Assuming first column is product name
        }

        return testData;
    }

    @Test(dataProvider = "searchData")
    public void searchForProductSuccessfully(String productName) {
        searchProductPage = new SearchProductPage(getDriver());
        productDetailsPage = new ProductDetailsPage(getDriver());

        searchProductPage.productSearch(productName);
        searchProductPage.openProductDetailsPage();

        Assert.assertTrue(
                productDetailsPage.productNameBreadCrumb.getText()
                        .equalsIgnoreCase(productName),
                "Expected product name: " + productName +
                        " but found: " + productDetailsPage.productNameBreadCrumb.getText()
        );
    }
}
