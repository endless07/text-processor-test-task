package pl.sagiton.example.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import pl.sagiton.example.impl.strategy.search.SearchStrategy;
import pl.sagiton.example.impl.strategy.search.SearchTypeCityVisitedStrategy;
import pl.sagiton.example.impl.strategy.search.SearchTypeCityVisitorsStrategy;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainParserTest {

    MainParser mainParserCity;
    MainParser mainParserID;
    BufferedReader bufferedReader;
    SearchStrategy searchStrategyCity;
    SearchStrategy searchStrategyID;
    String separator = System.getProperty("line.separator");

    String SEARCH_VALUE_CITY = "BAR";
    String SEARCH_VALUE_ID = "13654902-Y";
    String FILEPATH = "src/test/resources/testfile.txt";
    List<String> results;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        File resourcesDirectory = new File(FILEPATH);
        FileReader fileReader = new FileReader(resourcesDirectory);
        results = new LinkedList<>();
        bufferedReader = new BufferedReader(fileReader);
        searchStrategyCity = new SearchTypeCityVisitorsStrategy();
        searchStrategyID = new SearchTypeCityVisitedStrategy();
        mainParserCity = new MainParser(searchStrategyCity, results::add);
        mainParserID = new MainParser(searchStrategyID, results::add);
        MockitoAnnotations.initMocks(this);
    }

    @SneakyThrows
    @Test
    void shouldParseGivenTextAsID(){
        mainParserID.parse(bufferedReader, SEARCH_VALUE_ID);
        assertEquals(getCorrectIDStringBuilder().toString().trim(), String.join(separator, results).trim());
    }

    @SneakyThrows
    @Test
    void shouldParseGivenTextAsCITY(){
        mainParserCity.parse(bufferedReader, SEARCH_VALUE_CITY);
        assertEquals(getCorrectCityStringBuilder().toString().trim(), String.join(separator, results).trim());
    }

    private StringBuilder getCorrectIDStringBuilder(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CITYX");
        stringBuilder.append(separator);
        stringBuilder.append("CITYX2");
        return stringBuilder;
    }

    private StringBuilder getCorrectCityStringBuilder(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("EricaSEQQQ, 43654902Y");
        stringBuilder.append(separator);
        stringBuilder.append("EricaSEQW, 83654902-Y");
        stringBuilder.append(separator);
        stringBuilder.append("EricaSEWW, 93654902-Y");
        return stringBuilder;
    }
}