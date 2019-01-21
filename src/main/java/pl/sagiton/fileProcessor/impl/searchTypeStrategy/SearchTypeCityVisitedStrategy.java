package pl.sagiton.fileProcessor.impl.searchTypeStrategy;

import lombok.SneakyThrows;
import pl.sagiton.fileProcessor.impl.enums.FormatType;
import pl.sagiton.fileProcessor.impl.exception.NoApplicableStrategyException;
import pl.sagiton.fileProcessor.impl.textParseStrategy.F1TextParseStrategy;
import pl.sagiton.fileProcessor.impl.textParseStrategy.F2TextParseStrategy;
import pl.sagiton.fileProcessor.impl.textParseStrategy.TextParseStrategy;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class SearchTypeCityVisitedStrategy implements SearchStrategy{

    private String lineSeparator = System.lineSeparator();
    private StringBuilder resultBuilder = new StringBuilder();
    private TextParseStrategy actualStrategy = new F1TextParseStrategy();
    private List<TextParseStrategy> textParseStrategies = getTextParseStrategies();

    private List<TextParseStrategy> getTextParseStrategies() {
        List<TextParseStrategy> strategies = new ArrayList<>();
        strategies.add(new F1TextParseStrategy());
        strategies.add(new F2TextParseStrategy());
        return strategies;
    }

    @Override
    public boolean isApplicable(String searchStrategy) {
        return searchStrategy.equals(SearchStrategyType.ID.toString());
    }

    @SneakyThrows
    @Override
    public void process(BufferedReader reader, String searchValue) {
        String textLine = reader.readLine();
        do {
            if (!textLine.startsWith(FormatType.D.toString())){
                String finalTextLine = textLine;
                if (!actualStrategy.isApplicable(finalTextLine)){
                    actualStrategy = textParseStrategies.stream().filter(s -> s.isApplicable(finalTextLine)).findFirst().orElseThrow(NoApplicableStrategyException::new);
                }
                textLine = reader.readLine();
                continue;
            }
            if (textLineFitsTheSearchValueCheck(textLine, searchValue)){
                resultBuilder.append(formatValidTextLine(textLine));
                resultBuilder.append(lineSeparator);
            }
            textLine = reader.readLine();
        } while(textLine != null);

        System.out.println(resultBuilder.toString());
    }

    private boolean textLineFitsTheSearchValueCheck(String textLine, String searchValue){
        String reformatedSearchValue;
        if (!searchValue.contains("-")) {
            int indexToInsertDash = searchValue.length() - 1;
            reformatedSearchValue = searchValue.substring(0, indexToInsertDash)+"-"+searchValue.substring(indexToInsertDash, searchValue.length());
        } else {
            reformatedSearchValue = searchValue.replaceAll("-", "");
        }
        return textLine.contains(searchValue) || textLine.contains(reformatedSearchValue);
    }

    private String formatValidTextLine(String textLine){
        return textLine.split(actualStrategy.strategySplitter())[1];
    }
}
