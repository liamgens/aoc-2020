package com.aoc.Day11;

import java.util.ArrayList;
import java.util.Arrays;

public class Day11 {

    private char[][] seatingArrangement;
    private char[][] newSeatingArrangement;

    public Day11 (ArrayList<String> lines) {
        seatingArrangement = new char[lines.size()][lines.get(0).length()];
        populateSeats(lines);
        newSeatingArrangement = Arrays.stream(seatingArrangement).map(char[]::clone).toArray(char[][]::new);
    }

    private void populateSeats(ArrayList<String> lines) {
        for (int k = 0; k < lines.size(); k++) {
           for (int i = 0; i < lines.get(k).length(); i++) {
               seatingArrangement[k][i] = lines.get(k).charAt(i);
           }
        }
    }

    private void printSeatingArrangement() {
        for (char[] chars : seatingArrangement) {
            System.out.println(chars);
        }
    }


    private void printNewSeatingArrangement() {
        for (char[] chars : newSeatingArrangement) {
            System.out.println(chars);
        }
    }

    private void changeSeatStates() {
        for (int row = 0; row < seatingArrangement.length; row++) {
            for (int col = 0; col < seatingArrangement[row].length; col++) {
                if (seatingArrangement[row][col] != '.') {
                    changeSeatState(row, col);
                }
            }
        }
    }

    private void changeSeatState(int row, int col) {
        if (adjacentOccupiedCount(row, col) >= 4) {
            // leave seat
            newSeatingArrangement[row][col] = 'L';
        } else if (adjacentOccupiedCount(row, col) == 0) {
            // occupy seat
            newSeatingArrangement[row][col] = '#';
        }
    }

    private boolean isSeatOccupied(int row, int col) {
        return seatingArrangement[row][col] == '#';
    }

    private int adjacentOccupiedCount(int row, int col) {
        int count = 0;
        boolean firstRow = row == 0;
        boolean lastRow = row == seatingArrangement.length - 1;
        boolean firstCol = col == 0;
        boolean lastCol = col == seatingArrangement[row].length - 1;

        if (!firstRow && isSeatOccupied(row-1, col)) count++;
        if (!firstRow && !firstCol && isSeatOccupied(row-1, col-1)) count++;
        if (!firstRow && !lastCol && isSeatOccupied(row-1, col+1)) count++;

        if (!lastRow && isSeatOccupied(row+1, col)) count++;
        if (!lastRow && !firstCol && isSeatOccupied(row+1, col-1)) count++;
        if (!lastRow && !lastCol && isSeatOccupied(row+1, col+1)) count++;

        if(!firstCol && isSeatOccupied(row, col-1)) count++;
        if(!lastCol && isSeatOccupied(row, col+1)) count++;

        return count;
    }

    private int countOccupiedSeats() {
        int count = 0;

        for (char[] chars : seatingArrangement) {
            for (char aChar : chars) {
                if (aChar == '#') {
                    count++;
                }
            }
        }

        return count;
    }

    public int part1() {
        changeSeatStates();
        while (!Arrays.deepEquals(seatingArrangement, newSeatingArrangement)) {
            seatingArrangement = Arrays.stream(newSeatingArrangement).map(char[]::clone).toArray(char[][]::new);
            changeSeatStates();
        }
        return countOccupiedSeats();
    }
}
