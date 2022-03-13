package by.vlad.jwd_task3.interpreter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolishNotationParser {
    private static PolishNotationParser bitOperation;
    private static final String NUMBER_REGEXP = "\\d+";

    private PolishNotationParser(){}

    public static PolishNotationParser getInstance(){
        if (bitOperation == null){
            bitOperation = new PolishNotationParser();
        }
        return bitOperation;
    }

    private List<String> getPolishNotation(String expression){
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
                BitOperationEnum priority = BitOperationEnum.findBitOperation(symbol);
                BitOperationEnum priorityFirst = BitOperationEnum.findBitOperation(basicText.peek());

                while (!basicText.isEmpty() && !basicText.peek().equals("(")
                        && priority.getPriority() <= priorityFirst.getPriority()) {
                    String arg = basicText.pop();
                    result.add(arg);
                    priorityFirst = BitOperationEnum.findBitOperation(basicText.peek());
                }
                basicText.push(symbol);
            }
        }

        while (!basicText.isEmpty()){
            result.add(basicText.pop());
        }

        return result;
    }

    private List<BitExpression> parsePolishNotation(List<String> rpnExpression){
        List<BitExpression> expression = new ArrayList<>();

        for (String token: rpnExpression) {
            switch (BitOperationEnum.findBitOperation(token)){
                case NOT:{
                    expression.add(c -> c.pushValue(~(c.popValue())));
                    break;
                }

                case XOR:{
                    expression.add(c -> c.pushValue(c.popValue() ^ c.popValue()));
                    break;
                }

                case AND:{
                    expression.add(c -> c.pushValue(c.popValue() & c.popValue()));
                    break;
                }

                case OR:{
                    expression.add(c -> c.pushValue(c.popValue() | c.popValue()));
                    break;
                }

                case SHIFT_RIGHT:{
                    expression.add(c -> {
                        int n = c.popValue();
                        int x = c.popValue();
                        c.pushValue(x >> n);
                    });

                    break;
                }

                case SHIFT_LEFT:{
                    expression.add(c -> {
                        int n = c.popValue();
                        int x = c.popValue();
                        c.pushValue(x << n);
                    });
                    break;
                }

                case ASSIGN_SHIFT_RIGHT:{
                    expression.add(c -> {
                        int n = c.popValue();
                        int x = c.popValue();
                        c.pushValue(x >>> n);
                    });
                    break;
                }

                default:{
                    expression.add(c->c.pushValue(Integer.parseInt(token)));
                }
            }
        }

        return expression;
    }

    public String handleExpression(String expression){
        List<String> polishNotation = getPolishNotation(expression);
        List<BitExpression> expressions = parsePolishNotation(polishNotation);

        ContextStack stack = new ContextStack();

        for (BitExpression terminal : expressions) {
            terminal.interpret(stack);
        }

        return stack.popValue().toString();
    }
}
