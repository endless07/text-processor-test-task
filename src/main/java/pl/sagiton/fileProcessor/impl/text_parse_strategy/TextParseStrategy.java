package pl.sagiton.fileProcessor.impl.text_parse_strategy;

public interface TextParseStrategy {
    String strategySplitter();
    boolean isApplicable(String textLine);
}
