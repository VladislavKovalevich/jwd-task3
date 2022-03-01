package by.vlad.jwd_task3.composite;

import java.util.ArrayList;
import java.util.List;

public class TextCompositeImpl implements TextComposite {

    private TextComponentType type;
    private List<TextComposite> components = new ArrayList<>();

    public TextCompositeImpl(TextComponentType type) {
        this.type = type;
    }

    public void operation() {
    }

    public boolean add(TextComposite textComposite) {
        return components.add(textComposite);
    }

    public boolean remove(TextComposite textComposite) {
        return components.remove(textComposite);
    }

    public TextComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextCompositeImpl that = (TextCompositeImpl) o;

        if (type != that.type) return false;
        return components.equals(that.components);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + type.hashCode();
        result = prime * result + components.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("TextCompositeImpl{ type=")
                .append(type)
                .append(", components=")
                .append(components)
                .append('}').toString();
    }
}
