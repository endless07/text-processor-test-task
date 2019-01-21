package pl.sagiton.fileProcessor.impl.searchTypeStrategy;

import java.io.BufferedReader;

public interface SearchStrategy {
    boolean isApplicable(String searchStrategy);
    void process(BufferedReader reader, String searchValue);
}
