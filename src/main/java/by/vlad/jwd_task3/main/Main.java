package by.vlad.jwd_task3.main;

import by.vlad.jwd_task3.util.impl.BitOperationImpl;

public class Main {
    public static void main(String[] args) {
        BitOperationImpl bitOperation = BitOperationImpl.getInstance();

        String result = bitOperation.returnResultOfExpression("(7^5|1&2<<(2|5>>2&71))|1200");
        System.out.println(result);
    }
}
