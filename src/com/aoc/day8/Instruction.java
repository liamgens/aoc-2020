package com.aoc.day8;

public class Instruction {
    public void setInst(String inst) {
        this.inst = inst;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private String inst;
    private int value;

    public Instruction(String inst, int value) {
        this.inst = inst;
        this.value = value;
    }

    public String getInst() {
        return inst;
    }

    public int getValue() {
        return value;
    }
}
