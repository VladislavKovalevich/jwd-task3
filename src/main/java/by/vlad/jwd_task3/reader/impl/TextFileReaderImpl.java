package by.vlad.jwd_task3.reader.impl;

import by.vlad.jwd_task3.exception.CustomTextException;
import by.vlad.jwd_task3.reader.TextFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.stream.Collectors;

public class TextFileReaderImpl implements TextFileReader {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String readTextFromFile(String path) throws CustomTextException {
        String text;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))) {

            text = bufferedReader.lines().collect(Collectors.joining());

        } catch (FileNotFoundException e) {
            logger.error("Cannot find this file " + path, e);
            throw new CustomTextException("Cannot find this file " + path, e);
        } catch (IOException e) {
            logger.error("IOException during read data from file", e);
            throw new CustomTextException("IOException during read data from file", e);
        }

        return text;
    }
}
