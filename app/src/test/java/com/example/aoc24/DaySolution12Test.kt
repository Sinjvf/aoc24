package com.example.aoc24

import com.example.solutions.DaySolution12
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution12Test {
    private val solution = DaySolution12(TestLogger())

    @Test
    fun testPart1() = runTest {
        input12.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result, solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input12.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result, solution.part2.obtainResult())
    }

    private val input1 = """AAAA
BBCD
BBCC
EEEC""".trimMargin().split("\n")


    private val input2 = """EEEEE
EXXXX
EEEEE
EXXXX
EEEEE""".trimMargin().split("\n")


    private val input3 = """AAAAAA
AAABBA
AAABBA
ABBAAA
ABBAAA
AAAAAA""".trimMargin().split("\n")


    private val input = """RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE""".trimMargin().split("\n")

    private val result = "11"
}