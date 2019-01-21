package pl.sagiton.fileProcessor.impl.search_type_strategy;


import pl.sagiton.fileProcessor.impl.enums.SearchStrategyType;
import pl.sagiton.fileProcessor.impl.text_parse_strategy.TextParseStrategy;

public class SearchTypeCityVisitedStrategy implements SearchStrategy{


    @Override
    public boolean isApplicable(String searchStrategy) {
        return searchStrategy.equals(SearchStrategyType.ID.toString());
    }

    @Override
    public boolean textLineFitsTheSearchValueCheck(String textLine, String searchValue, TextParseStrategy actualStrategy){
        String reformatedSearchValue;
        if (!searchValue.contains("-")) {
            int indexToInsertDash = searchValue.length() - 1;
            reformatedSearchValue = searchValue.substring(0, indexToInsertDash)+"-"+searchValue.substring(indexToInsertDash, searchValue.length());
        } else {
            reformatedSearchValue = searchValue.replaceAll("-", "");
        }
        return textLine.contains(searchValue) || textLine.contains(reformatedSearchValue);
    }

    @Override
    public String formatValidTextLine(String textLine, TextParseStrategy actualStrategy){
        return textLine.split(actualStrategy.strategySplitter())[1];
    }
}
