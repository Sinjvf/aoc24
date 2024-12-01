package com.example.aoc24

import com.example.solutions.DaySolution1
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution1Test {
    private val solution = DaySolution1(TestLogger())

    @Test
    fun testPart1() = runTest {
        input1.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result1, solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input1.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result2, solution.part2.obtainResult())
    }

/*    private val input1 =
        """3   4
4   3
2   5
1   3
3   9
3   3""".split("\n")*/


    private val result1 = "11"



    private val result2 = "2"
}