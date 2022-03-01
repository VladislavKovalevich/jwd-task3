package by.vlad.jwd_task3.composite;

public interface TextComposite {
    void operation();

    boolean add(TextComposite textComposite);

    boolean remove(TextComposite textComposite);
}
