package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComponent;
import by.vlad.jwd_task3.composite.TextComposite;
import by.vlad.jwd_task3.interpreter.PolishNotationParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LexemeParser extends AbstractParserHandler{
    private static final String LEXEME_DELIMITER = "\\s";
    private static final String BIT_OPERATION_REGEX = "(\\d+)([\\&\\|\\^\\(\\~\\<+\\>+\\)]){2,}";//


    public LexemeParser() {
        this.nextHandler = new WordParser();
    }

    @Override
    public void parse(TextComponent composite, String text) {
        String[] lexemes = text.split(LEXEME_DELIMITER);


        for (int i = 0; i < lexemes.length; i++) {
            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);

            Pattern pattern = Pattern.compile(BIT_OPERATION_REGEX);
            Matcher matcher = pattern.matcher(lexemes[i]);
            if (matcher.find()){
                PolishNotationParser polishNotationParser = PolishNotationParser.getInstance();
                String temp = polishNotationParser.handleExpression(lexemes[i]);
                lexemes[i] = temp;
            }

            composite.add(lexemeComponent);
            nextHandler.parse(lexemeComponent, lexemes[i]);
        }
    }
}
