package com.example.solutions

import com.example.ILogger
import kotlin.math.abs

class DaySolution2(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0


        override fun handleLine(inputStr: String, pos: Int) {

            if (inputStr.isEmpty()) return


            val report = inputStr.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            if (isSafe(report, 0)) {
                intRes++
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }

    private fun isSafe(report: List<Int>, possibleRecursion: Int): Boolean {
        var diff = 0
        report.forEachIndexed { id, it ->
            if (id != 0) {
                val diff2 = it - report[id - 1]
                if (abs(diff2) > 3) {
                    return tryRecursion(possibleRecursion, report, id)
                }
                if (id == 1) {
                    diff = diff2
                } else {
                    if (diff2 * diff <= 0) {
                        return tryRecursion(possibleRecursion, report, id)
                    }
                }
            }

        }
        return true
    }

    private fun tryRecursion(count: Int, report: List<Int>, currentId: Int): Boolean {
        if (count == 0) return false
        if (currentId == 2 && isSafe(report.filterIndexed { index, i -> index != 0 }, count - 1)) {
            return true
        }
        if (isSafe(report.filterIndexed { index, i -> index != currentId - 1 }, count - 1)) {
            return true
        } else if (isSafe(report.filterIndexed { index, i -> index != currentId }, count - 1)) {
            return true
        }
        return false
    }

    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) return
            val report = inputStr.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            if (isSafe(report, 1)) {
                intRes++
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }
}
