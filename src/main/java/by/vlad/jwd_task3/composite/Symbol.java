package by.vlad.jwd_task3.composite;

import java.util.List;

public class Symbol implements TextComponent {
    private TextComponentType type;
    private char symbol;

    public Symbol(TextComponentType type, char symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getAllLeaf() {
        return null;
    }

    @Override
    public boolean add(TextComponent textComponent) {
        return false;
    }

    @Override
    public boolean remove(TextComponent textComponent) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol1 = (Symbol) o;

        if (symbol != symbol1.symbol) return false;
        return type == symbol1.type;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = prime * result + type.hashCode();
        result = 31 * result + (int) symbol;

        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
