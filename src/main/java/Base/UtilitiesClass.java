package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UtilitiesClass extends TestBase{

    public static ExtentReports extent;

    public UtilitiesClass() {
    }

    public String readDataFromJsonFile(String filePath, String key) {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;

        try {
            reader = new FileReader(filePath);
        } catch (FileNotFoundException var10) {
            var10.printStackTrace();
        }

        Object obj = null;

        try {
            obj = jsonParser.parse(reader);
        } catch (IOException var8) {
            var8.printStackTrace();
        } catch (ParseException var9) {
            var9.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject)obj;
        String value = (String)jsonObject.get(key);
        return value;
    }

    public static ExtentReports getExtentReports() {
        String extentReportsFilePath = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportsFilePath);
        reporter.config().setReportName("Automation framework");
        reporter.config().setDocumentTitle("Automation framework test results");
        extent = new ExtentReports();
        extent.attachReporter(new ExtentObserver[]{reporter});
        extent.setSystemInfo("Deepak Kumar", "Testing Lead");
        return extent;
    }
}
