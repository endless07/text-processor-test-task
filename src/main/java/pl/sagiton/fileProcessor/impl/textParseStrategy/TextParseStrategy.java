package pl.sagiton.fileProcessor.impl.textParseStrategy;

public interface TextParseStrategy {
    String strategySplitter();
    boolean isApplicable(String textLine);
    StringBuilder processLine();
}
