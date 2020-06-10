package las3007.assignment.factory.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePageFactory {

    private static final String URL = "https://www.imdb.com/";

    protected WebDriver driver;

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loadHomePage() {
        driver.get(URL);
    }
}
