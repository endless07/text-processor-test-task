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
import java.util.function.Consumer;

public class MainParser {

    public MainParser(SearchStrategy searchStrategy, Consumer<String> consumer) {
        this.searchStrategy = searchStrategy;
        this.consumer = consumer;
    }

    private TextParseStrategy actualParseStrategy = new F1TextParseStrategy();
    private List<TextParseStrategy> textParseStrategies = getTextParseStrategies();
    private SearchStrategy searchStrategy;
    private Consumer<String> consumer;

    private List<TextParseStrategy> getTextParseStrategies() {
        List<TextParseStrategy> strategies = new ArrayList<>();
        strategies.add(new F1TextParseStrategy());
        strategies.add(new F2TextParseStrategy());
        return strategies;
    }

    @SneakyThrows
    void parse(BufferedReader reader, String searchValue){
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
                    consumer.accept((searchStrategy.formatValidTextLine(eraseRedundantSpaces(textLine), actualParseStrategy)));
                }
            }
            textLine = reader.readLine();
        } while(textLine != null);

    }

    private String eraseRedundantSpaces(String textLine){
        textLine = textLine.replaceAll(" ", "");
        return textLine;
    }
}
