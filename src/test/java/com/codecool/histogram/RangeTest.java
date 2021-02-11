package com.codecool.histogram;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RangeTest {

	@Test
	@Order(1)
	void fromValueLowerThanZero_ThrowsIllegalArgumentException() {

		assertThrows(IllegalArgumentException.class, () -> new Range(-1, 5));
	}

	@Test
	@Order(2)
	void toValueIsLowerThanFromValue_ThrowsIllegalArgumentException() {

		assertThrows(IllegalArgumentException.class, () -> new Range(10, 5));
	}

	@Test
	@Order(3)
	void wordLengthIsInRange_AssertTrue() {
		Range rangeTest = new Range(5, 10);

		String forTrue = "thousand";

		assertTrue(rangeTest.isInRange(forTrue));
	}

	@Test
	@Order(4)
	void wordLengthEqualsToRangeFrom_AssertTrue() {
		Range rangeTest = new Range(5, 10);

		String forTrue = "broad";

		assertTrue(rangeTest.isInRange(forTrue));
	}

	@Test
	@Order(5)
	void wordLengthEqualsToRangeTo_AssertTrue() {
		Range rangeTest = new Range(5, 10);

		String forTrue = "mustbetrue";

		assertTrue(rangeTest.isInRange(forTrue));
	}

	@Test
	@Order(6)
	void wordLengthOutOfRange_isInRangeFalse() {
		Range rangeTest = new Range(5, 10);

		String forTrue = "Thismustbeenoughlong";

		assertFalse(rangeTest.isInRange(forTrue));
	}

	@Test
	@Order(7)
	void testToString_valuesEquals() {
		Range rangeTest = new Range(5, 10);

		String rangeToStringMustEqualTo = "5  - 10";

		assertEquals(rangeToStringMustEqualTo, rangeTest.toString());
	}


}
