package pl.sagiton.example.impl.strategy.parse;

import pl.sagiton.example.impl.enums.FormatType;

public class F1TextParseStrategy implements TextParseStrategy {
    @Override
    public String strategySplitter() {
        return ",";
    }

    @Override
    public boolean isApplicable(String textLine) {
        return textLine.startsWith(FormatType.F1.toString());
    }
}
