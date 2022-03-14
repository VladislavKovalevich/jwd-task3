package test.vlad.jwd_task3.service;

import by.vlad.jwd_task3.composite.TextComponent;
import by.vlad.jwd_task3.composite.TextComposite;
import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.exception.CustomTextException;
import by.vlad.jwd_task3.parser.ParagraphParser;
import by.vlad.jwd_task3.service.TextService;
import by.vlad.jwd_task3.service.impl.TextServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TextServiceTest {
    private TextService service;
    private TextComponent text;
    private TextComponent text2;

    @BeforeClass
    void setUp(){
        ParagraphParser parser = new ParagraphParser();

        text = new TextComposite(TextComponentType.TEXT);
        text2 = new TextComposite(TextComponentType.TEXT);

        parser.parse(text, "Some data! Test test. Qwerty dsa asd. Qwerty sum.\nTestssooooo Test tetete ttesa.\nTypes tests.");
        parser.parse(text2, "Some data! Test test. Qwerty dsa asd. Qwerty sum.\nTestssooooo Test tetete ttesa.\nTypes tests.");

        service = new TextServiceImpl();
    }


    @Test
    void sortParagraphsBySentenceNumberTest(){
        List<TextComponent> sorted = service.sortParagraphBySentenceCount(text);

        int[] actual = new int[sorted.size()];
        int[] expected = {1, 1, 4};

        for (int i = 0; i<sorted.size();i++){
            actual[i] = sorted.get(i).getAllLeaf().size();
        }

        Assert.assertEquals(actual,expected);
    }

    @Test
    void findSentencesWithLongestWord() throws CustomTextException {
        List sentences = service.findSentenceWithLongestWord(text);
        String expected = "Testssooooo";
        String actual = sentences.get(0).toString();
        Assert.assertEquals(expected,actual);
    }

    @Test
    void removeAllSentencesWithNumberWordsTest() {
        List<TextComponent> paragraphs = service.deleteSentencesWithWordsLessThan(text2, 3);
        String expected = "Qwerty dsa asd.";
        String actual = paragraphs.get(0).getAllLeaf().get(0).toString().trim();
        Assert.assertEquals(actual, expected);
    }

    @Test
    void findDuplicateNumberTest() {
        Map<String, Integer> map = service.findCountOfWords(text);
        int expected = 3;
        int actual = map.get("test");
        Assert.assertEquals(actual, expected);
    }

    @Test
    void countVowelTest() {
        long actual = service.countVowelsInText(text);
        long expected = 28;
        Assert.assertEquals(expected,actual);
    }

    @Test
    void countConsonantsTest() {
        long actual = service.countConsonantsInText(text);
        long expected = 45;
        Assert.assertEquals(expected,actual);
    }
}