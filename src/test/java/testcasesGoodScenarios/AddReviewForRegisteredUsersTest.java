package testcasesGoodScenarios;

import data.ExcelReader;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.List;

public class AddReviewForRegisteredUsersTest extends BaseTest {
    private HomePage homePage;
    private RegisterPage registerPage;
    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;
    AddReviewForRegisteredUsersPage addReviewForRegisteredUsersPage;
    @DataProvider(name = "addReviewData")
    public Object[][] provideAddReviewData() throws IOException {
        List<String[]> data = ExcelReader.getReviewData();
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
    @Test(priority = 1,dataProvider = "addReviewData")
    public void userCanAddReviewSuccessfully(String firstname, String lastname,String email,  String password,
                                            String productName, String reviewTitle,String reviewDescription )
    {
        homePage=new HomePage(driver);
        homePage.openRegisterPage();
        registerPage=new RegisterPage(driver);

        registerPage.userRegistration(firstname,lastname,email,password);
        Assert.assertTrue(registerPage.successRegisterResult.getText().contains("Your registration completed"));
        searchProductPage=new SearchProductPage(driver);
        searchProductPage.productSearch(productName);
        searchProductPage.openProductDetailsPage();
        productDetailsPage=new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
        //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
        productDetailsPage.goToReviewPage();
        addReviewForRegisteredUsersPage=new AddReviewForRegisteredUsersPage(driver);
        addReviewForRegisteredUsersPage.registeredUserCanAddReview(reviewTitle,reviewDescription);
        Assert.assertTrue(addReviewForRegisteredUsersPage.reviewSuccessMessage.getText()
                .contains("Product review is successfully added."));
        registerPage.userLogout();

    }

//Before Excel Reader
//    @Test(priority = 1)
//    public void userCanRegisterSuccessfully(String firstname, String lastname,String email,  String password,
//                                            String productName, String reviewTitle,String reviewDescription )
//    {
//        homePage=new HomePage(driver);
//        homePage.openRegisterPage();
//        registerPage=new RegisterPage(driver);
//
//        registerPage.userRegistration(firstname,lastname,email,password);
//        Assert.assertTrue(registerPage.successRegisterResult.getText().contains("Your registration completed"));
//    }
//    @Test(priority = 2)
//    public void searchForProductSuccessfully(String firstname, String lastname,String email,  String password,
//                                             String productName, String reviewTitle,String reviewDescription)
//    {
//        searchProductPage=new SearchProductPage(driver);
//        searchProductPage.productSearch(productName);
//        searchProductPage.openProductDetailsPage();
//        productDetailsPage=new ProductDetailsPage(driver);
//        Assert.assertTrue(productDetailsPage.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
//        //Assert.assertEquals(productPage.productNameBreadCrumb.getText(),productName);--->we can use this also
//    }
//    @Test(priority = 3)
//    public void addReviewSuccessfully(String firstname, String lastname,String email,  String password,
//                                      String productName, String reviewTitle,String reviewDescription)
//    {
//        productDetailsPage.goToReviewPage();
//        addReviewForRegisteredUsersPage=new AddReviewForRegisteredUsersPage(driver);
//        addReviewForRegisteredUsersPage.registeredUserCanAddReview(reviewTitle,reviewDescription);
//        Assert.assertTrue(addReviewForRegisteredUsersPage.reviewSuccessMessage.getText().contains("Product review is successfully added."));
//    }
//    @Test(priority = 4)
//    public void registeredUserCanLogoutSuccessfully()
//    {
//        registerPage.userLogout();
//    }

}
