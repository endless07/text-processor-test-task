package pl.sagiton.example.impl.strategy.search;

import org.junit.jupiter.api.Test;
import pl.sagiton.example.impl.exception.CorruptedTextLineException;
import pl.sagiton.example.impl.strategy.parse.F1TextParseStrategy;
import pl.sagiton.example.impl.strategy.parse.F2TextParseStrategy;

import static org.junit.jupiter.api.Assertions.*;

class SearchTypeCityVisitedStrategyTest {
    private SearchTypeCityVisitedStrategy strategy = new SearchTypeCityVisitedStrategy();
    private F1TextParseStrategy f1TextParseStrategy = new F1TextParseStrategy();
    private F2TextParseStrategy f2TextParseStrategy = new F2TextParseStrategy();


    private String NOT_APPLICABLE = "NOTAPPLICABLEBOI";
    private String APPLICABLE = "ID";
    private String TEXTLINE_CORRECT_F1 = "D Jane Doe,LONDON,09877359D";
    private String TEXTLINE_CORRECT_F2 = "D Jane Doe ; LONDON ; 09877359-D";
    private String TEXTLINE_INCORRECT = "IM SO INCORRECT";

    @Test
    void shouldBeApplicable(){
        boolean result = strategy.isApplicable(APPLICABLE);
        assertTrue(result);
    }

    @Test
    void shouldNotBeApplicable(){
        boolean result = strategy.isApplicable(NOT_APPLICABLE);
        assertFalse(result);
    }

    @Test
    void textLineShouldBeApplicableF1(){
        boolean result = strategy.textLineFitsTheSearchValueCheck(TEXTLINE_CORRECT_F1, "09877359D", f1TextParseStrategy);
        assertTrue(result);
    }

    @Test
    void textLineShouldBeApplicableF2(){
        boolean result = strategy.textLineFitsTheSearchValueCheck(TEXTLINE_CORRECT_F2, "09877359D", f2TextParseStrategy);
        assertTrue(result);
    }

    @Test
    void shouldFormatF1TextLineCorrectly(){
        String result = strategy.formatValidTextLine(TEXTLINE_CORRECT_F1, f1TextParseStrategy);
        assertEquals("LONDON", result);
    }

    @Test
    void shouldFormatF2TextLineCorrectly(){
        String result = strategy.formatValidTextLine(TEXTLINE_CORRECT_F2, f2TextParseStrategy);
        assertEquals("LONDON", result);
    }

    @Test
    void shouldNotFormatTextLineCorrectly(){
        String result = strategy.formatValidTextLine(TEXTLINE_INCORRECT, f1TextParseStrategy);
        assertEquals(result, "");
    }
}