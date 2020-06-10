package las3007.assignment.factory.watchlist;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class WatchlistPageFactory {
    protected WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(css = "span.element-selected-total")
    private WebElement totalSelectedNum;

    @FindBy(xpath = "//*[@id=\"page-1\"]/div/div/div[1]/div")
    private WebElement toggleDelete;

    @FindBy(xpath= "//*[@id=\"imdbHeader\"]/div[2]/div[4]/a")
    private WebElement watchlistMenuItem;

    @FindBy(tagName = "h3")
    private List<WebElement> watchlistHeaders;

    @FindBy(id = "lister-sort-by-options")
    private WebElement listOrder;

    @FindBy(css = "p.plot")
    private List<WebElement> plot;

    @FindBy(css = "span.lister-widget-sprite.lister-mode.grid")
    private WebElement viewMode;

    @FindBy(css = "span.faceter-facet-count")
    private List<WebElement> watchlistCount;

    @FindBy(css = "label.faceter-facet")
    private List<WebElement> allWatchlist;

    @FindBy(css = "button.lister-controls-expand")
    private WebElement refineBtn;

    public WatchlistPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeaderText() {
        return header.getText();
    }

    public void deleteToggle() {
        toggleDelete.click();
    }

    public void openWatchlist() {
        watchlistMenuItem.click();
    }

    public boolean isMoveInWatchlist(String title) {
        return watchlistHeaders
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()).contains(title);
    }

    public void changeListOrder() {
        listOrder.click();
        Select listOrderSe = new Select(listOrder);
        listOrderSe.selectByValue("DATE_ADDED");
    }

    public boolean isGridView() {
        if(plot == null) return true;
        return plot.isEmpty();
    }

    public void changeViewMode() {
        viewMode.click();
    }

    public int getWatchlistMoviesCount() {
        return Integer.parseInt(watchlistCount.get(0).getText());
    }

    public void refineWatchlist() {
        refineBtn.click();
    }

    public Integer getWatchlistSeriesCount() {
        return Integer.parseInt(watchlistCount.get(1).getText());
    }
}
