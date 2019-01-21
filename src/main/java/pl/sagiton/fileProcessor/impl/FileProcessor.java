package pl.sagiton.fileProcessor.impl;

import lombok.SneakyThrows;
import pl.sagiton.fileProcessor.impl.exception.NoApplicableStrategyException;
import pl.sagiton.fileProcessor.impl.search_type_strategy.SearchTypeCityVisitedStrategy;
import pl.sagiton.fileProcessor.impl.search_type_strategy.SearchTypeCityVisitorsStrategy;
import pl.sagiton.fileProcessor.impl.search_type_strategy.SearchStrategy;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    List<SearchStrategy> searchStrategies = getSearchStrategies();

    private List<SearchStrategy> getSearchStrategies() {
        List<SearchStrategy> strategies = new ArrayList<>();
        strategies.add(new SearchTypeCityVisitedStrategy());
        strategies.add(new SearchTypeCityVisitorsStrategy());
        return strategies;
    }

    @SneakyThrows
    public void processFile(BufferedReader reader, String searchType, String searchValue){
        SearchStrategy searchStrategy = searchStrategies.stream().filter(s -> s.isApplicable(searchType)).findFirst().orElseThrow(NoApplicableStrategyException::new);
        MainParser parser = new MainParser(searchStrategy);
        parser.parse(reader, searchValue);
    }
}
