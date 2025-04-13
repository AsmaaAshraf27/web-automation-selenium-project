package testcasesGoodScenarios;

import data.ExcelReader;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.SearchProductPage;
import pages.ShoppingCartPage;

import java.io.IOException;
import java.util.List;

public class AddProductToShoppingCartTest extends BaseTest {
    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;
    private ShoppingCartPage shoppingCartPage;

    @DataProvider(name = "shoppingCartData")
    public Object[][] provideShoppingCartData() throws IOException {
        List<String[]> data = ExcelReader.getAddProductToShoppingCartData();
        Object[][] testData = new Object[data.size()][2];

        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            testData[i][0] = row[0];
            testData[i][1] = row[1];

        }

        return testData;

    }

    @Test(dataProvider = "shoppingCartData")
    public void addProductToCart(String productName, String quantity) {
        searchProductPage = new SearchProductPage(getDriver());
        searchProductPage.productSearch(productName);
        searchProductPage.openProductDetailsPage();
        productDetailsPage = new ProductDetailsPage(getDriver());
        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
        //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
        shoppingCartPage = new ShoppingCartPage(getDriver());
        productDetailsPage.addProductToShoppingCart();
        productDetailsPage.goToShoppingCartPage();
        Assert.assertTrue(shoppingCartPage.productName.getText().equalsIgnoreCase(productName));
        shoppingCartPage.updateProductQuantityFromShoppingCartPage(quantity);
        //Assert.assertTrue(shoppingCartPage.itemQuantityField.getText().equalsIgnoreCase("3"));
        System.out.println(shoppingCartPage.itemQuantityField.getText());
        shoppingCartPage.removeProductFromCart();
        Assert.assertTrue(shoppingCartPage.shoppingCartIsEmptyMessage.getText().contains("Your Shopping Cart is empty!"));
    }
}
//Before Excel Reader
//@Test(priority = 1)
//public void searchForProductSuccessfully()
//{
//    searchProductPage=new SearchProductPage(driver);
//    searchProductPage.productSearch(productName);
//    searchProductPage.openProductDetailsPage();
//    productDetailsPage=new ProductDetailsPage(driver);
//    Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
//    //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
//}
//    @Test(priority = 2)
//    public void addProductToCart()
//    {
//        shoppingCartPage=new ShoppingCartPage(driver);
//        productDetailsPage.addProductToShoppingCart();
//        productDetailsPage.goToShoppingCartPage();
//        Assert.assertTrue(shoppingCartPage.productName.getText().equalsIgnoreCase(productName));
//
//    }
//    @Test(priority = 3)
//    public void updateItemQuantity()
//    {
//
//        shoppingCartPage.updateProductQuantityFromShoppingCartPage(quantity);
//        //Assert.assertTrue(shoppingCartPage.itemQuantityField.getText().equalsIgnoreCase("3"));
//        System.out.println(shoppingCartPage.itemQuantityField.getText());
//    }
//    @Test(priority = 4)
//    public void removeItemFromTheShoppingCart()
//    {
//        shoppingCartPage.removeProductFromCart();
//        Assert.assertTrue(shoppingCartPage.shoppingCartIsEmptyMessage.getText().contains("Your Shopping Cart is empty!"));
//    }
//
//
//}
