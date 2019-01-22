package pl.sagiton.example.impl.strategy.search;

import pl.sagiton.example.impl.enums.SearchStrategyType;
import pl.sagiton.example.impl.exception.CorruptedTextLineException;
import pl.sagiton.example.impl.strategy.parse.TextParseStrategy;

public class SearchTypeCityVisitorsStrategy implements SearchStrategy {


    @Override
    public boolean isApplicable(String searchStrategy) {
        return searchStrategy.equals(SearchStrategyType.CITY.toString());
    }

    @Override
    public boolean textLineFitsTheSearchValueCheck(String textLine, String searchValue, TextParseStrategy actualStrategy){
        String[] splittedTextLine = textLine.replaceAll("D ","").split(actualStrategy.strategySplitter());
        if (splittedTextLine.length != 3){
            throw new CorruptedTextLineException();
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
