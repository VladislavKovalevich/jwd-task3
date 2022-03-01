package by.vlad.jwd_task3.composite;

public class Punctuation implements TextComposite {

    private char punct;

    public Punctuation(char punct) {
        this.punct = punct;
    }

    public char getPunct() {
        return punct;
    }

    public void operation() {
    }

    public boolean add(TextComposite textComposite) {
        return false;
    }

    public boolean remove(TextComposite textComposite) {
        return false;
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
        return new StringBuilder(punct).toString();
    }
}
