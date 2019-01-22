package pl.sagiton.example.impl.strategy.search;


import pl.sagiton.example.impl.enums.SearchStrategyType;
import pl.sagiton.example.impl.exception.CorruptedTextLineException;
import pl.sagiton.example.impl.strategy.parse.TextParseStrategy;

public class SearchTypeCityVisitedStrategy implements SearchStrategy{


    @Override
    public boolean isApplicable(String searchStrategy) {
        return searchStrategy.equals(SearchStrategyType.ID.toString());
    }

    @Override
    public boolean textLineFitsTheSearchValueCheck(String textLine, String searchValue, TextParseStrategy actualStrategy){
        String[] splittedTextLine = textLine.split(actualStrategy.strategySplitter());
        if (splittedTextLine.length != 3){
            throw new CorruptedTextLineException();
        }
        textLine = splittedTextLine[2];
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
        String[] splittedTextLine = textLine.split(actualStrategy.strategySplitter());
        if (splittedTextLine.length != 3){
            return "";
        }
        return makeStringPretty(textLine.split(actualStrategy.strategySplitter())[1]);
    }

    private String makeStringPretty(String textLine){
        textLine = textLine.replaceAll(" ", "");
        return textLine;
    }
}
