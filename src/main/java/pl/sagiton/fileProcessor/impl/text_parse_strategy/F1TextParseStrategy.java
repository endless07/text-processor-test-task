package pl.sagiton.fileProcessor.impl.text_parse_strategy;

import pl.sagiton.fileProcessor.impl.enums.FormatType;

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
