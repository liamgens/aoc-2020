package com.aoc.day6;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day6 {

    private ArrayList<HashSet<Character>> groups;
    private ArrayList<HashSet<Character>> sameAnswerGroups;

    public Day6(ArrayList<String> lines) {
        this.groups = new ArrayList<>();
        this.sameAnswerGroups = new ArrayList<>();
        parseInput(lines);
    }

    private void parseInput(ArrayList<String> lines) {
        HashSet<Character> currentGroup = new HashSet<>();
        ArrayList<HashSet<Character>> currentGroupAnswerSets = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                this.groups.add(currentGroup);
                currentGroup = new HashSet<>();

                // take set intersection here
                HashSet<Character> intersection = currentGroupAnswerSets.get(0);
                for (HashSet<Character> currentSet : currentGroupAnswerSets) {
                    intersection.retainAll(currentSet);
                }
                this.sameAnswerGroups.add(intersection);
                currentGroupAnswerSets = new ArrayList<>();

            } else {
                HashSet<Character> person = new HashSet<>();
                for (int i = 0; i < line.length(); i++) {
                    currentGroup.add(line.charAt(i));
                    person.add(line.charAt(i));
                }
                currentGroupAnswerSets.add(person);
            }
        }

        HashSet<Character> intersection = currentGroupAnswerSets.get(0);
        for (HashSet<Character> currentSet : currentGroupAnswerSets) {
            intersection.retainAll(currentSet);
        }
        this.sameAnswerGroups.add(intersection);

        this.groups.add(currentGroup);
    }

    private int getTotalGroupCount() {
        int count = 0;
        for(HashSet<Character> group : this.groups) {
            count += group.size();
        }
        return count;
    }

    public int part1() {
        return getTotalGroupCount();
    }

    private int getTotalGroupSameAnswersCount() {
        int count = 0;
        for(HashSet<Character> group : this.sameAnswerGroups) {
            count += group.size();
        }
        return count;
    }

    public int part2() {
        return getTotalGroupSameAnswersCount();
    }
}
