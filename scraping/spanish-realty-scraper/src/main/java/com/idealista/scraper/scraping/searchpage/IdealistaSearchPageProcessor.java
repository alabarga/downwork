package com.idealista.scraper.scraping.searchpage;

import com.idealista.scraper.model.Category;
import com.idealista.scraper.util.URLUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IdealistaSearchPageProcessor extends AbstractSearchPageProcessor
{
    private static final Logger LOGGER = LogManager.getLogger(IdealistaSearchPageProcessor.class);

    public IdealistaSearchPageProcessor(Category category)
    {
        super(category);
    }

    @Override
    public Set<Category> call()
    {
        Category category = getCategory();
        URL page = category.getUrl();
        LOGGER.info("Processing search page: {}", page);
        WebDriver driver = getWebDriverProvider().get();
        driver = getNavigateActions().get(page);
        driver = getProxyMonitor().checkForVerificationAndRestartDriver(driver);
        List<WebElement> divContainer = driver.findElements(By.xpath("//div[@class='items-container']"));
        if (!divContainer.isEmpty())
        {
            WebElement container = divContainer.get(0);
            List<WebElement> ads = container.findElements(By.xpath(".//article[not(@class)]"));
            Set<Category> adUrls = new HashSet<>();
            for (WebElement ad : ads)
            {
                List<WebElement> infoLink = ad.findElements(
                        By.xpath(".//div[@class='item-info-container']//a[contains(@class,'item-link')]"));
                if (!infoLink.isEmpty())
                {
                    String attribute = infoLink.get(0).getAttribute("href");
                    adUrls.add(new Category(URLUtils.generateUrl(attribute), category));
                }
            }
            LOGGER.info("Page has been processed successfully: {}", page);
            return adUrls;
        }
        LOGGER.error("Search page is empty.. Smth bad happened, returning empty collection from page: {}", page);
        return Collections.emptySet();
    }
}
