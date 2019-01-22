package pl.sagiton.example.impl.strategy.parse;

public interface TextParseStrategy {
    String strategySplitter();
    boolean isApplicable(String textLine);
}
