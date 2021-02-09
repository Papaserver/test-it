# Histogram

## Story

A histogram is an approximate, graphical representation of the distribution of numerical
or categorical data. Simply, it represents data using bars of different heights.
In a histogram, each bar group numbers into ranges. Taller bars show that more data falls
in that range. A histogram displays the shape and spread of continuous sample data.
To construct it, the first step is to get the range of values and then counting it occurrence
in your data.

For example, let's take below text and make histogram:

Harry Potter and the Sorcerer's Stone
Mr. and Mrs. Dursley, of number four, Privet Drive,
were proud to say that they were perfectly normal, thank you very much.

```
1  - 3 | *********
4  - 6 | ****************
7  - 9 | ***
```

See the above, there are 9 words in the range 1-3 letters count,
16 words in range 4-6, and 3 in the range 7  - 9. The punctuation signs are not included.

We need to ensure, that program which is already implemented will collect all data into histogram
correctly. You know, nobody like charts which shows incorrect data. So, let’s implement
unit tests. You should make your tests shine and ensure that they pay off more than they cost.
In this project, your job is to cover your code with tests, and make an assertion for all
the statements in the bullet points.

## What are you going to learn?

You'll have to:

- writing unit tests,
- developing boundary conditions,
- handling exceptions and covering them with tests,
- improving code to more efficient based on tests result.

## Tasks

1. There are separated files for testing purposes isolated from production code.
    - There are test files containing edge cases in `test > resources` directory.
    - `BeforeEach`, `BeforeAll`, `AfterEach`, `AfterAll` annotations are used properly. e.g. if a class needs to be instantiated before every test method then it is inide a `BeforeEach` annotated method

2. In `TextReader` class we have read method which returns `String` from the read file. Cover that method with tests, by implementing the `TextReaderTest` class.
    - When we provide the wrong file name, then we expect `FileNotFoundException` to be thrown.
    - Provide test cases for `read()` method. The following cases should be covered: - existing but empty files - one line text in text file - multiple line in text file

3. `Range` class is covered with tests, by implementing the `RangeTest` class.
    - Provide test cases for constructor. The following cases should be covered:
- `from < 0`
- `to < from`
    - Provide test cases for `isInRange()`. The following cases should be covered: - word's length in range - word's length equals to range `from` - word's length equals to range `to` - word's length is out of range
    - Provide test case for a `toString()` method

4. `Histogram` class is covered with tests, by implementing the `HistogramTest` class.
    - Provide test cases for `generateRanges()`. The following cases should be covered: - positive integers added as parameters - negative integer as `step` parameter - negative integer as `amount` parameter
    - Provide test cases for `generate()`. The method returns with counts of words with length in given ranges as a `HashMap`. The following cases should be covered: - all words in `text` is in one of the given `ranges` - `text` with words out of given ranges - empty `text` - null as `text` - null as `ranges` - generate histogram multiple times
    - Provide test cases for `getHistogram()` method. The following case should be covered: - Calling before generate histogram - Calling after generate histogram - Calling after multiple calls of `generateHistogram()`
    - String representation of histogram should present specific ranges
and their values displayed with proper asterisk repetition.
The following cases should be covered:
- Histogram before generate
- Histogram after generate

5. Min-max normalization, is the simplest method and consists in rescaling the range of features to
scale the range in [0, 1] or [−1, 1]. For the purpose of better diagram readability, we want to
scale words length ranges from 0 to 100. Please implement such a method in Histogram class, so
the max value on histogram could be less or equal than 100.
    - Get max value from histogram
    - Get min value from histogram
    - New value is calculated by given formula
`V' = (V - min) * 100 / (max - min)`.
Here is an example:
Assuming that for ranges max value is 115, and min 7, based on this if we
have 25 words in the range of 4-6 characters, we should get value 16 after
normalizing this range (fraction part in value result is not considered,
so we take an integer value)

6. Update the `HistogramTest` class to cover methods written in the previous step.
    - The `getMin()` and `getMax()` methods should return values accordingly.
    - String representation of histogram should present specific ranges
and their values from 0 to 100, represented by asterisk repetition

7. Try to optimize code and find a way to improve tests efficiency.
    - There is no unnecessary code repetition in test methods
    - Tests execution time is as short as possible
    - Test coverage of `Histogram`, `TextReader` and `Range` classes are 100%

## General requirements

- Every test methods' name follows the same naming convention.
In case of failing test, based on its name it is straightforward
which method, in which scenario has broken and what would be the expected behavior.
- The different assertion methods are used as intended. e.g. in case of examining a boolean value not `assertEquals(true, value)` is used  but `assertTrue(value)` and so forth..

## Hints

- When you test if a given structure, list, map etc. contains the right object,
  you need to  to override both `equals` and `hashCode`.
- In IntelliJ you can run test, and check the coverage by clicking `Run > Run with Coverage`.
  You will be able to see percentile test coverage of classes, methods, and lines.
- There are different naming standards for unit tests as you can see in background materials.
  We suggest to choose one which is more descriptive and specific enough to easily detect
  where can be a certain issue. e.g. `MethodName_StateUnderTest_ExpectedBehavior`


## Background materials

- <i class="far fa-exclamation"></i> [Software testing](project/curriculum/materials/pages/general/software-testing.md)
- <i class="far fa-exclamation"></i> [Positive test or negative test](https://stackoverflow.com/questions/8162423)
- <i class="far fa-exclamation"></i> [IDEA - JUnit step-by-step Guide](project/curriculum/materials/pages/tools/idea-junit-step-by-step-guide.md)
- <i class="far fa-exclamation"></i> [JUnit Introduction](project/curriculum/materials/pages/java/junit-introduction.md)
- <i class="far fa-exclamation"></i> [Unit test naming conventions](https://dzone.com/articles/7-popular-unit-test-naming)
- <i class="far fa-book-open"></i> [BeforeEach and BeforeAll](https://www.baeldung.com/junit-before-beforeclass-beforeeach-beforeall#beforeeach-and-beforeall)
- <i class="far fa-candy-cane"></i> [Order of tests in JUnit](https://www.baeldung.com/junit-5-test-order)

