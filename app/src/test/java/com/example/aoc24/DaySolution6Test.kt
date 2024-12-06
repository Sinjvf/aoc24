package com.example.aoc24

import com.example.solutions.DaySolution6
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution6Test {
    private val solution = DaySolution6(TestLogger())

    @Test
    fun testPart1() = runTest {
        input.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals("41", solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals("6", solution.part2.obtainResult())
    }


    private val input = """....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...""".trimMargin().split("\n")


    private val result = "11"

}