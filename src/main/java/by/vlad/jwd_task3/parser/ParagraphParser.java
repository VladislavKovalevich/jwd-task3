package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComposite;
import by.vlad.jwd_task3.composite.TextCompositeImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParagraphParser extends AbstractParserHandler {
    public static final String PARAGRAPH_DELIMITER = "\\n";

    public ParagraphParser() {
        this.nextHandler = new SentenceParser();
    }

    @Override
    public void parse(TextComposite component, String text) {
        List<String> paragraphs = Stream.of(text.split(PARAGRAPH_DELIMITER))
                .collect(Collectors.toList());

        for (String paragraph : paragraphs) {
            TextComposite paragraphComponent = new TextCompositeImpl(TextComponentType.PARAGRAPH);
            component.add(paragraphComponent);
            nextHandler.parse(paragraphComponent, paragraph);
        }
    }
}