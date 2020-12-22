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
        System.out.println(" ");
    }


    private void printNewSeatingArrangement() {
        for (char[] chars : newSeatingArrangement) {
            System.out.println(chars);
        }
        System.out.println(" ");
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

    private void changeSeatStates2() {
        for (int row = 0; row < seatingArrangement.length; row++) {
            for (int col = 0; col < seatingArrangement[row].length; col++) {
                if (seatingArrangement[row][col] != '.') {
                    changeSeatState2(row, col);
                }
            }
        }
        System.out.println(" ");
    }

    private void changeSeatState2(int row, int col) {
//        System.out.printf("(%d, %d): %d\n", row, col, visibleOccupiedCount(row, col));
        if (visibleOccupiedCount(row, col) >= 5) {
            // leave seat
            newSeatingArrangement[row][col] = 'L';
        } else if (visibleOccupiedCount(row, col) == 0) {
            // occupy seat
            newSeatingArrangement[row][col] = '#';
        }
    }


    private boolean isSeatOccupied(int row, int col) {
        return seatingArrangement[row][col] == '#';
    }

    private boolean isSeatEmpty(int row, int col) {
        return seatingArrangement[row][col] == 'L';
    }


    private int topOccupied(int row, int col) {
        while (row > 0) {
            if (isSeatOccupied(row-1, col)) {
                return 1;
            } else if (isSeatEmpty(row-1, col)) {
                return 0;
            }
            row--;
        }
        return 0;
    }

    private int bottomOccupied(int row, int col) {
        while (row < seatingArrangement.length - 1) {
            if (isSeatOccupied(row+1, col)) {
                return 1;
            } else if (isSeatEmpty(row+1, col)) {
                return 0;
            }
            row++;
        }
        return 0;
    }

    private int leftOccupied(int row, int col) {
        while (col > 0) {
            if (isSeatOccupied(row, col-1)) {
                return 1;
            } else if (isSeatEmpty(row, col-1)) {
                return 0;
            }
            col--;
        }
        return 0;
    }

    private int rightOccupied(int row, int col) {
        while (col < seatingArrangement[row].length - 1) {
            if (isSeatOccupied(row, col+1)) {
                return 1;
            } else if (isSeatEmpty(row, col+1)) {
                return 0;
            }
            col++;
        }
        return 0;
    }

    private int topRightOccupied(int row, int col) {
        while (col < seatingArrangement[row].length - 1 && row > 0) {
            if (isSeatOccupied(row-1, col+1)) {
                return 1;
            } else if (isSeatEmpty(row-1, col+1)) {
                return 0;
            }
            row--;
            col++;
        }
        return 0;
    }

    private int topLeftOccupied(int row, int col) {
        while (row > 0 && col > 0) {
            if (isSeatOccupied(row-1, col-1)) {
                return 1;
            } else if (isSeatEmpty(row-1, col-1)) {
                return 0;
            }
            row--;
            col--;
        }
        return 0;
    }

    private int bottomRightOccupied(int row, int col) {
        while (row < seatingArrangement.length - 1 && col < seatingArrangement[row].length - 1) {
            if (isSeatOccupied(row+1, col+1)) {
                return 1;
            } else if (isSeatEmpty(row+1, col+1)) {
                return 0;
            }
            row++;
            col++;
        }
        return 0;
    }

    private int bottomLeftOccupied(int row, int col) {
        while (row < seatingArrangement.length - 1 && col > 0 ) {
            if (isSeatOccupied(row+1, col-1)) {
                return 1;
            } else if (isSeatEmpty(row+1, col-1)) {
                return 0;
            }
            row++;
            col--;
        }
        return 0;
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

    private int visibleOccupiedCount(int row, int col) {
        return rightOccupied(row, col) + leftOccupied(row, col)
                + bottomOccupied(row, col) + topOccupied(row, col)
                + topLeftOccupied(row, col) + topRightOccupied(row, col)
                + bottomRightOccupied(row, col) + bottomLeftOccupied(row, col);
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

    public int part2() {
        changeSeatStates2();
        while (!Arrays.deepEquals(seatingArrangement, newSeatingArrangement)) {
            seatingArrangement = Arrays.stream(newSeatingArrangement).map(char[]::clone).toArray(char[][]::new);
            changeSeatStates2();
        }
        return countOccupiedSeats();
    }
}
