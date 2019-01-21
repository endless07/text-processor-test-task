package pl.sagiton.fileProcessor.impl.textParseStrategy;

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

    @Override
    public StringBuilder processLine() {
        return null;
    }
}
