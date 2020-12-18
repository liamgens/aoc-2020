package com.aoc.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day4 {

    private ArrayList<HashMap<String, String>> passports;

    public Day4(ArrayList<String> lines) {
        ArrayList<String> passportStrings = parseInput(lines);
        this.passports = new ArrayList<>();

        for (String p : passportStrings) {
            this.passports.add(stringToPassport(p));
        }
    }

    private ArrayList<String> parseInput(ArrayList<String> lines) {
        String passport = "";
        ArrayList<String> passports = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                passports.add(passport);
                passport = "";
            } else {
                passport = passport.concat(line).concat(" ");
            }
        }
        passports.add(passport);

        return passports;
    }

    private HashMap<String, String> stringToPassport(String s) {
        HashMap<String, String> passport = new HashMap<>();
        String[] fields = s.split("\\s|:");

        for (int i = 0; i < fields.length; i+=2) {
            passport.put(fields[i], fields[i+1]);
        }

        return passport;
    }

    private boolean validatePassport(HashMap<String, String> passport, boolean validateFields) {
        final HashMap<String, String> requiredFields = new HashMap<>(){{
                put("byr", "^(19[2-9][0-9]|200[0-2])$");
                put("iyr", "^(201[0-9]|2020)$");
                put("eyr", "^(202[0-9]|2030)$");
                put("hgt", "^(1[5-8][0-9]cm|19[0-3]cm|59in|6[0-9]in|7[0-6]in)$");
                put("hcl", "^#[0-9a-f]{6}$");
                put("ecl", "^(amb|blu|brn|gry|grn|hzl|oth)$");
                put("pid", "^[0-9]{9}$");
        }};
        for (Map.Entry<String, String> requirement : requiredFields.entrySet()) {
            String field = requirement.getKey();
            if (!passport.containsKey(field) || (validateFields && !passport.get(field).matches(requirement.getValue()))) {
                return false;
            }
        }
        return true;
    }

    private int validPassportCount(boolean validateFields) {
        int validPassports = 0;
        for (HashMap<String, String> passport : this.passports) {
            if(validatePassport(passport, validateFields)) {
                validPassports++;
            }
        }
        return validPassports;
    }

    public int part1() {
        return validPassportCount(false);
    }

    public int part2() {
        return validPassportCount(true);
    }
}
