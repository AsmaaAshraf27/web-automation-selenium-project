package testcasesGoodScenarios;

import data.ExcelReader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CustomerPage;
import pages.HomePage;
import pages.RegisterPage;

import java.io.IOException;
import java.util.List;

public class ChangePasswordTest extends BaseTest {
    private HomePage homePage;
    private RegisterPage registerPage;
    private CustomerPage customerPage;
//    private String firstname;
//    private String lastname;
//    private String email;
//    private String oldPassword;
//    private String newPassword;
//    @BeforeClass
//    public void setupTestData() throws IOException {
//        List<String[]> data = ExcelReader.getChangePasswordData();
//        // نأخذ أول صف فقط من البيانات
//        String[] row = data.get(0);
//        firstname = row[0];  // الاسم الأول
//        lastname = row[1];   // الاسم الأخير
//        email = row[2];      // البريد الإلكتروني
//        oldPassword = row[3];// كلمة المرور القديمة
//        newPassword = row[4];// كلمة المرور الجديدة
//    }
@DataProvider(name = "ChangePasswordData")
public Object[][] provideChangePasswordData() throws IOException {
    List<String[]> data = ExcelReader.getReviewData();
    Object[][] testData = new Object[data.size()][5];

    for (int i = 0; i < data.size(); i++) {
        String[] row = data.get(i);
        testData[i][0] = row[0];
        testData[i][1] = row[1];
        testData[i][2] = row[2];
        testData[i][3] = row[3];
        testData[i][4] = row[4];


    }

    return testData;

}

    @Test(dataProvider = "ChangePasswordData")
    public void userChangePasswordSuccessfully(String firstname,String lastname,String email,String oldPassword,String newPassword)
    {
        homePage=new HomePage(driver);
        homePage.openRegisterPage();
        registerPage=new RegisterPage(driver);

        registerPage.userRegistration(firstname,lastname,email,oldPassword);
        Assert.assertTrue(registerPage.successRegisterResult.getText().contains("Your registration completed"));
        customerPage=new CustomerPage(driver);
        homePage.openMyAccountPage();
        customerPage.openChangePasswordPage();
        customerPage.changePassword(oldPassword,newPassword);
        Assert.assertTrue(customerPage.changePasswordResultMessage.getText().contains("Password was changed"));
        registerPage.userLogout();
    }
    //Before Excel Reader
//    @Test(priority = 1,alwaysRun = true)
//    public void userCanRegisterSuccessfully() {
//        homePage = new HomePage(driver);
//        homePage.openRegisterPage();
//        registerPage = new RegisterPage(driver);
//
//        registerPage.userRegistration(firstname, lastname, email, oldPassword);
//        Assert.assertTrue(registerPage.successRegisterResult.getText().contains("Your registration completed"));
//        @Test(dependsOnMethods = {"userCanRegisterSuccessfully"})
//    }
//    public void registeredUserCanChangePassword()
//    {
//        customerPage=new CustomerPage(driver);
//        homePage.openMyAccountPage();
//        customerPage.openChangePasswordPage();
//        customerPage.changePassword(oldPassword,newPassword);
//        Assert.assertTrue(customerPage.changePasswordResultMessage.getText().contains("Password was changed"));
//
//    }
//    @Test(dependsOnMethods = {"registeredUserCanChangePassword"})
//    public void registeredUserCanLogoutSuccessfully()
//    {
//        registerPage.userLogout();
//    }

}
