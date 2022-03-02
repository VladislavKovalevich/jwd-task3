package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.Punctuation;
import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComposite;
import by.vlad.jwd_task3.composite.TextCompositeImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomWordParser extends AbstractParserHandler {
    private static final String WORD_PUNCTUATION_REGEX = "[0-9a-zA-Zа-яА-ЯёЁ']+|[\\p{Punct}\\u2026]";
    private static final String WORD_REGEX = "[0-9a-zA-Zа-яА-ЯёЁ']+";

    public CustomWordParser() {
        this.nextHandler = new CustomLetterParser();
    }

    @Override
    public void parse(TextComposite component, String text) {

        Pattern pattern = Pattern.compile(WORD_PUNCTUATION_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String group = matcher.group();

            Pattern patternWord = Pattern.compile(WORD_REGEX);
            Matcher matcherWord = patternWord.matcher(group);

            if (matcherWord.matches()) {
                TextComposite wordComponent = new TextCompositeImpl(TextComponentType.WORD);
                component.add(wordComponent);
                nextHandler.parse(wordComponent, group);
            } else {
                TextComposite punctComponent = new Punctuation(group.charAt(0));
                component.add(punctComponent);
            }
        }
    }
}
