//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package TestNGListener;

import Base.TestBase;
import Base.UtilitiesClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners extends TestBase implements ITestListener {
    ExtentTest test;
    ExtentReports extent = UtilitiesClass.getExtentReports();
    ThreadLocal<ExtentTest> tExtent = new ThreadLocal();

    public TestListeners() {
    }

    public String takeScreenshot(String testcaseName, WebDriver driver) throws IOException {
        String screenshotPath = System.getProperty("user.dir") + "/reports/" + testcaseName + ".png";
        TakesScreenshot srcFile = (TakesScreenshot)driver;
        File source = (File)srcFile.getScreenshotAs(OutputType.FILE);
        File DestFileLocation = new File(screenshotPath);
        FileUtils.copyFile(source, DestFileLocation);
        return screenshotPath;
    }

    public void onTestStart(ITestResult iTestResult) {
        this.test = this.extent.createTest(iTestResult.getMethod().getMethodName());
        this.tExtent.set(this.test);
    }

    public void onTestSuccess(ITestResult iTestResult) {
        ((ExtentTest)this.tExtent.get()).log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        WebDriver driver = TestBase.getDriver();
        ((ExtentTest)this.tExtent.get()).fail(iTestResult.getThrowable());
        String screenshotPath = null;

        try {
            screenshotPath = this.takeScreenshot(iTestResult.getMethod().getMethodName(), driver);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        ((ExtentTest)this.tExtent.get()).addScreenCaptureFromPath(screenshotPath, iTestResult.getMethod().getMethodName());
    }

    public void onTestSkipped(ITestResult iTestResult) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {
        this.extent.flush();
    }
}
