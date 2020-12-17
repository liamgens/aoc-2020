package com.aoc.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day2 {

    public HashMap<String, String> parseLine(String line) {
        HashMap<String, String> tokens = new HashMap<>();

        String[] removedSpaces = line.split(" ");
        tokens.put("password", removedSpaces[2]);

        String[] range = removedSpaces[0].split("-");
        tokens.put("min", range[0]);
        tokens.put("max", range[1]);

        tokens.put("letter", removedSpaces[1].substring(0,1));
        return tokens;
    }

    public boolean validCharacters(String letter, String password, int min, int max) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (letter.charAt(0) == password.charAt(i)) {
                count++;
            }
        }
        return count >= min && count <= max;
    }

    public boolean onlyOneCharacter(String letter, String password, int position1, int position2) {
        return password.charAt(position1 - 1) == letter.charAt(0) ^ password.charAt(position2 - 1) == letter.charAt(0);
    }

    public ArrayList<HashMap<String, String>> tokenizeLines(ArrayList<String> lines) {
        ArrayList<HashMap<String, String>> tokenizedLines = new ArrayList<>();
        for (String line : lines) {
            tokenizedLines.add(parseLine(line));
        }
        return tokenizedLines;
    }

    public int part1(ArrayList<String> lines) {
        ArrayList<HashMap<String, String>> tokenizedLines = tokenizeLines(lines);

        int count = 0;
        for (HashMap<String, String> tokens : tokenizedLines) {
            if (validCharacters(tokens.get("letter"), tokens.get("password"), Integer.parseInt(tokens.get("min")), Integer.parseInt(tokens.get("max")))) {
                count++;
            }
        }
        return count;
    }

    public int part2(ArrayList<String> lines) {
        ArrayList<HashMap<String, String>> tokenizedLines = tokenizeLines(lines);

        int count = 0;
        for (HashMap<String, String> tokens : tokenizedLines) {
            if (onlyOneCharacter(tokens.get("letter"), tokens.get("password"), Integer.parseInt(tokens.get("min")), Integer.parseInt(tokens.get("max")))) {
                count++;
            }
        }
        return count;
    }
}
