package com.aoc.Day12

class Day12(lines: ArrayList<String>) {
    private val directions: ArrayList<Pair<Char, Int>> = ArrayList()

    init {
        parseLines(lines)
    }

    private fun parseLines(lines: ArrayList<String>) {
        for (line in lines) {
            val dir = line[0]
            val unit = line.substring(1).toInt()
            directions.add(Pair(dir, unit))
        }
    }

    private fun performDirections(): Int {
        var x = 0
        var y = 0
        /* direction mapping
        *     0
        *  3  x  1
        *     2
        * */
        var facing = 1
        for (dir in directions) {
            val newDir = performDirection(x, y, dir, facing)
            x = newDir.first
            y = newDir.second
            facing = newDir.third
        }

        return manhattanDistance(0, 0, x, y)
    }

    private fun performDirection(x: Int, y: Int, dir: Pair<Char, Int>, facing: Int): Triple<Int, Int, Int> {
        var newX = x
        var newY = y
        var newFacing = facing

        val units = dir.second
        when (dir.first) {
            'N' -> newY += units
            'S' -> newY -= units
            'E' -> newX += units
            'W' -> newX -= units
            'F' -> {
                when (facing) {
                    0 -> newY += units
                    1 -> newX += units
                    2 -> newY -= units
                    3 -> newX -= units
                }
            }
            'R' -> {
                newFacing = (facing + (units / 90)) % 4
            }
            'L' -> {
                newFacing = (facing - (units / 90)) % 4
                if (newFacing < 0) newFacing += 4
            }
        }
//        println("$dir -> ($newX, $newY) $newFacing ${manhattanDistance(0, 0, newX, newY)}")
        if (dir.first == 'L' || dir.first == 'R')
            println("x: $newX y: $newY $dir, $newFacing")


        return Triple(newX, newY, newFacing)
    }

    private fun manhattanDistance(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        return kotlin.math.abs(x1 - x2) + kotlin.math.abs(y1 - y2)
    }

    fun part1() : Int{
        return performDirections()
    }

}