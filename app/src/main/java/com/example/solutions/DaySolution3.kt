package com.example.solutions

import com.example.ILogger

class DaySolution3(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0

        override fun handleLine(inputStr: String, pos: Int) {
            val word = "mul"
            val regexFull = """(?i)$word\(\d{1,3},\d{1,3}\)""".toRegex()
            val regexSingle = """(?i)\d{1,3}""".toRegex()

            regexFull.findAll(inputStr).forEach {
                println(it.value + " ")
                intRes += regexSingle.findAll(it.value).map { it.value.toInt() }.fold(1) { a, b -> a * b }
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0

        override fun handleLine(inputStr: String, pos: Int) {
            val word = "mul"
            val doWord = """do\(\)"""
            val dontWord = """don\'t\(\)"""
            val regexFull = """(?i)($word\(\d{1,3},\d{1,3}\))|$doWord|$dontWord""".toRegex()
            val regexSingle = """(?i)\d{1,3}""".toRegex()

            var calc = true
            regexFull.findAll(inputStr).forEach {
                println(it.value + " ")
                if (it.value == "do()") {
                    calc = true
                } else if (it.value == "don't()") {
                    calc = false
                } else {
                    if (calc) {
                        intRes += regexSingle.findAll(it.value).map { it.value.toInt() }.fold(1) { a, b -> a * b }
                    }
                }
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }
}
