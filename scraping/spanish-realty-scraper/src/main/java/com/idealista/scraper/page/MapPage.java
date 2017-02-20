package com.idealista.scraper.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MapPage
{
    private WebDriver driver;

    public MapPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickShowAll()
    {
        try
        {
            WebElement showAllLink = driver.findElement(By.id("showAllLink"));
            if (showAllLink != null)
            {
                showAllLink.click();
            }
        }
        catch (NoSuchElementException e)
        {
            // nothing to do, it happens
        }
    }

    public void clickIdealistaLink()
    {
        driver.findElement(By.xpath("//div[@class='breadcrumb-geo wrapper clearfix']//a[text()='idealista']")).click();
    }
}
