package by.vlad.jwd_task3.composite;

import java.util.List;

public interface TextComponent {
    List<TextComponent> getAllLeaf();

    boolean add(TextComponent textComponent);

    boolean remove(TextComponent textComponent);

    TextComponentType getType();

    String toString();
}