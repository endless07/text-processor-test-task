package pl.sagiton.fileProcessor.impl.text_parse_strategy;

import pl.sagiton.fileProcessor.impl.enums.FormatType;

public class F2TextParseStrategy implements TextParseStrategy {
    @Override
    public String strategySplitter() {
        return ";";
    }

    @Override
    public boolean isApplicable(String textLine) {
        return textLine.startsWith(FormatType.F2.toString());
    }
}
