package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComposite;
import by.vlad.jwd_task3.composite.TextCompositeImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParserHandler {
    private static final String SENTENCE_REGEX = "([A-Z]|[А-ЯЁ]).+?([.!?\\u2026])(\\s|$)";

    public SentenceParser() {
        this.nextHandler = new LexemeParser();
    }

    @Override
    public void parse(TextComposite component, String text) {
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            String sentence = matcher.group();
            TextComposite sentenceComponent = new TextCompositeImpl(TextComponentType.SENTENCE);
            component.add(sentenceComponent);
            nextHandler.parse(sentenceComponent, sentence);
        }
    }
}
