package by.vlad.jwd_task3.service.impl;

import by.vlad.jwd_task3.composite.*;
import by.vlad.jwd_task3.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final Logger logger = LogManager.getLogger();
    private static final String VOWEL_REGEX = "(?ui)[aeiouyуеыаоэяиюё]";
    private static final String CONSONANT_REGEX = "(?ui)[a-zа-я&&[^aeiouyуеыаоэяию]]";

    @Override
    public List<TextComposite> sortParagraphBySentenceCount(TextComposite text) {
        List<TextComposite> paragraphList = text.getAllLeaf();

        paragraphList.sort((o1, o2) -> {
            Integer sizeO1 = o1.getAllLeaf().size();
            Integer sizeO2 = o2.getAllLeaf().size();

            return sizeO1.compareTo(sizeO2);
        });

        return paragraphList;
    }

    @Override
    public List<TextComposite> findSentenceWithLongestWord(TextComposite text) {
        List<TextComposite> paragraphList = text.getAllLeaf();

        int sizeOfLongestWord = 0;

        for (TextComposite paragraph : paragraphList) {
            for (TextComposite sentence : paragraph.getAllLeaf()) {
                for (TextComposite lexeme : sentence.getAllLeaf()) {
                    for (TextComposite word : lexeme.getAllLeaf()) {
                        if (!(word.getType().equals(TextComponentType.PUNCTUATION))) {
                            List<TextComposite> letters = word.getAllLeaf();
                            if (letters.size() > sizeOfLongestWord) {
                                sizeOfLongestWord = letters.size();
                            }
                        }
                    }
                }
            }
        }

        List<TextComposite> result = new ArrayList<>();

        for (TextComposite paragraph : paragraphList) {
            for (TextComposite sentence : paragraph.getAllLeaf()) {
                for (TextComposite lexeme : sentence.getAllLeaf()) {
                    for (TextComposite word : lexeme.getAllLeaf()) {
                        if (!(word.getType().equals(TextComponentType.PUNCTUATION))) {
                            List<TextComposite> letters = word.getAllLeaf();
                            if (letters.size() == sizeOfLongestWord) {
                                result.add(word);
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    @Override
    public List<TextComposite> deleteSentencesWithWordsLessThan(TextComposite text, int countWord) {
        List<TextComposite> paragraphList = text.getAllLeaf();
        List<TextComposite> sentenceList;

        for (TextComposite paragraph : paragraphList) {
            sentenceList = paragraph.getAllLeaf();
            for (TextComposite sentence : sentenceList) {
                int countOfWords = 0;
                for (TextComposite lexeme : sentence.getAllLeaf()) {
                    for (TextComposite word : lexeme.getAllLeaf()) {
                        if (word.getType().equals(TextComponentType.WORD)) {
                            countOfWords++;
                        }
                    }
                }
                if (countOfWords < countWord) {
                    paragraph.remove(sentence);
                }
            }
        }

        return paragraphList;
    }

    @Override
    public Map<String, Integer> findCountOfWords(TextComposite text) {
        Map<String, Integer> sameWords;

        sameWords = text.getAllLeaf().stream()
                .flatMap(p -> p.getAllLeaf().stream())
                .flatMap(s -> s.getAllLeaf().stream())
                .flatMap(l -> l.getAllLeaf().stream())
                .filter(w -> w.getType().equals(TextComponentType.WORD))
                .map(w -> w.toString().toLowerCase())
                .collect(Collectors.toMap(str -> str, i -> 1, (i1, i2) -> i1 + i2));

        sameWords.values().removeIf(i -> i == 1);

        return sameWords;
    }

    @Override
    public long countVowelsInText(TextComposite text) {
        Pattern pattern = Pattern.compile(VOWEL_REGEX);

        long counter = text.getAllLeaf().stream()
                .flatMap(p -> p.getAllLeaf().stream())
                .flatMap(s -> s.getAllLeaf().stream())
                .flatMap(l -> l.getAllLeaf().stream())
                .filter(w -> w.getType().equals(TextComponentType.WORD))
                .flatMap(w -> w.getAllLeaf().stream())
                .map(Object::toString)
                .filter(let -> pattern.matcher(let).matches())
                .count();

        return counter;
    }

    @Override
    public long countConsonantsInText(TextComposite text) {
        Pattern pattern = Pattern.compile(CONSONANT_REGEX);

        long counter = text.getAllLeaf().stream()
                .flatMap(p -> p.getAllLeaf().stream())
                .flatMap(s -> s.getAllLeaf().stream())
                .flatMap(l -> l.getAllLeaf().stream())
                .filter(w -> w.getType().equals(TextComponentType.WORD))
                .flatMap(w -> w.getAllLeaf().stream())
                .map(Object::toString)
                .filter(let -> pattern.matcher(let).matches())
                .count();

        return counter;
    }
}
