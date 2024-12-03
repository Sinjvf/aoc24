package com.example.aoc24

import com.example.solutions.DaySolution3
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution3Test {
    private val solution = DaySolution3(TestLogger())

    @Test
    fun testPart1() = runTest {
        input3.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result, solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input3.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result, solution.part2.obtainResult())
    }

    private val input =
        """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))""".split("\n")


    private val result = "11"

}