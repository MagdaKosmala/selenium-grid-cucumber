package las3007.assignment.pages.watchlist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class WatchlistPage {
    protected WebDriver driver;

    private By headerBy = By.tagName("h1");
    private By toggleDeleteBy = By.xpath("//*[@id=\"page-1\"]/div/div/div[1]/div");
    private By watchlistMenuItemBy = By.xpath("//*[@id=\"imdbHeader\"]/div[2]/div[4]/a");
    private By watchlistHeadersBy = By.tagName("h3");
    private By listOrderBy = By.id("lister-sort-by-options");
    private By plotBy = By.cssSelector("p.plot");
    private By viewModeBy = By.cssSelector("span.lister-widget-sprite.lister-mode.grid");
    private By watchlistCountBy = By.cssSelector("span.faceter-facet-count");
    private By allWatchlistBy = By.cssSelector("label.faceter-facet");
    private By refineBtnBy = By.cssSelector("button.lister-controls-expand");

    public WatchlistPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        return driver.findElement(headerBy).getText();
    }

    public void deleteToggle() {
        driver.findElement(toggleDeleteBy).click();
    }

    public void openWatchlist() {
        driver.findElement(watchlistMenuItemBy).click();
    }

    public boolean isMoveInWatchlist(String title) {
        List<WebElement> watchlistHeaders = driver.findElements(watchlistHeadersBy);
        return watchlistHeaders
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()).contains(title);
    }

    public void changeListOrder() {
        WebElement listOrder = driver.findElement(listOrderBy);
        listOrder.click();
        Select listOrderSe = new Select(listOrder);
        listOrderSe.selectByValue("DATE_ADDED");
    }

    public boolean isGridView() {
        List<WebElement> plot = driver.findElements(plotBy);
        return plot.isEmpty();
    }

    public void changeViewMode() {
        driver.findElement(viewModeBy).click();
    }

    public int getWatchlistMoviesCount() {
        List<WebElement> watchlistCount = driver.findElements(watchlistCountBy);
        return Integer.parseInt(watchlistCount.get(0).getText());
    }

    public void refineWatchlist() {
        driver.findElement(refineBtnBy).click();
    }

    public Integer getWatchlistSeriesCount() {
        List<WebElement> watchlistCount = driver.findElements(watchlistCountBy);
        return Integer.parseInt(watchlistCount.get(1).getText());
    }
}
