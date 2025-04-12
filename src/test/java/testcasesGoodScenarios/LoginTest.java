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

public class LoginTest extends BaseTest {
    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    @DataProvider(name = "loginData")
    public Object[][] provideLoginData() throws IOException {
        List<String[]> data = ExcelReader.getLoginData();
        Object[][] testData = new Object[data.size()][2];

        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            testData[i][0] = row[0]; // Email
            testData[i][1] = row[1]; // Password
        }

        return testData;
    }

    @Test(dataProvider = "loginData")
    public void registeredUserCanLoginSuccessfully(String email, String password) throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.openLoginPage();
        loginPage = new LoginPage(driver);
        loginPage.userLogin(email, password);
        registerPage = new RegisterPage(driver);
        Assert.assertTrue(registerPage.logOutLink.getText().contains("Log out"));
        Thread.sleep(2000);
        registerPage.userLogout();
    }
}
