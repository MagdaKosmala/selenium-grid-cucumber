package las3007.assignment.factory.search;

import las3007.assignment.factory.common.MovieDetailsPageFactory;
import las3007.assignment.pages.watchlist.MovieDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPageFactory {
    protected WebDriver driver;

    @FindBy(xpath = "//*[@id=\"main\"]/div/div[2]/table/tbody/tr[1]/td[2]")
    private WebElement firstSearchResult;

    @FindBy(css = "div.article")
    private WebElement noSearchResult;

    @FindBy(css = "tr.findResult")
    private List<WebElement> movieTitles;

    public SearchPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFirstSearchTitle() {
        return firstSearchResult.getText();
    }

    public String getNoResultMessage() {
        return noSearchResult.getText();
    }

    public MovieDetailsPageFactory loadMovieDetailsPage() {
        movieTitles.get(0).findElements(By.tagName("td")).get(0).click();
        return new MovieDetailsPageFactory(driver);
    }

    public MovieDetailsPage loadMovieDetailsPage2() {
        movieTitles.get(0).findElements(By.tagName("td")).get(0).click();
        return new MovieDetailsPage(driver);
    }
}
