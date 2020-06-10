package las3007.assignment.factory.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPageFactory {
    protected WebDriver driver;

    @FindBy(id="ap_email")
    private WebElement emailFld;

    @FindBy(id="ap_password")
    private WebElement passwordFld;

    @FindBy(id="signInSubmit")
    private WebElement signInBtn;

    @FindBy(css="span.a-list-item")
    private WebElement errorMessage;

    @FindBy(tagName = "li")
    private List<WebElement> errorMessagesLi;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCredentials(String email, String password) {
        //Thread.sleep is used to avoid re-captcha
        try {
            emailFld.clear();
            passwordFld.clear();
            Thread.sleep(2000);

            emailFld.sendKeys(email);
            Thread.sleep(2000);
            passwordFld.sendKeys(password);

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void submitLoginForm() {
        signInBtn.click();
    }

    public String loginFailedMessage() {
        return errorMessage.getText();
    }

    public boolean matchMessages(List<String> listToCompare) {
        return errorMessagesLi
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()).containsAll(listToCompare);
    }
}
