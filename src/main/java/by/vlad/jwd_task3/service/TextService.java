package by.vlad.jwd_task3.service;

import by.vlad.jwd_task3.composite.TextComponent;
import by.vlad.jwd_task3.exception.CustomTextException;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortParagraphBySentenceCount(TextComponent text);
    List<TextComponent> findSentenceWithLongestWord(TextComponent text) throws CustomTextException;
    List<TextComponent> deleteSentencesWithWordsLessThan(TextComponent text, int countWord);
    Map<String, Integer> findCountOfWords(TextComponent text);
    long countVowelsInText(TextComponent text);
    long countConsonantsInText(TextComponent text);
}