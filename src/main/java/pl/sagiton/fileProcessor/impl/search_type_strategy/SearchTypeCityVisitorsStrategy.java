package pl.sagiton.fileProcessor.impl.search_type_strategy;

import pl.sagiton.fileProcessor.impl.enums.SearchStrategyType;
import pl.sagiton.fileProcessor.impl.text_parse_strategy.TextParseStrategy;

public class SearchTypeCityVisitorsStrategy implements SearchStrategy {


    @Override
    public boolean isApplicable(String searchStrategy) {
        return searchStrategy.equals(SearchStrategyType.CITY.toString());
    }

    @Override
    public boolean textLineFitsTheSearchValueCheck(String textLine, String searchValue, TextParseStrategy actualStrategy){
        String[] splittedTextLine = textLine.replaceAll("D ","").split(actualStrategy.strategySplitter());
        if (splittedTextLine.length != 3){
            return false;
        }
        return splittedTextLine[1].trim().equals(searchValue);
    }

    @Override
    public String formatValidTextLine(String textLine, TextParseStrategy actualStrategy){
        String[] splittedTextLine = makeStringPretty(textLine).split(actualStrategy.strategySplitter());
        if (splittedTextLine.length != 3){
            return "";
        }
        return splittedTextLine[0] + ", " + splittedTextLine[2];
    }

    private String makeStringPretty(String textLine){
        textLine = textLine.replaceFirst("D", "");
        textLine = textLine.replaceAll(" ", "");
        return textLine;
    }
}
