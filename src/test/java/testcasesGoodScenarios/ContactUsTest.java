package testcasesGoodScenarios;

import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

import java.io.IOException;
import java.util.List;

public class ContactUsTest extends BaseTest {
    private HomePage homePage;
    private ContactUsPage contactUsPage;

    @DataProvider(name = "contactUsData")
    public Object[][] provideContactUsData() throws IOException {
        List<String[]> data = ExcelReader.getContactUsData();
        Object[][] testData = new Object[data.size()][3];

        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            testData[i][0] = row[0]; // Name
            testData[i][1] = row[1]; // Email
            testData[i][2] = row[2]; // Message
        }

        return testData;

    }

    @Test(dataProvider = "contactUsData")
    public void userCanContactUsSuccessfully(String name, String email, String message) {
        homePage = new HomePage(driver);
        homePage.openContactUsPage();

        contactUsPage = new ContactUsPage(driver);
        contactUsPage.contactUs(name, email, message);
        Assert.assertTrue(contactUsPage.successMessage.getText()
                .contains("Your enquiry has been successfully sent to the store owner."));
    }
}
