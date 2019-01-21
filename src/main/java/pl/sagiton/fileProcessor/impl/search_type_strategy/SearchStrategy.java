package pl.sagiton.fileProcessor.impl.search_type_strategy;

import pl.sagiton.fileProcessor.impl.text_parse_strategy.TextParseStrategy;

public interface SearchStrategy {
    boolean isApplicable(String searchStrategy);
    boolean textLineFitsTheSearchValueCheck(String textLine, String searchValue, TextParseStrategy actualStrategy);
    String formatValidTextLine(String textLine, TextParseStrategy actualStrategy);
}
