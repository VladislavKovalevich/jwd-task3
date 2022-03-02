package by.vlad.jwd_task3.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Punctuation implements TextComposite {
    private static final Logger logger = LogManager.getLogger();
    private char punct;

    public Punctuation(char punct) {
        this.punct = punct;
    }

    public char getPunct() {
        return punct;
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

        Punctuation that = (Punctuation) o;

        return punct == that.punct;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime* result + punct;
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(punct);
    }
}
