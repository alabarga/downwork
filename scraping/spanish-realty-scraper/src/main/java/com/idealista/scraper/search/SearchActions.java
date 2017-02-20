package com.idealista.scraper.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class SearchActions
{
    private WebDriver driver;

    public SearchActions(WebDriver driver)
    {
        this.driver = driver;
    }

    public List<WebElement> findElementsByXpath(String xpath)
    {
        return driver.findElements(By.xpath(xpath));
    }
    
    public List<WebElement> findElementsByXpath(List<WebElement> rootElement, String xpath)
    {
        if (!rootElement.isEmpty())
        {
            return rootElement.get(0).findElements(By.xpath("." + xpath));
        }
        return Collections.emptyList();
    }

    public List<WebElement> findElementsByTagName(List<WebElement> rootElement, String tagName)
    {
        if (!rootElement.isEmpty())
        {
            return rootElement.get(0).findElements(By.tagName(tagName));
        }
        return Collections.emptyList();
    }

    public List<WebElement> findElementsById(List<WebElement> rootElement, String id)
    {
        if (!rootElement.isEmpty())
        {
            return rootElement.get(0).findElements(By.id(id));
        }
        return Collections.emptyList();
    }

    public String getElementText(List<WebElement> rootElement)
    {
        if (!rootElement.isEmpty())
        {
            return rootElement.get(0).getText();
        }
        return null;
    }
}