package com.example.aoc24

import com.example.solutions.DaySolution21
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution21Test {
    private val solution = DaySolution21(TestLogger())

    @Test
    fun testPart1() {
        input1.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result, solution.part1.obtainResult())
    }

    @Test
    fun testPart2()  {
        input21.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result, solution.part2.obtainResult())
    }

    private val input211 = """02""".trimMargin().split("\n")

    private val input2 = """029A""".trimMargin().split("\n")

    private val input3 = """456A""".trimMargin().split("\n")

    private val input1 = """029A
980A
179A
456A
379A""".trimMargin().split("\n")



    private val result = "11"
}