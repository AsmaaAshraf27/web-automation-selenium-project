package testcasesGoodScenarios;

import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.List;

public class GustCheckOutTest extends BaseTest {
    private HomePage homePage;
    private RegisterPage registerPage;
    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckOutPage checkOutPage;
    private OrderDetailsPage orderDetailsPage;

    @DataProvider(name = "GuestCheckOut")
    public Object[][] provideGuestCheckOutData() throws IOException {
        List<String[]> data = ExcelReader.getGuestCheckoutData();
        Object[][] testData = new Object[data.size()][9];

        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            testData[i][0] = row[0];
            testData[i][1] = row[1];
            testData[i][2] = row[2];
            testData[i][3] = row[3];
            testData[i][4] = row[4];
            testData[i][5] = row[5];
            testData[i][6] = row[6];
            testData[i][7] = row[7];
            testData[i][8] = row[8];


        }

        return testData;

    }

    @Test(dataProvider = "GuestCheckOut")
    public void guestCheckOut(String guestFName, String guestLName, String guestEmail, String city,
                              String address, String postalCode, String phoneNumber, String productName,
                              String country) throws InterruptedException {
        searchProductPage = new SearchProductPage(getDriver());
        productDetailsPage = new ProductDetailsPage(getDriver());
        searchProductPage.productSearch(productName);
        searchProductPage.openProductDetailsPage();

        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
        shoppingCartPage = new ShoppingCartPage(getDriver());
        productDetailsPage = new ProductDetailsPage(getDriver());
        productDetailsPage.addProductToShoppingCart();
        productDetailsPage.goToShoppingCartPage();
        Assert.assertTrue(shoppingCartPage.productName.getText().equalsIgnoreCase(productName));
        shoppingCartPage = new ShoppingCartPage(getDriver());
        shoppingCartPage.openCheckOutPageForGuestUsers();
        checkOutPage = new CheckOutPage(getDriver());
        checkOutPage.guestUserCanCheckOUt(guestFName, guestLName, guestEmail, city, address, postalCode, phoneNumber, country);
        checkOutPage.orderConfirmation();
        Assert.assertTrue(checkOutPage.successMessage.getText().contains("Your order has been successfully processed!"));
        checkOutPage.orderDetails();
        orderDetailsPage = new OrderDetailsPage(getDriver());
        orderDetailsPage.orderDownloading();
        Thread.sleep(5000); // wait for 5 seconds after clicking download

        orderDetailsPage.orderPrinting();
    }
    //1-register
    //2-search for product
    //3-add to cart
    //4-checkout product
    //5-logout


//    @Test(priority = 1)
//    public void searchForProductSuccessfully() {
//        searchProductPage = new SearchProductPage(driver);
//        productDetailsPage = new ProductDetailsPage(driver);
//        searchProductPage.productSearch(productName);
//        searchProductPage.openProductDetailsPage();
//
//        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
//    }
//    @Test(priority = 2)
//    public void addProductToCart() {
//        shoppingCartPage = new ShoppingCartPage(driver);
//        productDetailsPage = new ProductDetailsPage(driver);
//        productDetailsPage.addProductToShoppingCart();
//        productDetailsPage.goToShoppingCartPage();
//        Assert.assertTrue(shoppingCartPage.productName.getText().equalsIgnoreCase(productName));
//    }
//    @Test(priority = 3)
//    public void checkoutTheProduct() throws InterruptedException {
//        shoppingCartPage = new ShoppingCartPage(driver);
//        shoppingCartPage.openCheckOutPageForGuestUsers();
//        checkOutPage = new CheckOutPage(driver);
//        checkOutPage.guestUserCanCheckOUt(guestFName,guestLName,guestEmail,city, address, postalCode, phoneNumber, country);
//        checkOutPage.orderConfirmation();
//        Assert.assertTrue(checkOutPage.successMessage.getText().contains("Your order has been successfully processed!"));
//        checkOutPage.orderDetails();
//        orderDetailsPage=new OrderDetailsPage(driver);
//        orderDetailsPage.orderDownloading();
//        Thread.sleep(5000); // wait for 5 seconds after clicking download
//
//        orderDetailsPage.orderPrinting();
//    }

}
