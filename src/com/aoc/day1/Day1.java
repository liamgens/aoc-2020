package com.aoc.day1;

import java.util.ArrayList;
import java.util.HashSet;

public class Day1 {

    public HashSet<Integer> cleanInput(ArrayList<String> lines) {
        HashSet<Integer> numbers = new HashSet<>();
        for (String line : lines) {
            numbers.add(Integer.parseInt(line));
        }
        return numbers;
    }

    public int solve(HashSet<Integer> numbers, int operands) {
        return solve(2020, numbers, operands, 1);
    }

    public int solve(int sum, HashSet<Integer> numbers, int operands, int total) {
        int answer = -1;
        for (int num : numbers) {
            int diff = sum - num;

            if (numbers.contains(diff) && operands == 2) {
                return num * diff * total;
            } else if (diff >= 0 && operands > 2) {
                HashSet<Integer> numbersCopy = new HashSet<>(numbers);
                numbersCopy.remove(num);
                if (answer == -1) {
                    answer = solve(diff, numbersCopy, operands - 1, num * total);
                }
            }
        }
        return answer;
    }

    public int part1 (ArrayList < String > lines) {
        HashSet<Integer> numbers = cleanInput(lines);
        return solve(numbers, 2);
    }


    public int part2(ArrayList<String> lines) {
        HashSet<Integer> numbers = cleanInput(lines);
        return solve(numbers, 3);
    }
}
