package com.example.aoc24

import com.example.solutions.DaySolution11
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution11Test {
    private val solution = DaySolution11(TestLogger())

    private val BLINKING = 75
    @Test
    fun testPart1() = runTest {
        input.forEachIndexed { id, str -> solution.part1.handleLine(str, BLINKING) }
        solution.part1.finish()
        assertEquals(result, solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input2.forEachIndexed { id, str -> solution.part2.handleLine(str, BLINKING) }
        solution.part2.finish()
        assertEquals(result, solution.part2.obtainResult())
    }
    private val input = """125 17""".trimMargin().split("\n")

    private val input2 = """773 79858 0 71 213357 2937 1 3998391""".trimMargin().split("\n")


    private val result = "11"

}