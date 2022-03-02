package by.vlad.jwd_task3.composite;

import java.util.List;

public interface TextComposite {
    List<TextComposite> getAllLeaf();

    boolean add(TextComposite textComposite);

    boolean remove(TextComposite textComposite);

    TextComponentType getType();

    String toString();
}