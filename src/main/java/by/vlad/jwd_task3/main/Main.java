package by.vlad.jwd_task3.main;

import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComposite;
import by.vlad.jwd_task3.composite.TextCompositeImpl;
import by.vlad.jwd_task3.exception.CustomTextException;
import by.vlad.jwd_task3.parser.CustomParagraphParser;
import by.vlad.jwd_task3.reader.TextFileReader;
import by.vlad.jwd_task3.reader.impl.TextFileReaderImpl;
import by.vlad.jwd_task3.service.CustomTextService;
import by.vlad.jwd_task3.service.impl.CustomTextServiceImpl;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "src\\main\\resources\\data.txt";

        TextFileReader fileReader = new TextFileReaderImpl();

        String text;
        try {
            text = fileReader.readTextFromFile(filePath);

            TextComposite textComposite = new TextCompositeImpl(TextComponentType.TEXT);
            CustomParagraphParser parser = new CustomParagraphParser();
            System.out.println("parser phase");

            parser.parse(textComposite, text);

            System.out.println("is parsed");

            System.out.println(textComposite.toString());

            CustomTextService customTextService = new CustomTextServiceImpl();

            System.out.println(customTextService.countConsonantsInText(textComposite));
            System.out.println(customTextService.countVowelsInText(textComposite));

            Map<String, Integer> map = customTextService.findCountOfWords(textComposite);

            for (Map.Entry entry : map.entrySet()) {
                System.out.println("Key: " + entry.getKey() + " Value: "
                        + entry.getValue());
            }

            List<TextComposite> resultList = customTextService.sortParagraphBySentenceCount(textComposite);

            System.out.println(resultList.toString());

            //resultList = customTextService.deleteSentencesWithWordsLessThan(textComposite, 31);

            //System.out.println(resultList.toString());

            resultList = customTextService.findSentenceWithLongestWord(textComposite);

            System.out.println(resultList.toString());
        } catch (CustomTextException e) {
            e.printStackTrace();
        }

    }
}
