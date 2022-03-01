package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.Punctuation;
import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComposite;
import by.vlad.jwd_task3.composite.TextCompositeImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomWordParser extends AbstractParserHandler {
    private static final String WORD_PUNCTUATION_REGEX = "[\\wа-яА-ЯёЁ']+|[\\p{Punct}\\u2026]";
    private static final String WORD_REGEX = "[\\wа-яА-ЯёЁ']+";

    public CustomWordParser() {
        this.nextHandler = new CustomWordParser();
    }

    @Override
    public void parse(TextComposite component, String text) {

        Pattern pattern = Pattern.compile(WORD_PUNCTUATION_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                if (matcher.group(i).matches(WORD_REGEX)){
                    TextComposite wordComponent = new TextCompositeImpl(TextComponentType.WORD);
                    component.add(wordComponent);
                    nextHandler.parse(wordComponent, matcher.group(i));
                }else {
                    TextComposite punctComponent = new Punctuation(matcher.group(i).charAt(0));
                    component.add(punctComponent);
                }
            }
        }
    }
}
