package com.aoc.day9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9 {
    private ArrayList<Long> numbers;
    private final int PREAMBLE = 25;

    public Day9(ArrayList<String> lines) {
        numbers = new ArrayList<>();

        for (String line : lines) {
            numbers.add(Long.parseLong(line));
        }
    }

    private boolean isValid(long number, int index) {
        List<Long> validNumbers = numbers.subList(index-PREAMBLE, index);
        for (int i = 0; i < validNumbers.size(); i++) {
            for (int k = i + 1; k < validNumbers.size(); k++) {
                long num1 = validNumbers.get(i);
                long num2 = validNumbers.get(k);

                if (num1 != num2 && num1 != number && num2 != number) {
                    if (validNumbers.get(i) + validNumbers.get(k) == number) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private long findFirstInvalid() {
        for (int i = PREAMBLE; i < numbers.size(); i++) {
            if (!isValid(numbers.get(i), i)) {
                return numbers.get(i);
            }
        }
        return 0;
    }

    private long listSum(List<Long> list) {
         return list.stream().mapToLong(Long::longValue).sum();
    }

    private long findContiguousSum(long sumTo) {
        for (int i = 0; i < numbers.size(); i++) {
            for (int k = i+1; k < numbers.size(); k++) {
                List<Long> range = numbers.subList(i, k);
                if (listSum(range) == sumTo) {
                    long max = Collections.max(range);
                    long min = Collections.min(range);
                    return max + min;
                }
            }
        }
        return 0;
    }

    public long part1() {
        return findFirstInvalid();
    }

    public long part2() {
        long invalid = findFirstInvalid();
        return findContiguousSum(invalid);
    }
}
