package pl.sagiton.fileProcessor.impl.search_type_strategy;

import org.junit.jupiter.api.Test;
import pl.sagiton.fileProcessor.impl.text_parse_strategy.F1TextParseStrategy;
import pl.sagiton.fileProcessor.impl.text_parse_strategy.F2TextParseStrategy;

import static org.junit.jupiter.api.Assertions.*;

class SearchTypeCityVisitorsStrategyTest {

    private SearchTypeCityVisitorsStrategy strategy = new SearchTypeCityVisitorsStrategy();
    private F1TextParseStrategy f1TextParseStrategy = new F1TextParseStrategy();
    private F2TextParseStrategy f2TextParseStrategy = new F2TextParseStrategy();


    private String NOT_APPLICABLE = "NOTAPP";
    private String APPLICABLE = "CITY";
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
        boolean result = strategy.textLineFitsTheSearchValueCheck(TEXTLINE_CORRECT_F1, "LONDON", f1TextParseStrategy);
        assertTrue(result);
    }

    @Test
    void textLineShouldBeApplicableF2(){
        boolean result = strategy.textLineFitsTheSearchValueCheck(TEXTLINE_CORRECT_F2, "LONDON", f2TextParseStrategy);
        assertTrue(result);
    }

    @Test
    void textLineShouldNotBeApplicable(){
        boolean result = strategy.textLineFitsTheSearchValueCheck(TEXTLINE_INCORRECT, "LONDON", f1TextParseStrategy);
        assertFalse(result);
    }

    @Test
    void shouldFormatF1TextLineCorrectly(){
        String result = strategy.formatValidTextLine(TEXTLINE_CORRECT_F1, f1TextParseStrategy);
        assertEquals(result, "JaneDoe, 09877359D");
    }

    @Test
    void shouldFormatF2TextLineCorrectly(){
        String result = strategy.formatValidTextLine(TEXTLINE_CORRECT_F2, f2TextParseStrategy);
        assertEquals(result, "JaneDoe, 09877359-D");
    }

    @Test
    void shouldNotFormatTextLineCorrectly(){
        String result = strategy.formatValidTextLine(TEXTLINE_INCORRECT, f1TextParseStrategy);
        assertEquals(result, "");
    }



}