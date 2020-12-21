package com.aoc.day10;

import java.util.ArrayList;
import java.util.Collections;

public class Day10 {

    private ArrayList<Integer> adapters;
    private long totall;

    public Day10(ArrayList<String> lines) {
        adapters = new ArrayList<>();
        adapters.add(0);
        lines.forEach((line) -> adapters.add(Integer.parseInt(line)));
        adapters.sort(null);
        totall = 1;
    }

    private int calculateDifferences() {
        int diff1 = 0;
        int diff3 = 1;
        for(int i = 0; i < adapters.size() - 1; i++) {
            int diff = adapters.get(i + 1) - adapters.get(i);
            if (diff == 1) {
                diff1++;
            } else if (diff == 3) {
                diff3++;
            }
        }
        return diff1 * diff3;
    }

    private ArrayList<Integer> availableAdapters(int adapter) {
        ArrayList<Integer> availableAdapters = new ArrayList<>();

        for (int i = adapter + 1; i <= adapter + 3; i++) {
            if (adapters.contains(i)) {
                availableAdapters.add(i);
            }
        }
        return availableAdapters;
    }

    private int calculateCombinations(int a, ArrayList<Integer> adaptersToCalculate) {
        if (a == Collections.max(adapters)) {
            return 1;
        }

        int total = 0;
        for (int adapter : adaptersToCalculate) {
            total += calculateCombinations(adapter, availableAdapters(adapter));
        }

        return total;
    }

    public int part1() {
        return calculateDifferences();
    }

    public int part2() {
        ArrayList<Integer> x = new ArrayList<>();
        x.add(0);
        int a = calculateCombinations(0, x);
        System.out.println(this.totall);

        return a;

    }
}
