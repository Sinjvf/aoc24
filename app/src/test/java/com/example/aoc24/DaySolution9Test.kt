package com.example.aoc24

import com.example.solutions.DaySolution9
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution9Test {
    private val solution = DaySolution9(TestLogger())

    @Test
    fun testPart1() = runTest {
        input9.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result, solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input9.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result, solution.part2.obtainResult())
    }

    private val input = """2333133121414131402""".trimMargin().split("\n")
    private val input2 = """1012345673234623412563245430000077878787878""".trimMargin().split("\n")


    private val result = "11"

}