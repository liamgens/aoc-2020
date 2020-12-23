package com.aoc.Day12

import kotlin.math.*

class Day12(lines: ArrayList<String>) {
    private val directions: ArrayList<Pair<Char, Int>> = ArrayList()
    private var waypointX: Int = 10
    private var waypointY: Int = 1

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

    private fun performDirections(withWaypoint: Boolean = false): Int {
        var x = 0
        var y = 0
        /* direction mapping
        *     0
        *  3  x  1
        *     2
        * */
        var facing = 1
        for (dir in directions) {
            val newDir = performDirection(x, y, dir, facing, withWaypoint)
            x = newDir.first
            y = newDir.second
            facing = newDir.third
        }

        return manhattanDistance(0, 0, x, y)
    }

    private fun performDirection(x: Int, y: Int, dir: Pair<Char, Int>, facing: Int, withWaypoint: Boolean = false): Triple<Int, Int, Int> {
        var newX = x
        var newY = y
        var newFacing = facing

        val units = dir.second
        when (dir.first) {
            'N' -> if (withWaypoint) waypointY += units else newY += units
            'S' -> if (withWaypoint) waypointY -= units else newY -= units
            'E' -> if (withWaypoint) waypointX += units else newX += units
            'W' -> if (withWaypoint) waypointX -= units else newX -= units
            'F' -> {
                if (withWaypoint) {
                    newX += (waypointX.toInt() * units)
                    newY += (waypointY.toInt() * units)
//                    waypointX = newX + 10.0
//                    waypointY = newY + 1.0
                } else {
                    when (facing) {
                        0 -> newY += units
                        1 -> newX += units
                        2 -> newY -= units
                        3 -> newX -= units
                    }
                }
            }
            'R' -> {
                if (withWaypoint) {
                    val newPoint = rotatePoint(waypointX, waypointY, 360-units)
                    waypointX = newPoint.first
                    waypointY = newPoint.second
                } else {
                    newFacing = (facing + (units / 90)) % 4
                }
            }
            'L' -> {
                if (withWaypoint) {
                    val newPoint = rotatePoint(waypointX, waypointY, units)
                    waypointX = newPoint.first
                    waypointY = newPoint.second
                } else {
                    newFacing = (facing - (units / 90)) % 4
                    if (newFacing < 0) newFacing += 4
                }
            }
        }

        return Triple(newX, newY, newFacing)
    }

    private fun manhattanDistance(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        return kotlin.math.abs(x1 - x2) + kotlin.math.abs(y1 - y2)
    }

    private fun rotatePoint(x: Int, y: Int, degrees: Int): Pair<Int, Int> {
        val radians = (degrees * (PI /180))
        val xP = round(x * cos(radians)) - (y * sin(radians)).roundToInt()
        val yP = round(y * cos(radians)) + (x * sin(radians)).roundToInt()
        return Pair(xP.toInt(), yP.toInt())
    }

    fun part1() : Int{
        return performDirections()
    }

    fun part2() : Int{
        return performDirections(true)
    }

}