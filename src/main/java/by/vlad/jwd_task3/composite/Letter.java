package by.vlad.jwd_task3.composite;

public class Letter implements TextComposite {
    private char letter;

    public Letter(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
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
        return new StringBuilder(letter).toString();
    }
}
