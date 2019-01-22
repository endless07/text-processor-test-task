package pl.sagiton.example.impl.strategy.search;

import pl.sagiton.example.impl.strategy.parse.TextParseStrategy;

public interface SearchStrategy {
    boolean isApplicable(String searchStrategy);
    boolean textLineFitsTheSearchValueCheck(String textLine, String searchValue, TextParseStrategy actualStrategy);
    String formatValidTextLine(String textLine, TextParseStrategy actualStrategy);
}
