package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.Symbol;
import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComponent;

public class LetterParser extends AbstractParserHandler {
    @Override
    public void parse(TextComponent composite, String text) {
        char[] letters = text.toCharArray();

        for (char letter : letters) {
            composite.add(new Symbol(TextComponentType.LETTER, letter));
        }
    }
}
