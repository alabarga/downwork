package com.idealista.scraper.scraping.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.idealista.scraper.executor.ExecutorServiceProvider;
import com.idealista.scraper.model.Category;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractCategoriesChooser
{
    private static final Logger LOGGER = LogManager.getLogger(AbstractCategoriesChooser.class);

    @Autowired
    private ExecutorServiceProvider executorServiceProvider;
    
    protected Set<Category> executeAndGetResults(Queue<Callable<Category>> results)
    {
        List<Future<Category>> categories = new ArrayList<>();
        try
        {
            categories = executorServiceProvider.getExecutor().invokeAll(results);
        }
        catch (InterruptedException e)
        {
            LOGGER.error("Error while executing tasks: {}", e.getMessage());
        }

        return categories.stream().map(t ->
        {
            try
            {
                return t.get();
            }
            catch (InterruptedException | ExecutionException e)
            {
                LOGGER.error("Error while retrieving category task result: {}", e);
                return null;
            }
        }).collect(Collectors.toSet());
    }
}