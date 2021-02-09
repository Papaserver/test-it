package com.codecool.histogram;

import java.util.Objects;

/**
 * The Range class represents numbers between upper and lower limits.
 */
public class Range {
    private int from, to;

    /**
     * Constructs a Range with specified lower and upper limit.
     * Lower and upper limits cannot be smaller than 0.
     * @param from lower limit
     * @param to upper limit
     */
    public Range(int from, int to) {
        if (from < 0 || to < from) {
            throw new IllegalArgumentException();
        }

        this.from = from;
        this.to = to;
    }

    /**
     * Returns that the given word belongs to the particular range or not.
     * @param word text to investigate
     * @return The word belongs to the particular range or not.
     */
    public boolean isInRange(String word) {
        return word.length() >= from && word.length() <= to;
    }

    public String toString() {
        return String.format("%-2d - %-2d", from, to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return from == ((Range) o).from && to == ((Range) o).to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
