package pl.sagiton.example.impl;

import lombok.SneakyThrows;
import pl.sagiton.example.impl.enums.FormatType;
import pl.sagiton.example.impl.exception.NoApplicableStrategyException;
import pl.sagiton.example.impl.strategy.search.SearchStrategy;
import pl.sagiton.example.impl.strategy.parse.F1TextParseStrategy;
import pl.sagiton.example.impl.strategy.parse.F2TextParseStrategy;
import pl.sagiton.example.impl.strategy.parse.TextParseStrategy;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class MainParser {

    public MainParser(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    private StringBuilder resultBuilder = new StringBuilder();
    private TextParseStrategy actualParseStrategy = new F1TextParseStrategy();
    private List<TextParseStrategy> textParseStrategies = getTextParseStrategies();
    private SearchStrategy searchStrategy;

    private List<TextParseStrategy> getTextParseStrategies() {
        List<TextParseStrategy> strategies = new ArrayList<>();
        strategies.add(new F1TextParseStrategy());
        strategies.add(new F2TextParseStrategy());
        return strategies;
    }

    @SneakyThrows
    StringBuilder parse(BufferedReader reader, String searchValue){
        String textLine = reader.readLine();
        do {
            if (!textLine.startsWith(FormatType.D.toString())){
                String finalTextLine = textLine;
                if (!actualParseStrategy.isApplicable(finalTextLine)){
                    actualParseStrategy = textParseStrategies.stream().filter(s -> s.isApplicable(finalTextLine)).findFirst().orElseThrow(NoApplicableStrategyException::new);
                }
                textLine = reader.readLine();
                continue;
            }
            if (searchStrategy.textLineFitsTheSearchValueCheck(textLine, searchValue, actualParseStrategy)){
                String lineToAppend = searchStrategy.formatValidTextLine(eraseRedundantSpaces(textLine), actualParseStrategy);
                if (!lineToAppend.isEmpty()){
                    System.out.println((searchStrategy.formatValidTextLine(eraseRedundantSpaces(textLine), actualParseStrategy)));
                }
            }
            textLine = reader.readLine();
        } while(textLine != null);

        return resultBuilder;
    }

    private String eraseRedundantSpaces(String textLine){
        textLine = textLine.replaceAll(" ", "");
        return textLine;
    }
}
