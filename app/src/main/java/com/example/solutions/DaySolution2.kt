package com.example.solutions

import com.example.ILogger

/**
 * @author Тамара Синева on 01.12.2023
 */
class DaySolution2(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0

        override fun handleLine(inputStr: String, pos: Int) {
        }

        override fun finish() {
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0

        override fun handleLine(inputStr: String, pos: Int) {
        }

        override fun finish() {
        }

        override fun obtainResult(): String = intRes.toString()
    }
}
