package com.example.aoc24

import com.example.solutions.DaySolution22
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution22Test {
    private val solution = DaySolution22(TestLogger())

    @Test
    fun testPart1() = runTest {
        input22.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result, solution.part1.obtainResult())
    }

    @Test
    fun testPart2(){
        input22.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result, solution.part2.obtainResult())
    }

    private val input1 = """1
10
100
2024""".trimMargin().split("\n")


    private val input0 = """123""".trimMargin().split("\n")

    private val result = "11"
}