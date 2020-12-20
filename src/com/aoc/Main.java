package com.aoc;

import com.aoc.day1.Day1;
import com.aoc.day2.Day2;
import com.aoc.day3.Day3;
import com.aoc.day4.Day4;
import com.aoc.day5.Day5;
import com.aoc.day6.Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public ArrayList<String> parseFile(int day) {
        ArrayList<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(String.format("resources/day%d.txt", day)))) {
            while (br.ready()) {
                result.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        Main m = new Main();
        ArrayList<String> lines = m.parseFile(6);
        Day6 day6 = new Day6(lines);
        System.out.println(day6.part1());
        System.out.println(day6.part2());

    }
}
