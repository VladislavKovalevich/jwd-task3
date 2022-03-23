package by.vlad.jwd_task3.service.impl;

import by.vlad.jwd_task3.composite.*;
import by.vlad.jwd_task3.exception.CustomTextException;
import by.vlad.jwd_task3.service.TextService;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final String VOWEL_REGEX = "(?ui)[aeiouyуеыаоэяиюё]";
    private static final String CONSONANT_REGEX = "(?ui)[a-zа-я&&[^aeiouyуеыаоэяию]]";

    @Override
    public List<TextComponent> sortParagraphBySentenceCount(TextComponent text) {
        List<TextComponent> paragraphList = text.getAllLeaf();

        paragraphList = paragraphList.stream().sorted((o1, o2) -> {
            Integer sizeO1 = o1.getAllLeaf().size();
            Integer sizeO2 = o2.getAllLeaf().size();

            return sizeO1.compareTo(sizeO2);
        }).collect(Collectors.toList());

        return paragraphList;
    }

    @Override
    public List<TextComponent> findSentenceWithLongestWord(TextComponent text) throws CustomTextException {
        List<TextComponent> paragraphList = text.getAllLeaf();

        OptionalInt longestWordLength = OptionalInt.of(0);

        longestWordLength = text.getAllLeaf().stream()
                .flatMap(p -> p.getAllLeaf().stream())
                .flatMap(s -> s.getAllLeaf().stream())
                .flatMap(l -> l.getAllLeaf().stream())
                .filter(w -> w.getType().equals(TextComponentType.WORD))
                .mapToInt(w -> w.getAllLeaf().size())
                .max();

        longestWordLength.orElseThrow(() -> new CustomTextException("invalid value"));
        int maxLength = longestWordLength.getAsInt();
        List<TextComponent> result;

        result = text.getAllLeaf().stream()
                .flatMap(p -> p.getAllLeaf().stream())
                .filter(s -> s.getAllLeaf().stream()
                        .anyMatch(l -> l.getAllLeaf().stream()
                        .filter(w -> w.getType().equals(TextComponentType.WORD))
                        .anyMatch(w -> w.getAllLeaf().size() == maxLength)))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<TextComponent> deleteSentencesWithWordsLessThan(TextComponent text, int countWord) {
        List<TextComponent> paragraphList = text.getAllLeaf();
        List<TextComponent> sentenceList;

        for (TextComponent paragraph : paragraphList) {
            sentenceList = paragraph.getAllLeaf();
            for (TextComponent sentence : sentenceList) {
                int countOfWords = 0;
                for (TextComponent lexeme : sentence.getAllLeaf()) {
                    for (TextComponent word : lexeme.getAllLeaf()) {
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
    public Map<String, Integer> findCountOfWords(TextComponent text) {
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
    public long countVowelsInText(TextComponent text) {
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
    public long countConsonantsInText(TextComponent text) {
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
