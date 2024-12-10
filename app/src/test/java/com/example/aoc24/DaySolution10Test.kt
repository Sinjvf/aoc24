package com.example.aoc24

import com.example.solutions.DaySolution10
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution10Test {
    private val solution = DaySolution10(TestLogger())

    @Test
    fun testPart1() = runTest {
        input10.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result, solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input10.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result, solution.part2.obtainResult())
    }

    private val input = """89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732""".trimMargin().split("\n")


    private val result = "11"

}