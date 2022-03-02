package by.vlad.jwd_task3.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Letter implements TextComposite {
    private static final Logger logger = LogManager.getLogger();
    private char letter;

    public Letter(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    @Override
    public List<TextComposite> getAllLeaf() {
        return null;
    }

    public boolean add(TextComposite textComposite) {
        logger.warn("Cannot add element to final component");
        return false;
    }

    public boolean remove(TextComposite textComposite) {
        logger.warn("Cannot remove element from final component");
        return false;
    }

    @Override
    public TextComponentType getType() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Letter letter1 = (Letter) o;

        return letter == letter1.letter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + letter;
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(letter);
    }
}
