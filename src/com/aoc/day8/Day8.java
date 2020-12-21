package com.aoc.day8;

import java.util.ArrayList;

public class Day8 {
    private ArrayList<Instruction> instructions;
    private ArrayList<Instruction> run;
    private int accumlator;
    private int pointer;

    public Day8(ArrayList<String> lines) {
        this.accumlator = 0;
        this.pointer = 0;
        this.instructions = new ArrayList<>();
        this.run = new ArrayList<>();
        this.setInstructions(lines);
    }

    private void setInstructions(ArrayList<String> lines) {
        for (String line : lines) {
            String[] parsed = line.split(" ");
            instructions.add(new Instruction(parsed[0], Integer.parseInt(parsed[1])));
        }
    }

    private void performInstruction(Instruction inst) {
        switch (inst.getInst()) {
            case "acc" -> {
                this.accumlator += inst.getValue();
                this.pointer++;
            }
            case "jmp" -> this.pointer += inst.getValue();
            case "nop" -> this.pointer++;
        }
        this.run.add(inst);
    }

    private void performInstructions() {
        boolean done = false;
        while (!done) {
            Instruction inst = this.instructions.get(this.pointer);
            if (this.run.contains(inst)) {
                done = true;
            } else {
                performInstruction(inst);
            }
        }
    }

    private boolean runsCorrectly() {
        this.accumlator = 0;
        this.pointer = 0;
        this.run = new ArrayList<>();

        while (true) {
            Instruction inst = this.instructions.get(this.pointer);
            if (this.run.contains(inst)) {
                return false;
            } else {
                performInstruction(inst);
                if (this.instructions.get(this.instructions.size() - 1) == inst) {
                    return true;
                }
            }
        }
    }

    private void changeInstruction() {
        for (Instruction i : this.instructions) {
            if (i.getInst().equals("jmp")) {
                i.setInst("nop");
                if (runsCorrectly()) {
                    break;
                } else {
                    i.setInst("jmp");
                }
            } else if (i.getInst().equals("nop")) {
                i.setInst("jmp");
                if (runsCorrectly()) {
                    break;
                } else {
                    i.setInst("nop");
                }
            }
        }
    }

    public int part1() {
        performInstructions();
        return this.accumlator;
    }

    public int part2() {
        changeInstruction();
        return this.accumlator;
    }
}
