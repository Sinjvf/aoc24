package com.example.aoc24

import com.example.solutions.DaySolution2
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution2Test {
    private val solution = DaySolution2(TestLogger())

    @Test
    fun testPart1() = runTest {
        input2.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result1, solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input2.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result1, solution.part2.obtainResult())
    }

    private val input1 =
        """68 66 67 69 72 73 76""".split("\n")


    private val result1 = "2"

}