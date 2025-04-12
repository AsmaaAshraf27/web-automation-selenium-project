package testcasesGoodScenarios;

import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EmailAFriendPage;
import pages.ProductDetailsPage;
import pages.SearchProductPage;

import java.io.IOException;
import java.util.List;

public class EmailAFriendForNotRegisteredUsersTest extends BaseTest {
    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;
    EmailAFriendPage emailAFriendPage;
    @DataProvider(name = "EmailFriendForNonRegisteredData")
    public Object[][] provideData() throws IOException {
        List<String[]> data = ExcelReader.getEmailFriendForNonRegisteredData();
        Object[][] testData = new Object[data.size()][4];

        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            testData[i][0] = row[0];
            testData[i][1] = row[1];
            testData[i][2] = row[2];
            testData[i][3] = row[3];
        }

        return testData;

    }

    @Test(dataProvider = "EmailFriendForNonRegisteredData")
    public void searchForProductSuccessfully(String friendEmail,String userEmail, String personalMessage,String productName)
    {
        searchProductPage=new SearchProductPage(driver);
        searchProductPage.productSearch(productName);
        searchProductPage.openProductDetailsPage();
        productDetailsPage=new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
        //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
                productDetailsPage=new ProductDetailsPage(driver);
        productDetailsPage.clickOnEmailAFriendButton();
        emailAFriendPage=new EmailAFriendPage(driver);
        emailAFriendPage.nonRegisteredUserCanNotSendAnEmailToHisFriend(friendEmail,userEmail,personalMessage);
        Assert.assertTrue(emailAFriendPage.errorMessage.getText().contains("Only registered customers can use email a friend feature"));
    }

//    @Test(priority = 1)
//    public void searchForProductSuccessfully()
//    {
//        searchProductPage=new SearchProductPage(driver);
//        searchProductPage.productSearch(productName);
//        searchProductPage.openProductDetailsPage();
//        productDetailsPage=new ProductDetailsPage(driver);
//        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
//        //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
//    }
//    @Test(priority = 2)
//    public void emailAFriendSuccessfully()
//    {
//
//        productDetailsPage=new ProductDetailsPage(driver);
//        productDetailsPage.clickOnEmailAFriendButton();
//        emailAFriendPage=new EmailAFriendPage(driver);
//        emailAFriendPage.nonRegisteredUserCanNotSendAnEmailToHisFriend("ew@ex.com","exn6789@exa.com","This is for test");
//        Assert.assertTrue(emailAFriendPage.errorMessage.getText().contains("Only registered customers can use email a friend feature"));
//    }
//
 }
