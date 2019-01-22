package pl.sagiton.example.impl.strategy.parse;

import pl.sagiton.example.impl.enums.FormatType;

public class F2TextParseStrategy implements TextParseStrategy {
    @Override
    public String strategySplitter() {
        return ";";
    }

    @Override
    public boolean isApplicable(String textLine) {
        return textLine.toLowerCase().startsWith(FormatType.F2.toString().toLowerCase());
    }
}
