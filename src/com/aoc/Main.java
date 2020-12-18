package com.aoc;

import com.aoc.day1.Day1;
import com.aoc.day2.Day2;
import com.aoc.day3.Day3;

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
        ArrayList<String> lines = m.parseFile(3);
        Day3 day3 = new Day3(lines);
        System.out.println(day3.part1());
        System.out.println(day3.part2());

    }
}
