package com.idealista.scraper.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

public final class URLUtils
{
    private static final Logger LOGGER = LogManager.getLogger(URLUtils.class);
    
    public static URL generateUrl(String text)
    {
        try
        {
            return new URL(text);
        }
        catch (MalformedURLException e)
        {
            System.err.println("Error while generating URL for string: " + text + ": " + e.getMessage());
        }
        return null;
    }
    
    public static Set<URL> convertStringToUrls(Set<String> input)
    {
        return input.stream().map(t ->
        {
            try
            {
                return new URL(t);
            }
            catch (MalformedURLException e)
            {
                LOGGER.info("Could not convert to URL: {}", t);
            }
            return null;
        }).collect(Collectors.toSet());
    }
}
