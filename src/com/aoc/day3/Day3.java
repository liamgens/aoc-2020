package com.aoc.day3;

import java.util.ArrayList;

public class Day3 {
    private int width;
    private int height;
    private ArrayList<ArrayList<Boolean>> map;

    public Day3 (ArrayList<String> lines) {
        setMap(lines);
        this.height = map.size();
        this.width = map.get(0).size();
    }

    private void setMap (ArrayList<String> lines) {
        this.map = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            ArrayList<Boolean> row = new ArrayList<>();
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                boolean tree = line.charAt(x) == '#';
                row.add(tree);
            }
            this.map.add(row);
        }
    }

    private int getSudoX (int x) {
        while (x >= this.width) {
            x -= this.width;
        }
        return x;
    }

    private boolean containsTree (int x, int y) {
        x = getSudoX(x);
        return this.map.get(y).get(x);
    }

    private long calculateTrees(int slopeX, int slopeY) {
        int x = 0;
        int y = 0;
        int treeCount = 0;
        while(y < this.height) {
            if (containsTree(x,y)) {
                treeCount++;
            }
            x += slopeX;
            y += slopeY;
        }
        return treeCount;
    }

    public long part1 () {
        return calculateTrees(3, 1);
    }

    public long part2 () {
        return (calculateTrees(1, 1) *
                calculateTrees(3, 1) *
                calculateTrees(5, 1) *
                calculateTrees(7, 1) *
                calculateTrees(1, 2));
    }
}
