package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComposite;
import by.vlad.jwd_task3.composite.TextCompositeImpl;
import by.vlad.jwd_task3.util.BitOperation;
import by.vlad.jwd_task3.util.impl.BitOperationImpl;


public class CustomLexemeParser extends AbstractParserHandler{
    private static final String LEXEME_DELIMITER = "\\s";
    private static final String BIT_OPERATION_REGEX = "(\\d+)([\\&\\|\\^\\(\\~\\<\\>\\)]){2,}";//


    public CustomLexemeParser() {
        this.nextHandler = new CustomWordParser();
    }

    @Override
    public void parse(TextComposite component, String text) {
        String[] lexemes = text.split(LEXEME_DELIMITER);


        for (int i = 0; i < lexemes.length; i++) {
            TextComposite lexemeComponent = new TextCompositeImpl(TextComponentType.LEXEME);
            if (lexemes[i].matches(BIT_OPERATION_REGEX)){
                BitOperation bitOperation = BitOperationImpl.getInstance();
                String temp = bitOperation.returnResultOfExpression(lexemes[i]);
                lexemes[i] = temp;
            }

            component.add(lexemeComponent);
            nextHandler.parse(lexemeComponent, lexemes[i]);
        }
    }
}
