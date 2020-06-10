package las3007.assignment.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPage {
    protected WebDriver driver;

    private By emailFldBy = By.id("ap_email");
    private By passwordFldBy = By.id("ap_password");
    private By signInBtnBy = By.id("signInSubmit");
    private By errorMessageBy = By.cssSelector("span.a-list-item");
    private By errorMessageLiBy = By.tagName("li");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCredentials(String email, String password) {
        //Thread.sleep is used to avoid re-captcha
        try {
            driver.findElement(emailFldBy).clear();
            driver.findElement(passwordFldBy).clear();

            Thread.sleep(2000);
            driver.findElement(emailFldBy).sendKeys(email);
            Thread.sleep(2000);
            driver.findElement(passwordFldBy).clear();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void submitLoginForm() {
        driver.findElement(signInBtnBy).click();
    }

    public String loginFailedMessage() {
        return driver.findElement(errorMessageBy).getText();
    }

    public boolean matchMessages(List<String> listToCompare) {
        List<WebElement> errorMessagesLi = driver.findElements(errorMessageLiBy);

        return errorMessagesLi
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()).containsAll(listToCompare);
    }
}
