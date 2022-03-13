package by.vlad.jwd_task3.interpreter;

@FunctionalInterface
public interface BitExpression {
    void interpret(ContextStack stack);
}
