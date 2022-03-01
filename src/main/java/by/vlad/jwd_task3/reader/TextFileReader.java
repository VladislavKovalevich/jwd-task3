package by.vlad.jwd_task3.reader;

import by.vlad.jwd_task3.exception.CustomTextException;

public interface TextFileReader {
    String readTextFromFile(String path) throws CustomTextException;
}
