package by.vlad.jwd_task3.service;

import by.vlad.jwd_task3.composite.TextComposite;

import java.util.List;
import java.util.Map;

public interface CustomTextService {
    List<TextComposite> sortParagraphBySentenceCount(TextComposite text);
    List<TextComposite> findSentenceWithLongestWord(TextComposite text);
    List<TextComposite> deleteSentencesWithWordsLessThan(TextComposite text, int countWord);
    Map<String, Integer> findCountOfWords(TextComposite text);
    long countVowelsInText(TextComposite text);
    long countConsonantsInText(TextComposite text);
}