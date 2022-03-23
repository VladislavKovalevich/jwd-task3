package test.vlad.jwd_task3.interpreter;

import by.vlad.jwd_task3.interpreter.PolishNotationParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PolishNotationParserTest {
    private PolishNotationParser parser;

    @BeforeClass
    private void setUp(){
        parser = PolishNotationParser.getInstance();
    }

    @DataProvider(name = "polishNotationParser")
    private Object[][] initDataSet(){
        return new Object[][]{
                {"0", "3<<4&6>>2"},
                {"2", "2&(2|4)"},
                {"96", "3<<5"},
        };
    }

    @Test(dataProvider = "polishNotationParser")
    public void testPolishNotationParser(String expected, String inputData){
        String actual = parser.handleExpression(inputData);
        Assert.assertEquals(actual, expected);
    }
}
