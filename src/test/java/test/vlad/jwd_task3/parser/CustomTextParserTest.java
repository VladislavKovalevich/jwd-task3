package test.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.*;
import by.vlad.jwd_task3.parser.ParagraphParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomTextParserTest {
    private ParagraphParser parser;

    @BeforeClass
    private void setUp(){
        parser = new ParagraphParser();
    }

    @Test
    public void testFileReader() {
        String data = "\tIt was.";

        TextComponent word1 = new TextComposite(TextComponentType.WORD);
        TextComponent word2 = new TextComposite(TextComponentType.WORD);

        TextComponent punct = new Symbol(TextComponentType.PUNCTUATION, '.');

        TextComponent lexeme1 = new TextComposite(TextComponentType.LEXEME);
        TextComponent lexeme2 = new TextComposite(TextComponentType.LEXEME);

        TextComponent sentence = new TextComposite(TextComponentType.SENTENCE);
        TextComponent paragraph = new TextComposite(TextComponentType.PARAGRAPH);
        TextComponent expected = new TextComposite(TextComponentType.TEXT);

        word1.add(new Symbol(TextComponentType.LETTER,'I'));
        word1.add(new Symbol(TextComponentType.LETTER,'t'));

        word2.add(new Symbol(TextComponentType.LETTER,'w'));
        word2.add(new Symbol(TextComponentType.LETTER,'a'));
        word2.add(new Symbol(TextComponentType.LETTER,'s'));

        lexeme1.add(word1);
        lexeme2.add(word2);
        lexeme2.add(punct);

        sentence.add(lexeme1);
        sentence.add(lexeme2);

        paragraph.add(sentence);
        expected.add(paragraph);

        TextComponent actual = new TextComposite(TextComponentType.TEXT);
        parser.parse(actual, data);
        Assert.assertEquals(actual.toString(), expected.toString());
    }
}
