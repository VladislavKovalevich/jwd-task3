package by.vlad.jwd_task3.interpreter;

import java.util.ArrayDeque;

public class ContextStack {
    private ArrayDeque<Integer> contextStack = new ArrayDeque<>();

    public Integer popValue(){
        return contextStack.pop();
    }

    public void pushValue(Integer value){
        contextStack.push(value);
    }
}
