package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.Symbol;
import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComposite;

public class LetterParser extends AbstractParserHandler {
    @Override
    public void parse(TextComposite component, String text) {
        char[] letters = text.toCharArray();

        for (char letter : letters) {
            component.add(new Symbol(TextComponentType.LETTER, letter));
        }
    }
}
