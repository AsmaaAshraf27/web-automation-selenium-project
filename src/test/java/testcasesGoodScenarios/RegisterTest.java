package testcasesGoodScenarios;

import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

import java.io.IOException;
import java.util.List;

public class RegisterTest extends BaseTest {
    private HomePage homePage;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private String tempEmail;
    private String tempPassword;

    @DataProvider(name = "registerData")
    public Object[][] provideRegisterData() throws IOException {
        List<String[]> data = ExcelReader.getRegisterData();
        Object[][] testData = new Object[data.size()][4];

        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            testData[i][0] = row[0]; // FirstName
            testData[i][1] = row[1]; // LastName
            testData[i][2] = row[2]; // Email
            testData[i][3] = row[3]; // Password
        }

        return testData;
    }

    @Test(dataProvider = "registerData", priority = 1, alwaysRun = true)
    public void userCanRegisterSuccessfully(String fName, String lName, String email, String password) throws InterruptedException {
        tempEmail = email;
        tempPassword = password;

        homePage = new HomePage(getDriver());
        homePage.openRegisterPage();
        registerPage = new RegisterPage(getDriver());

        registerPage.userRegistration(fName, lName, email, password);
        Assert.assertTrue(registerPage.successRegisterResult.getText()
                .contains("Your registration completed"));
        registerPage.GoBackToTheHomePage();
        registerPage.userLogout();
        homePage.openLoginPage();
        loginPage = new LoginPage(getDriver());
        loginPage.userLogin(tempEmail, tempPassword);
        Assert.assertTrue(registerPage.logOutLink.getText().contains("Log out"));
        Thread.sleep(2000);
        registerPage.userLogout();
    }
}
//Before Excel Reader
//    @Test( priority = 1, alwaysRun = true)
//    public void userCanRegisterSuccessfully(String fName, String lName, String email, String password) {
//        tempEmail = email;
//        tempPassword = password;
//
//        homePage = new HomePage(driver);
//        homePage.openRegisterPage();
//        registerPage = new RegisterPage(driver);
//
//        registerPage.userRegistration(fName, lName, email, password);
//        Assert.assertTrue(registerPage.successRegisterResult.getText()
//                .contains("Your registration completed"));
//}
//
//    @Test(priority = 2)
//    public void registeredUserCanLogoutSuccessfully() {
//        registerPage.userLogout();
//    }
//
//    @Test(priority = 3)
//    public void registeredUserCanLoginSuccessfully() throws InterruptedException {
//        homePage.openLoginPage();
//        loginPage = new LoginPage(driver);
//        loginPage.userLogin(tempEmail, tempPassword);
//        Assert.assertTrue(registerPage.logOutLink.getText().contains("Log out"));
//        Thread.sleep(2000);
//        registerPage.userLogout();
//    }
//}
