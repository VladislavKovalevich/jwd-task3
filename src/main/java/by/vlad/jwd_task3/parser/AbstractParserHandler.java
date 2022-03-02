package by.vlad.jwd_task3.parser;

import by.vlad.jwd_task3.composite.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractParserHandler {
    private static Logger logger = LogManager.getLogger();

    protected AbstractParserHandler nextHandler = DefaultParserHandler.getInstance();

    public AbstractParserHandler() {
    }

    public abstract void parse(TextComposite component, String text);

    private static class DefaultParserHandler extends AbstractParserHandler{
        private static DefaultParserHandler handler = new DefaultParserHandler();

        private DefaultParserHandler(){
        }

        public static DefaultParserHandler getInstance(){
            return handler;
        }

        @Override
        public void parse(TextComposite component, String text) {
            logger.info("End of parsers chain");
        }
    }
}
