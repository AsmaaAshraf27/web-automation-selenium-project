package testcasesGoodScenarios;



import com.github.javafaker.Faker;
import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.List;

public class EmailAFriendForRegisteredUsersTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    SearchProductPage searchProductPage;
    ProductDetailsPage productDetailsPage;
    EmailAFriendPage emailAFriendPage;
    @DataProvider(name = "EmailFriendForRegisteredData")
    public Object[][] provideEmailFriendData() throws IOException {
        List<String[]> data = ExcelReader.getEmailFriendForRegisteredData();
        Object[][] testData = new Object[data.size()][7];

        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            testData[i][0] = row[0];
            testData[i][1] = row[1];
            testData[i][2] = row[2];
            testData[i][3] = row[3];
            testData[i][4] = row[4];
            testData[i][5] = row[5];
            testData[i][6] = row[6];
        }

        return testData;

    }
    @Test(dataProvider = "EmailFriendForRegisteredData")
    public void userCanRegisterSuccessfully(String firstName,String lastName,String email,
                                            String password,String friendEmail, String personalMessage,String productName) throws InterruptedException {
        homePage=new HomePage(driver);
        homePage.openRegisterPage();
        registerPage=new RegisterPage(driver);

        registerPage.userRegistration(firstName,lastName,email,password);
        Assert.assertTrue(registerPage.successRegisterResult.getText().contains("Your registration completed"));
        searchProductPage=new SearchProductPage(driver);
        searchProductPage.productSearch(productName);
        searchProductPage.openProductDetailsPage();
        productDetailsPage=new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
//        //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
        productDetailsPage=new ProductDetailsPage(driver);
        productDetailsPage.clickOnEmailAFriendButton();
        emailAFriendPage=new EmailAFriendPage(driver);
        emailAFriendPage.registeredUserCanSendAnEmailToHisFriend(friendEmail,personalMessage);
        Assert.assertTrue(emailAFriendPage.successMessage.getText().contains("Your message has been sent."));
        registerPage.userLogout();
        Thread.sleep(2000);
    }
    //1-user register
//    @Test(priority = 1)
//    public void userCanRegisterSuccessfully()
//    {
//        homePage=new HomePage(driver);
//        homePage.openRegisterPage();
//        registerPage=new RegisterPage(driver);
//
//        registerPage.userRegistration(firstname,lastname,email,password);
//        Assert.assertTrue(registerPage.successRegisterResult.getText().contains("Your registration completed"));
//    }
//    //2-user search for a product
//    @Test(priority = 2)
//    public void searchForProductSuccessfully()
//    {
//        searchProductPage=new SearchProductPage(driver);
//        searchProductPage.productSearch(productName);
//        searchProductPage.openProductDetailsPage();
//        productDetailsPage=new ProductDetailsPage(driver);
//        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
//        //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
//    }
//    //3-user email a friend
//    @Test(priority = 3)
//    public void emailAFriendToSendHimTheProductSuccessfully()
//    {
//
//        productDetailsPage=new ProductDetailsPage(driver);
//        productDetailsPage.clickOnEmailAFriendButton();
//        emailAFriendPage=new EmailAFriendPage(driver);
//        emailAFriendPage.registeredUserCanSendAnEmailToHisFriend("ew@ex.com","This is for test");
//        Assert.assertTrue(emailAFriendPage.successMessage.getText().contains("Your message has been sent."));
//    }
//    //user log out
//    @Test(priority = 4)
//    public void registeredUserCanLogoutSuccessfully() throws InterruptedException {
//        registerPage.userLogout();
//        Thread.sleep(5000);
//    }

}
