package testcasesGoodScenarios;

import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.SearchProductPage;

import java.io.IOException;
import java.util.List;

public class SearchProductWithAutoSuggestTest extends BaseTest {
    private SearchProductPage searchProductPage;
    private ProductDetailsPage productPage;


    @DataProvider(name = "SearchWithAutoCompleteData")
    public Object[][] provideSearchData() throws IOException {
        List<String[]> data = ExcelReader.getSearchWithAutoCompleteData();
        Object[][] testData = new Object[data.size()][2];

        for (int i = 0; i < data.size(); i++) {
            testData[i][0] = data.get(i)[0];
            testData[i][1] = data.get(i)[1];
        }

        return testData;
    }

    @Test(dataProvider = "SearchWithAutoCompleteData")
    public void searchForProductWithAutoComplete(String searchText, String expectedProductName) {
        searchProductPage = new SearchProductPage(getDriver());
        productPage = new ProductDetailsPage(getDriver());
        searchProductPage.searchUsingAutoSuggest(searchText);
        Assert.assertTrue(productPage.productNameBreadCrumb.getText()
                .equalsIgnoreCase(expectedProductName));
    }
}
