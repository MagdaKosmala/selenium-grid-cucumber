package las3007.assignment.pages.search;

import las3007.assignment.pages.watchlist.MovieDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage {
    protected WebDriver driver;

    private By firstSearchResultBy = By.xpath("//*[@id=\"main\"]/div/div[2]/table/tbody/tr[1]/td[2]");
    private By noSearchResultBy = By.cssSelector("div.article");
    private By movieTitlesBy = By.cssSelector("tr.findResult");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstSearchTitle() {
        return driver.findElement(firstSearchResultBy).getText();
    }

    public String getNoResultMessage() {
        return driver.findElement(noSearchResultBy).getText();
    }

    public MovieDetailsPage loadMovieDetailsPage() {
        List<WebElement> movieTitles = driver.findElements(movieTitlesBy);
        movieTitles.get(0).findElements(By.tagName("td")).get(0).click();
        return new MovieDetailsPage(driver);
    }
}
