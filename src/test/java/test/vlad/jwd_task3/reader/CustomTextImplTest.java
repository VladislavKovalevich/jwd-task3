package test.vlad.jwd_task3.reader;

import by.vlad.jwd_task3.exception.CustomTextException;
import by.vlad.jwd_task3.reader.impl.TextFileReaderImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomTextImplTest {
    private TextFileReaderImpl reader;

    @BeforeClass
    private void setUp(){
        reader = new TextFileReaderImpl();
    }

    @DataProvider(name = "reader")
    private Object[][] initData(){
        return new Object[][]{
                {"src\\test\\resources\\testData.txt", "It has survived - not only (five) centuries, but also the leap into electronic." +
                        " It is a long established fact that a reader will be distracted by the readable content of a page. "}
        };
    }

    @Test(dataProvider = "reader")
    public void testFileReader(String filePath, String expected) throws CustomTextException {
        String actual = reader.readTextFromFile(filePath);
        Assert.assertEquals(actual, expected);
    }
}
