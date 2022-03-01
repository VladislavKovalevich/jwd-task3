package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComposite;
import by.vlad.jwd_task3.composite.TextCompositeImpl;

import java.util.Arrays;
import java.util.List;

public class CustomParagraphParser extends AbstractParserHandler {
    public static final String PARAGRAPH_DELIMITER = "(^|\\n)(\\t|\\s{4})";

    public CustomParagraphParser() {
        this.nextHandler = new CustomSentenceParser();
    }

    @Override
    public void parse(TextComposite component, String text) {
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);

        for (String paragraph : paragraphs) {
            TextComposite paragraphComponent = new TextCompositeImpl(TextComponentType.PARAGRAPH);
            component.add(paragraphComponent);
            nextHandler.parse(paragraphComponent, paragraph);
        }
    }
}
