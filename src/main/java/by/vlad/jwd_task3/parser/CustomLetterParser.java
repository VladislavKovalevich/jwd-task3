package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.Letter;
import by.vlad.jwd_task3.composite.TextComposite;

public class CustomLetterParser extends AbstractParserHandler {
    @Override
    public void parse(TextComposite component, String text) {
        char[] letters = text.toCharArray();

        for (char letter : letters) {
            component.add(new Letter(letter));
        }
    }
}
