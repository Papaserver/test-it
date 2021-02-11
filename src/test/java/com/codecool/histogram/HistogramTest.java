package com.codecool.histogram;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {

	@Test
	@Order(1)
	void testToString_valuesEquals() {
		Histogram histogramForTest = new Histogram();

		int step = 4;
		int amount = 3;

		List<Range> testRangeGeneration = histogramForTest.generateRanges(step, amount);

		assertEquals(testRangeGeneration.size(), amount);
	}

	@Test
	@Order(2)
	void negativeIntAsStepParameter_ThrowsIllegalArgumentException() {
		Histogram histogramForTest = new Histogram();

		int step = -2;
		int amount = 3;

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> histogramForTest.generateRanges(step, amount));
		assertEquals("Step must be positive.", thrown.getMessage());
	}

	@Test
	@Order(3)
	void negativeIntAsAmountParameter_ThrowsIllegalArgumentException() {
		Histogram histogramForTest = new Histogram();

		int step = 2;
		int amount = -5;

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> histogramForTest.generateRanges(step, amount));
		assertEquals("Amount must be positive.", thrown.getMessage() );
	}

	@Test
	@Order(4)
	void allTheWordsAreTextIsInOneOfTheGivenRanges_oneWordInAllRanges() {
		//A hashmap-ben ugyanazok a kulcs-érték párok vannak-e
		//A generate-ből származó map és egy általunk elkészített map k-é párjainak meg kell egyezni
		//Jelen esetben mindegyik rang-hez 1 érték tartozik
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = Arrays.asList(new Range(0, 2), new Range(3, 5), new Range(5, 7));
		String forTest = "A ranges test";
		histogramForTest.generate(forTest, ranges);

		for (Map.Entry entry : histogramForTest.generate(forTest, ranges).entrySet()) {
			assertEquals(1, entry.getValue());
		}
	}

	@Test
	@Order(5)
	void wordsInTextAreOutOfRanges_zeroWordInAllRanges() {
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = Arrays.asList(new Range(0, 1), new Range(2, 3));
		String forTest = "Words longer than given ranges";
		histogramForTest.generate(forTest, ranges);

		for (Map.Entry entry : histogramForTest.generate(forTest, ranges).entrySet()) {
			assertEquals(0, entry.getValue());
		}
	}

	@Test
	@Order(6)
	void stringEqualsNull_throwsIllegalArgumentException() {
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = Arrays.asList(new Range(0, 1), new Range(2, 3));
		String forTest = null;

		assertThrows(IllegalArgumentException.class, () -> histogramForTest.generate(forTest, ranges));
	}

	@Test
	@Order(7)
	void emptyStringProvided_histogramReturned() {
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = Arrays.asList(new Range(0, 1), new Range(2, 3));
		String forTest = "";

		assertEquals(histogramForTest.generate(forTest, ranges), histogramForTest.getHistogram());
	}

	@Test
	@Order(8)
	void rangeEqualsNull_throwsIllegalArgumentException() {
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = null;
		String forTest = "A ranges test";

		assertThrows(IllegalArgumentException.class, () -> histogramForTest.generate(forTest, ranges));
	}

	@Test
	@Order(9)
	void callingGetHistogramBeforeGenerate_histogramEqualsEmptyHashMap() {
		Histogram histogramForTest = new Histogram();
		HashMap<String, Integer> emptyHashMap = new HashMap<>();

		assertEquals(emptyHashMap, histogramForTest.getHistogram());
	}

	@Test
	@Order(10)
	void callingGetHistogramAfterGenerate_histogramNotEqualsEmptyHashMap_andMultipleTimeCallingGenerate() {
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = Arrays.asList(new Range(0, 2), new Range(3, 5), new Range(5, 7));
		String forTest = "A ranges test";
		Map<Range, Integer> firstMap = histogramForTest.generate(forTest, ranges);

		List<Range> ranges2 = Arrays.asList(new Range(0, 3), new Range(4, 6));
		String forTest2 = "This is an other text for test";
		histogramForTest.generate(forTest2, ranges2);


		assertNotEquals(firstMap, histogramForTest.getHistogram());
	}

	@Test
	@Order(11)
	void stringRepresentationBeforeGenerate() {
		Histogram histogramForTest = new Histogram();

		assertEquals("", histogramForTest.toString());
	}

	@Test
	@Order(12)
	void stringRepresentationAfterGenerate() {
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = Arrays.asList(new Range(0, 2), new Range(3, 5), new Range(5, 7));
		String forTest = "A ranges test";
		histogramForTest.generate(forTest, ranges);

		assertEquals("0  - 2 | *"+System.lineSeparator()+
				"5  - 7 | *"+System.lineSeparator()+
				"3  - 5 | *"+System.lineSeparator(), histogramForTest.toString());
	}

	@Test
	@Order(13)
	void getMinOfHistogramShouldReturn11() throws IOException {
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = Arrays.asList(new Range(0, 1), new Range(2, 3), new Range(4, 6), new Range(7, 10));
		histogramForTest.generate(new TextReader("src/test/resources/text.txt").read(), ranges);

		assertEquals(11, histogramForTest.getMin());
	}

	@Test
	@Order(14)
	void getMaxOfHistogramShouldReturn115() throws IOException {
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = Arrays.asList(new Range(0, 1), new Range(2, 3), new Range(4, 6), new Range(7, 10));
		histogramForTest.generate(new TextReader("src/test/resources/text.txt").read(), ranges);

		assertEquals(115, histogramForTest.getMax());
	}

	@Test
	@Order(14)
	void callingNormalization_resultsZeroValueIn0To1Range() throws IOException {
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = Arrays.asList(new Range(0, 1), new Range(2, 3), new Range(4, 6), new Range(7, 10));
		histogramForTest.generate(new TextReader("src/test/resources/text.txt").read(), ranges);
		histogramForTest.normalizeValues();

		assertEquals(0, histogramForTest.getHistogram().values().stream().mapToInt(v -> v).min().orElse(1));
	}

	@Test
	@Order(15)
	void callingNormalization_results100ValueIn4To6Range() throws IOException {
		Histogram histogramForTest = new Histogram();
		List<Range> ranges = Arrays.asList(new Range(0, 1), new Range(2, 3), new Range(4, 6), new Range(7, 10));
		histogramForTest.generate(new TextReader("src/test/resources/text.txt").read(), ranges);
		histogramForTest.normalizeValues();

		assertEquals(100, histogramForTest.getHistogram().values().stream().mapToInt(v -> v).max().orElse(1));
	}
}
