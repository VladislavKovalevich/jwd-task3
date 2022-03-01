package by.vlad.jwd_task3.util.impl;

import by.vlad.jwd_task3.util.BitOperation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BitOperationImpl implements BitOperation {
    private static BitOperationImpl bitOperation;
    private static final String NUMBER_REGEXP = "\\d+";

    private BitOperationImpl(){}

    public static BitOperationImpl getInstance(){
        if (bitOperation == null){
            bitOperation = new BitOperationImpl();
        }
        return bitOperation;
    }

    @Override
    public String returnResultOfExpression(String expression) {
        List<String> rpnExpression = parseExpression(expression);
        return String.valueOf(getValueOfReversePolishExpression(rpnExpression));
    }

    public List<String> parseExpression(String expression){
        ArrayDeque<String> basicText = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        StringBuilder numberString = new StringBuilder();

        expression = expression.replaceAll("<<", "<")
                .replaceAll(">>", ">")
                .replaceAll(">>>", "R");

        for (int i = 0; i < expression.length(); i++) {
            String symbol = String.valueOf(expression.charAt(i));

            if (symbol.matches(NUMBER_REGEXP)){
                numberString.append(symbol);

                if (i < expression.length() - 1 && !String.valueOf(expression.charAt(i + 1)).matches(NUMBER_REGEXP)){
                    result.add(numberString.toString());
                    numberString = new StringBuilder();
                }else if(i == expression.length() - 1){
                    result.add(numberString.toString());
                }
            }else if (symbol.equals("(")){
                basicText.push(symbol);
            }else if (symbol.equals(")")){
                while (!basicText.peek().equals("(")){
                    result.add(basicText.pop());
                }
                basicText.pop();
            }else{
                TokenPriorityEnum priority = TokenPriorityEnum.findBitOperation(symbol);
                TokenPriorityEnum priorityFirst = TokenPriorityEnum.findBitOperation(basicText.peek());

                while (!basicText.isEmpty() && !basicText.peek().equals("(")
                        && priority.getPriority() <= priorityFirst.getPriority()) {
                    String arg = basicText.pop();
                    result.add(arg);
                    priorityFirst = TokenPriorityEnum.findBitOperation(basicText.peek());
                }
                basicText.push(symbol);
            }
        }

        while (!basicText.isEmpty()){
            result.add(basicText.pop());
        }

        return result;
    }

    public int getValueOfReversePolishExpression(List<String> rpnExpression){
        int result = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < rpnExpression.size(); i++) {
            if (rpnExpression.get(i).matches(NUMBER_REGEXP)){
                stack.push(Integer.parseInt(rpnExpression.get(i)));
            }else{
                String token = rpnExpression.get(i);
                Integer b = stack.pop();
                if (TokenPriorityEnum.findBitOperation(token) == TokenPriorityEnum.NOT){
                    stack.push(~b);
                }else{
                    Integer a = stack.pop();
                    switch (TokenPriorityEnum.findBitOperation(token)){
                        case OR:{
                            stack.push(a | b);
                            break;
                        }
                        case AND:{
                            stack.push(a & b);
                            break;
                        }
                        case XOR:{
                            stack.push(a ^ b);
                            break;
                        }
                        case SHIFT_LEFT:{
                            stack.push(a << b);
                            break;
                        }
                        case SHIFT_RIGHT:{
                            stack.push(a >> b);
                            break;
                        }
                        case ASSIGN_SHIFT_RIGHT:{
                            stack.push(a >>> b);
                            break;
                        }
                        default:{
                            break;
                        }
                    }
                }
            }
        }

        result = stack.pop();
        return result;
    }
}
