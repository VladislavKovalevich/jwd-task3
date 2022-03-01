package by.vlad.jwd_task3.util.impl;

public enum TokenPriorityEnum {
    FIRST_BRACKET("(", 0),
    LAST_BRACKET(")", 0),
    OR("|", 1),
    XOR("^", 2),
    AND("&", 3),
    SHIFT_LEFT("<", 4),
    SHIFT_RIGHT(">", 4),
    ASSIGN_SHIFT_RIGHT("R", 4),
    NOT("~", 5);

    private String symbol;
    private int priority;

    TokenPriorityEnum(String symbol, int priority){
        this.priority = priority;
        this.symbol = symbol;
    }

    public static TokenPriorityEnum findBitOperation(String name){
        TokenPriorityEnum found = FIRST_BRACKET;
        for (TokenPriorityEnum token: values()){
            if (token.symbol.equals(name)){
                found = token;
            }
        }

        return found;
    }

    int getPriority(){
        return priority;
    }
}
