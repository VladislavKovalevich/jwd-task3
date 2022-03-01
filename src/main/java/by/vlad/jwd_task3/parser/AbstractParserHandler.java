package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.TextComposite;

public abstract class AbstractParserHandler {

    protected AbstractParserHandler nextHandler = DefaultParserHandler.getInstance();

    public AbstractParserHandler() {
    }

    public abstract void parse(TextComposite component, String text);

    private static class DefaultParserHandler extends AbstractParserHandler{
        private static DefaultParserHandler handler;

        private DefaultParserHandler(){
        }

        public static DefaultParserHandler getInstance(){
            if (handler == null){
                handler = new DefaultParserHandler();
            }
            return handler;
        }

        @Override
        public void parse(TextComposite component, String text) {

        }
    }
}
