package pl.sagiton.fileProcessor.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sagiton.fileProcessor.Application;
import pl.sagiton.fileProcessor.impl.search_type_strategy.SearchStrategy;
import pl.sagiton.fileProcessor.impl.search_type_strategy.SearchTypeCityVisitedStrategy;
import pl.sagiton.fileProcessor.impl.text_parse_strategy.TextParseStrategy;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

class MainParserTest {

    MainParser mainParser;
    BufferedReader bufferedReader;
    SearchStrategy searchStrategy;

    String SEARCH_VALUE = "13654902-Y";
    String FILEPATH = "D:\\Users\\Administrator\\IdeaProjects\\text-processor-test-task\\src\\test\\resources\\testfile.txt";

    @BeforeEach
    @SneakyThrows
    void setUp() {
        FileReader fileReader = new FileReader(FILEPATH);
        bufferedReader = new BufferedReader(fileReader);
        searchStrategy = new SearchTypeCityVisitedStrategy();
        mainParser = new MainParser(searchStrategy);
        MockitoAnnotations.initMocks(this);
    }

    @SneakyThrows
    @Test
    void shouldParseGivenText(){
        mainParser.parse(bufferedReader, SEARCH_VALUE);
    }
}