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

        TextComposite word1 = new TextCompositeImpl(TextComponentType.WORD);
        TextComposite word2 = new TextCompositeImpl(TextComponentType.WORD);

        TextComposite punct = new Symbol(TextComponentType.PUNCTUATION, '.');

        TextComposite lexeme1 = new TextCompositeImpl(TextComponentType.LEXEME);
        TextComposite lexeme2 = new TextCompositeImpl(TextComponentType.LEXEME);

        TextComposite sentence = new TextCompositeImpl(TextComponentType.SENTENCE);
        TextComposite paragraph = new TextCompositeImpl(TextComponentType.PARAGRAPH);
        TextComposite expected = new TextCompositeImpl(TextComponentType.TEXT);

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

        TextComposite actual = new TextCompositeImpl(TextComponentType.TEXT);
        parser.parse(actual, data);
        Assert.assertEquals(actual.toString(), expected.toString());
    }
}
