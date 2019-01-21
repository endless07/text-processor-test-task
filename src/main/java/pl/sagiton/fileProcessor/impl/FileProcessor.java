package pl.sagiton.fileProcessor.impl;

import lombok.SneakyThrows;
import pl.sagiton.fileProcessor.impl.exception.NoApplicableStrategyException;
import pl.sagiton.fileProcessor.impl.searchTypeStrategy.SearchTypeCityVisitedStrategy;
import pl.sagiton.fileProcessor.impl.searchTypeStrategy.SearchTypeCityVisitorsStrategy;
import pl.sagiton.fileProcessor.impl.searchTypeStrategy.SearchStrategy;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileProcessor {

    List<SearchStrategy> strategies = getStrategies();

    private List<SearchStrategy> getStrategies() {
        List<SearchStrategy> strategies = new ArrayList<>();
        strategies.add(new SearchTypeCityVisitedStrategy());
        strategies.add(new SearchTypeCityVisitorsStrategy());
        return strategies;
    }

    @SneakyThrows
    public void processFile(BufferedReader reader, String searchType, String searchValue){
        Optional<SearchStrategy> strategy = strategies.stream().filter(s -> s.isApplicable(searchType)).findFirst();
        strategy.orElseThrow(NoApplicableStrategyException::new).process(reader, searchValue);
    }
}
