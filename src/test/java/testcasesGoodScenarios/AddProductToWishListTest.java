package testcasesGoodScenarios;

import data.ExcelReader;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.SearchProductPage;
import pages.WishListPage;

import java.io.IOException;
import java.util.List;

public class AddProductToWishListTest extends BaseTest {
    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;
    private WishListPage wishListPage;

    @DataProvider(name = "wishingListData")
    public Object[][] provideWishingListData() throws IOException {
        List<String[]> data = ExcelReader.getAddProductToWishingListData();
        Object[][] testData = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            testData[i][0] = row[0];


        }

        return testData;

    }

    @Test(priority = 1, dataProvider = "wishingListData")
    public void addProductToTheWishList(String productName) {
        searchProductPage = new SearchProductPage(driver);
        searchProductPage.productSearch(productName);
        searchProductPage.openProductDetailsPage();
        productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
        //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
        productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.addProductToWishlist();
        productDetailsPage.goToWishListPage();//---> كانت بتفيل ال test case معرفش ليه
//     driver.navigate().to("https://demowebshop.tricentis.com/wishlist");
        wishListPage = new WishListPage(driver);
        Assert.assertTrue(wishListPage.wishListHeader.isDisplayed());
        Assert.assertTrue(wishListPage.productCell.getText().contains(productName));
        wishListPage = new WishListPage(driver);
        wishListPage.removeProductFromwishList();
        Assert.assertTrue(wishListPage.EmptyCartLabel.getText().contains("The wishlist is empty!"));
    }
    //Before Excel Reader
//    @Test(priority = 1)
//    public void searchForProductSuccessfully(String productName)
//    {
//        searchProductPage=new SearchProductPage(driver);
//        searchProductPage.productSearch(productName);
//        searchProductPage.openProductDetailsPage();
//        productDetailsPage=new ProductDetailsPage(driver);
//        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
//        //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
//}
//    @Test(priority = 2,dataProvider = "wishingListData")
//    public void addProductToWishList(String productName)
//    {
//        productDetailsPage=new ProductDetailsPage(driver);
//        productDetailsPage.addProductToWishlist();
//       productDetailsPage.goToWishListPage();//---> كانت بتفيل ال test case معرفش ليه
//     driver.navigate().to("https://demowebshop.tricentis.com/wishlist");
//        wishListPage=new WishListPage(driver);
//       Assert.assertTrue(wishListPage.wishListHeader.isDisplayed());
//       Assert.assertTrue(wishListPage.productCell.getText().contains(productName));
//
//    }
//    @Test(priority = 3)
//    public void removeProductFromWishList()
//    {
//
//
//
//
//        wishListPage=new WishListPage(driver);
//        wishListPage.removeProductFromwishList();
//        Assert.assertTrue(wishListPage.EmptyCartLabel.getText().contains("The wishlist is empty!"));
//    }
}
