//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package TestPages;

import Base.TestBase;
import DataParamPath.DataParams;
import Pages.Login;
import TestNGListener.TestListeners;
import TestNGRetryAnalyzer.RetryFailedTest;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    Login login;
    TestListeners tl;
    DataParams dataParams = new DataParams();

    public LoginTest() {
    }

    @BeforeMethod
    public void setup() throws IOException {
        this.initialization();
        this.login = new Login();
        this.tl = new TestListeners();
    }

    @Test(
            retryAnalyzer = RetryFailedTest.class
    )
    public void login() throws IOException {
        String userEmail = ut.readDataFromJsonFile(this.dataParams.loginJsonPath, "email");
        String password = ut.readDataFromJsonFile(this.dataParams.loginJsonPath, "password");
        this.login.enterEmail(userEmail);
        this.login.enterPassword(password);
        this.login.clickOnLoginButton();
        String dashboardTitle = driver.getTitle();
        this.tl.takeScreenshot("Login", driver);
        System.out.println(dashboardTitle);
        Assert.assertEquals(dashboardTitle, "Cogmento");
    }

    @AfterMethod
    public void cleanup() {
        driver.quit();
    }
}
