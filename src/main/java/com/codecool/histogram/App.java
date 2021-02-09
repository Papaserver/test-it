package com.codecool.histogram;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {

        TextReader reader = new TextReader("src/main/resources/text.txt");
        String chapter;
        chapter = reader.read();

        Histogram histogram = new Histogram();

        System.out.println("words length histogram with generated ranges:");
        List<Range> ranges = histogram.generateRanges(3, 5);
        histogram.generate(chapter, ranges);
        System.out.println(histogram.toString());

        System.out.println("words length histogram with specified ranges:");
        histogram = new Histogram();
        ranges = Arrays.asList(new Range(0, 1), new Range(2, 3), new Range(4, 6), new Range(7, 10));
        histogram.generate(chapter, ranges);
        System.out.println(histogram.toString());

        System.out.println("words length normalized histogram with specified ranges:");
        histogram = new Histogram();
        ranges = Arrays.asList(new Range(0, 1), new Range(2, 3), new Range(4, 6), new Range(7, 10));
        histogram.generate(chapter, ranges);
        histogram.normalizeValues();
        System.out.println(histogram.toString());
    }
}
