package com.aoc;

import com.aoc.day8.Day8;
import com.aoc.day9.Day9;

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
        ArrayList <String> lines = m.parseFile(9);
        Day9 day8 = new Day9(lines);
        System.out.println(day8.part1());
        System.out.println(day8.part2());
    }
}
