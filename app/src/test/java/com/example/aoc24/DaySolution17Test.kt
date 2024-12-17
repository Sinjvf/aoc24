package com.example.aoc24

import com.example.solutions.DaySolution17
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution17Test {
    private val solution = DaySolution17(TestLogger())

    @Test
    fun testPart1() = runTest {
        input6.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result, solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input17.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result, solution.part2.obtainResult())
    }

    private val input0 = """Register A: 0
Register B: 0
Register C: 9

Program: 2,6""".trimMargin().split("\n")

    private val input1 = """Register A: 729
Register B: 0
Register C: 0

Program: 0,1,5,4,3,0""".trimMargin().split("\n")

    private val input2 = """Register A: 10
Register B: 0
Register C: 0

Program: 5,0,5,1,5,4""".trimMargin().split("\n")

    private val input3 = """Register A: 2024
Register B: 0
Register C: 0

Program: 0,1,5,4,3,0""".trimMargin().split("\n")
    private val input4 = """Register A: 2024
Register B: 29
Register C: 0

Program: 1,7""".trimMargin().split("\n")

    private val input5 = """Register A: 2024
Register B: 2024
Register C: 43690

Program: 4,0""".trimMargin().split("\n")



    private val input6 = """Register A: 117440
Register B: 0
Register C: 0

Program: 0,3,5,4,3,0""".trimMargin().split("\n")

    private val result = "202367025818154"
}