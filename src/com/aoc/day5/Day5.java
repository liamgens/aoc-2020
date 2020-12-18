package com.aoc.day5;

import java.util.ArrayList;
import java.util.HashSet;

public class Day5 {
    private ArrayList<String> seatings;

    public Day5(ArrayList<String> lines) {
        this.seatings = lines;
    }

    private double[] findRowAndSeat(String seating, double rowStart, double rowEnd, double seatStart, double seatEnd) {
        if (rowStart == rowEnd && seatStart == seatEnd) {
            double seatId = rowStart * 8 + seatEnd;
            return new double[]{ rowStart, seatStart, seatId};
        }
        double rowOffset = Math.ceil((rowEnd - rowStart) / 2);
        double seatOffset = Math.ceil((seatEnd - seatStart) / 2);
        if (seating.charAt(0) == 'F') {
            rowEnd -= rowOffset;
        } else if (seating.charAt(0) == 'B') {
            rowStart += rowOffset;
        } else if (seating.charAt(0) == 'L') {
            seatEnd -= seatOffset;
        } else if (seating.charAt(0) == 'R') {
            seatStart += seatOffset;
        }
        return findRowAndSeat(seating.substring(1), rowStart, rowEnd, seatStart, seatEnd);
    }

    private double highestSeatId(ArrayList<String> seatings) {
        double highSeatId = -1;
        for(String seating : seatings) {
            double seatId = findRowAndSeat(seating, 0, 127, 0, 7)[2];

            if (seatId > highSeatId) {
                highSeatId = seatId;
            }
        }
        return highSeatId;
    }

    private double findAvailableSeat() {
        HashSet<Double> takenSeats = new HashSet<>();
        for(String seating : this.seatings) {
            double seatId = findRowAndSeat(seating, 0, 127, 0, 7)[2];
            takenSeats.add(seatId);
        }

        for(double row = 0; row < 128; row++) {
            for(double seat = 0; seat < 8; seat++) {
                double seatId = row * 8 + seat;
                if (takenSeats.contains(seatId)){
                    continue;
                }
                double previousSeat = seat - 1;
                double previousRow = row;
                double nextRow = row;
                double nextSeat = seat + 1;

                if (seat == 0) {
                    previousSeat = 7;
                    previousRow--;
                } else if (seat == 7) {
                    nextRow++;
                    nextSeat = 0;
                }

                double previousSeatId = previousRow * 8 + previousSeat;
                double nextSeatId = nextRow * 8 + nextSeat;

                if (takenSeats.contains(previousSeatId) && takenSeats.contains(nextSeatId)) {
                    return seatId;
                }

            }
        }

        return -1;
    }

    public double part1() {
        return highestSeatId(this.seatings);
    }

    public double part2() {
        return findAvailableSeat();
    }
}
