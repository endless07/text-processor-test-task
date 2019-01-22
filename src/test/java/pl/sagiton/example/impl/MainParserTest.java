package pl.sagiton.example.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import pl.sagiton.example.impl.strategy.search.SearchStrategy;
import pl.sagiton.example.impl.strategy.search.SearchTypeCityVisitedStrategy;
import pl.sagiton.example.impl.strategy.search.SearchTypeCityVisitorsStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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

    @BeforeEach
    @SneakyThrows
    void setUp() {
        File resourcesDirectory = new File(FILEPATH);
        FileReader fileReader = new FileReader(resourcesDirectory);
        bufferedReader = new BufferedReader(fileReader);
        searchStrategyCity = new SearchTypeCityVisitorsStrategy();
        searchStrategyID = new SearchTypeCityVisitedStrategy();
        mainParserCity = new MainParser(searchStrategyCity);
        mainParserID = new MainParser(searchStrategyID);
        MockitoAnnotations.initMocks(this);
    }

    @SneakyThrows
    @Test
    void shouldParseGivenTextAsID(){
        StringBuilder result = mainParserID.parse(bufferedReader, SEARCH_VALUE_ID);
        assertEquals(result.toString(), getCorrectIDStringBuilder().toString());
    }

    @SneakyThrows
    @Test
    void shouldParseGivenTextAsCITY(){
        StringBuilder result = mainParserCity.parse(bufferedReader, SEARCH_VALUE_CITY);
        assertEquals(result.toString(), getCorrectCityStringBuilder().toString());
    }

    private StringBuilder getCorrectIDStringBuilder(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CITYX");
        stringBuilder.append(separator);
        stringBuilder.append("CITYX2");
        stringBuilder.append(separator);
        return stringBuilder;
    }

    private StringBuilder getCorrectCityStringBuilder(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("EricaSEQQQ, 43654902Y");
        stringBuilder.append(separator);
        stringBuilder.append("EricaSEQW, 83654902-Y");
        stringBuilder.append(separator);
        stringBuilder.append("EricaSEWW, 93654902-Y");
        stringBuilder.append(separator);
        return stringBuilder;
    }
}