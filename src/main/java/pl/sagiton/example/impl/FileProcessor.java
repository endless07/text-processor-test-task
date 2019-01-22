package pl.sagiton.example.impl;

import lombok.SneakyThrows;
import pl.sagiton.example.impl.exception.NoApplicableStrategyException;
import pl.sagiton.example.impl.strategy.search.SearchTypeCityVisitedStrategy;
import pl.sagiton.example.impl.strategy.search.SearchTypeCityVisitorsStrategy;
import pl.sagiton.example.impl.strategy.search.SearchStrategy;

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
