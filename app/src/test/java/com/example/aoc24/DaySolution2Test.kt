package com.example.aoc24

import com.example.solutions.DaySolution2
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution2Test {
    private val solution = DaySolution2(TestLogger())

    @Test
    fun testPart1() = runTest {
        input1.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result1, solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input2.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result2, solution.part2.obtainResult())
    }

    private val input1 =
        """""".split("\n")


    private val result1 = "11"

    private val input2 =
        """41226   69190""".split("\n")

    private val result2 = "2"
}