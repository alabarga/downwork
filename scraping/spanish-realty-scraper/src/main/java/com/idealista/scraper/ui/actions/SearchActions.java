package com.idealista.scraper.ui.actions;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class SearchActions
{
    private static final Logger LOGGER = LogManager.getLogger(SearchActions.class);

    private WebDriver driver;

    public List<WebElement> findElementsById(List<WebElement> rootElement, String id)
    {
        if (!rootElement.isEmpty())
        {
            return rootElement.get(0).findElements(By.id(id));
        }
        return driver.findElements(By.id(id));
    }

    public List<WebElement> findElementsById(String id)
    {
        return findElementsById(Collections.emptyList(), id);
    }

    public List<WebElement> findElementsByTagName(List<WebElement> rootElement, String tagName)
    {
        if (!rootElement.isEmpty())
        {
            return rootElement.get(0).findElements(By.tagName(tagName));
        }
        return Collections.emptyList();
    }

    public List<WebElement> findElementsByXpath(List<WebElement> rootElement, String xpath)
    {
        if (!rootElement.isEmpty())
        {
            return findElementsByXpath(rootElement.get(0), xpath);
        }
        return Collections.emptyList();
    }

    public List<WebElement> findElementsByXpath(String xpath)
    {
        return driver.findElements(By.xpath(xpath));
    }

    public List<WebElement> findElementsByXpath(WebElement rootElement, String xpath)
    {
        if (rootElement != null)
        {
            return rootElement.findElements(By.xpath("." + xpath));
        }
        return Collections.emptyList();
    }
    
    public String getAttribute(WebElement element, String attributeName)
    {
        if (element == null || attributeName == null)
        {
            LOGGER.warn("GetAttribute: element or attribute is null, returning null");
            return null;
        }
        return element.getAttribute(attributeName);
    }

    public String getElementText(List<WebElement> rootElement)
    {
        if (!rootElement.isEmpty())
        {
            return rootElement.get(0).getText();
        }
        return null;
    }

    public String getElementTextByXpath(String xpath)
    {
        return getElementText(findElementsByXpath(xpath));
    }

    public void setWebDriver(WebDriver driver)
    {
        this.driver = driver;
    }
}
