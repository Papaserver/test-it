package com.codecool.histogram;

import org.junit.jupiter.api.*;
import org.junit.platform.commons.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TextReaderTest {



	@Test
	@Order(1)
	void wrongFileNameProvided_throwsFileNotFoundException() {
		TextReader wrongFileNameTest = new TextReader("src/test/resources/epty.txt");

		assertThrows(FileNotFoundException.class, wrongFileNameTest::read);
	}

	@Test
	@Order(2)
	void existingButEmptyFileProvided_returnsEmptyString() throws IOException {
		TextReader testEmpty = new TextReader("src/test/resources/empty.txt");

		assertEquals("", testEmpty.read());
	}

	@Test
	@Order(3)
	void oneLineTextInTextFile() throws IOException {
		TextReader testOneLine = new TextReader("src/test/resources/test.txt");

		assertNotEquals("", testOneLine.read());

		String newline = System.getProperty("line.separator");
		String[] strArr = testOneLine.read().split(newline);
		int lineCount = strArr.length;

		assertEquals(1,lineCount);
	}

	@Test
	@Order(4)
	void multipleLineInTextFile() throws IOException {
		TextReader testMultipleLines = new TextReader("src/test/resources/text.txt");

		assertNotEquals("", testMultipleLines.read());

		String newline = System.getProperty("line.separator");
		String[] strArr = testMultipleLines.read().split(newline);
		int lineCount = strArr.length;

		assertTrue(lineCount>1);
	}


}
