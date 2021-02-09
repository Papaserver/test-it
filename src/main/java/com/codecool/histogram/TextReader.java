package com.codecool.histogram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {

    private String fileName;

    /**
     * Constructs a TextReader with a specified file.
     * @param fileName name of the file to read
     */
    public TextReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns the text content of the file specified in constructor.
     * @return text content of previously specified file
     * @throws IOException in case of non-existing file
     */
    public String read() throws IOException {
        StringBuilder resultBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String strCurrentLine;

        while ((strCurrentLine = br.readLine()) != null) {
            resultBuilder.append(String.format("%s%s", strCurrentLine, System.lineSeparator()));
        }

        return resultBuilder.toString();
    }
}
