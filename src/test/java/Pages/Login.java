//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Pages;

import Base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends TestBase {
    @FindBy(
            xpath = "//input[@placeholder='Email']"
    )
    WebElement email_input_field;
    @FindBy(
            xpath = "//input[@placeholder='Password']"
    )
    WebElement password_input_field;
    @FindBy(
            xpath = "//div[text()='Login']"
    )
    WebElement login_button;

    public Login() {
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String username) {
        this.email_input_field.sendKeys(new CharSequence[]{username});
    }

    public void enterPassword(String password) {
        this.password_input_field.sendKeys(new CharSequence[]{password});
    }

    public void clickOnLoginButton() {
        this.login_button.click();
    }
}
