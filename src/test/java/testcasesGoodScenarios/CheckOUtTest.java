package testcasesGoodScenarios;

import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.List;

public class CheckOUtTest extends BaseTest {
    private HomePage homePage;
    private RegisterPage registerPage;
    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckOutPage checkOutPage;
    private OrderDetailsPage orderDetailsPage;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;
//
//    @BeforeClass
//    public void setupRegistrationData() throws IOException {
//        List<String[]> registerData = ExcelReader.getRegisterData();
//        firstName = registerData.get(0)[0];
//        lastName = registerData.get(0)[1];
//        email = registerData.get(0)[2];
//        password = registerData.get(0)[3];
//    }

    @DataProvider(name = "checkOutData")
    public Object[][] provideCheckOutData() throws IOException {
        List<String[]> data = ExcelReader.getCheckoutData();
        Object[][] testData = new Object[data.size()][10];

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
            testData[i][9] = row[9];

        }

        return testData;

    }


    @Test(dataProvider = "checkOutData")
    public void userCanCheckOutSuccessfully(String firstName, String lastName, String email,String country
            ,String city,String address,String postalCode,String phoneNumber,String productName,String password) throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.openRegisterPage();
        registerPage = new RegisterPage(driver);
        registerPage.userRegistration(firstName, lastName, email, password);
        Assert.assertTrue(registerPage.successRegisterResult.getText()
                .contains("Your registration completed"));
        searchProductPage = new SearchProductPage(driver);
        searchProductPage.productSearch(productName);
        searchProductPage.openProductDetailsPage();
        productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText()
                .equalsIgnoreCase(productName));
        shoppingCartPage = new ShoppingCartPage(driver);
        productDetailsPage.addProductToShoppingCart();
        productDetailsPage.goToShoppingCartPage();
        Assert.assertTrue(shoppingCartPage.productName.getText()
                .equalsIgnoreCase(productName));
        shoppingCartPage.openCheckOutPageForRegisteredUsers();
        checkOutPage = new CheckOutPage(driver);
        checkOutPage.registeredUsersCanCheckOut(city, address, postalCode, phoneNumber, country);
        checkOutPage.orderConfirmation();
        Assert.assertTrue(checkOutPage.successMessage.getText()
                .contains("Your order has been successfully processed!"));
        checkOutPage.orderDetails();
        orderDetailsPage = new OrderDetailsPage(driver);
        orderDetailsPage.orderDownloading();
        Thread.sleep(5000);
        orderDetailsPage.orderPrinting();
        registerPage.userLogout();
    }
//    @Test(priority = 1)
//    public void userCanCheckOutSuccessfully() {
//        homePage = new HomePage(driver);
//        homePage.openRegisterPage();
//        registerPage = new RegisterPage(driver);
//        registerPage.userRegistration(firstName, lastName, email, password);
//        Assert.assertTrue(registerPage.successRegisterResult.getText()
//                .contains("Your registration completed"));}

//    @Test(priority = 2)
//    public void searchForProductSuccessfully(String fName, String lName, String email,String country
//            ,String city,String address,String postalCode,String phoneNumber,String productName,String password) {
//        searchProductPage = new SearchProductPage(driver);
//        searchProductPage.productSearch(productName);
//        searchProductPage.openProductDetailsPage();
//        productDetailsPage = new ProductDetailsPage(driver);
//        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText()
//                .equalsIgnoreCase(productName));
//    }
//
//    @Test(priority = 3)
//    public void addProductToCart(String fName, String lName, String email,String country
//            ,String city,String address,String postalCode,String phoneNumber,String productName,String password) {
//        shoppingCartPage = new ShoppingCartPage(driver);
//        productDetailsPage.addProductToShoppingCart();
//        productDetailsPage.goToShoppingCartPage();
//        Assert.assertTrue(shoppingCartPage.productName.getText()
//                .equalsIgnoreCase(productName));
//    }
//
//    @Test(priority = 4)
//    public void checkoutTheProduct(String fName, String lName, String email,String country
//            ,String city,String address,String postalCode,String phoneNumber,String productName,String password) throws InterruptedException {
//        shoppingCartPage.openCheckOutPageForRegisteredUsers();
//        checkOutPage = new CheckOutPage(driver);
//        checkOutPage.registeredUsersCanCheckOut(city, address, postalCode, phoneNumber, country);
//        checkOutPage.orderConfirmation();
//        Assert.assertTrue(checkOutPage.successMessage.getText()
//                .contains("Your order has been successfully processed!"));
//        checkOutPage.orderDetails();
//        orderDetailsPage = new OrderDetailsPage(driver);
//        orderDetailsPage.orderDownloading();
//        Thread.sleep(5000);
//        orderDetailsPage.orderPrinting();
//    }
//
//    @Test(priority = 5)
//    public void registeredUserCanLogoutSuccessfully() {
//        registerPage.userLogout();
//    }
}
