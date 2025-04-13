package testcasesGoodScenarios;

import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CompareProductsPage;
import pages.ProductDetailsPage;
import pages.SearchProductPage;

import java.io.IOException;
import java.util.List;

public class CompareProductsTest extends BaseTest {
    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;
    private CompareProductsPage compareProductsPage;

    @DataProvider(name = "compareProductsData")
    public Object[][] provideCompareProductsData() throws IOException {
        List<String[]> data = ExcelReader.getCompareProductData();
        Object[][] testData = new Object[data.size()][2];

        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            testData[i][0] = row[0];
            testData[i][1] = row[1];

        }

        return testData;
    }

    @Test(priority = 1, dataProvider = "compareProductsData")
    public void compareProducts(String product1Name, String product2Name) {
        searchProductPage = new SearchProductPage(getDriver());
        compareProductsPage = new CompareProductsPage(getDriver());
        searchProductPage.productSearch(product1Name);
        searchProductPage.openProductDetailsPage();
        productDetailsPage = new ProductDetailsPage(getDriver());

        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(product1Name));
        productDetailsPage.addProductToCompareList();
        Assert.assertTrue(compareProductsPage.product1Name.getText().contains(product1Name));
        searchProductPage.productSearch(product2Name);
        searchProductPage.openProductDetailsPage();
        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(product2Name));
        productDetailsPage.addProductToCompareList();
        Assert.assertTrue(compareProductsPage.product2Name.getText().contains(product2Name));
        compareProductsPage.compareProducts();
        compareProductsPage.removeAllComparedItems();
        Assert.assertTrue(compareProductsPage.noItemsToCompareMessage.getText().contains("You have no items to compare."));

    }}
//@Test(priority = 2)
//    public void clearCompareProductsList()
//{
//    compareProductsPage.removeAllComparedItems();
//    Assert.assertTrue(compareProductsPage.noItemsToCompareMessage.getText().contains("You have no items to compare."));
//
//}



//    @Test(priority = 1)
//    public void searchForProductSuccessfully()
//    {
//        searchProductPage=new SearchProductPage(driver);
//        searchProductPage.productSearch(product1Name);
//        searchProductPage.openProductDetailsPage();
//        productDetailsPage=new ProductDetailsPage(driver);
//        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(product1Name));

//    }


//    @Test(priority = 2)
//    public void addProductToCompare()
//    {
//        compareProductsPage=new CompareProductsPage(driver);
//        productDetailsPage.addProductToCompareList();
//        Assert.assertTrue(compareProductsPage.product1Name.getText().contains(product1Name));
//    }
//    @Test(priority = 3)
//    public void searchForProductSuccessfully()
//    {
//        searchProductPage=new SearchProductPage(driver);
//        searchProductPage.productSearch(product2Name);
//        searchProductPage.openProductDetailsPage();
//        productDetailsPage=new ProductDetailsPage(driver);
//        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(product2Name));
//        //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
//    }
//    @Test(priority = 4)
//    public void addProductToCompare()
//    {
//        compareProductsPage=new CompareProductsPage(driver);
//        productDetailsPage.addProductToCompareList();
//        Assert.assertTrue(compareProductsPage.product2Name.getText().contains(product2Name));
//    }
