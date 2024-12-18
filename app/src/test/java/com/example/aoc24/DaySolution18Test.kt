package com.example.aoc24

import com.example.solutions.DaySolution18
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DaySolution18Test {
    private val solution = DaySolution18(TestLogger())

    @Test
    fun testPart1() = runTest {
        input18.forEachIndexed { id, str -> solution.part1.handleLine(str, id) }
        solution.part1.finish()
        assertEquals(result, solution.part1.obtainResult())
    }

    @Test
    fun testPart2() = runTest {
        input18.forEachIndexed { id, str -> solution.part2.handleLine(str, id) }
        solution.part2.finish()
        assertEquals(result, solution.part2.obtainResult())
    }

    private val input1 = """5,4
4,2
4,5
3,0
2,1
6,3
2,4
1,5
0,6
3,3
2,6
5,1
1,2
5,5
2,5
6,5
1,4
0,4
6,4
1,1
6,1
1,0
0,5
1,6
2,0""".trimMargin().split("\n")



    private val result = "11"
}