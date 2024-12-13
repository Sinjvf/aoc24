package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Point2D

class DaySolution13(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val machines = mutableListOf<Machine>()

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.contains("Button A:")) {

                val regexFull = """(?i)\d{1,6}""".toRegex()
                val pos = regexFull.findAll(inputStr.subSequence("Button A:".length, inputStr.length))
                    .map { it.value.toInt() }.toList()
                machines.add(Machine(Point2D(pos[0], pos[1])))
            }
            if (inputStr.contains("Button B:")) {

                val regexFull = """(?i)\d{1,6}""".toRegex()
                val pos = regexFull.findAll(inputStr.subSequence("Button A:".length, inputStr.length))
                    .map { it.value.toInt() }.toList()
                machines[machines.size - 1] = machines.last().copy(b = Point2D(pos[0], pos[1]))
            }
            if (inputStr.contains("Prize:")) {

                val regexFull = """(?i)\d{1,6}""".toRegex()
                val pos =
                    regexFull.findAll(inputStr.subSequence("Prize:".length, inputStr.length)).map { it.value.toInt() }
                        .toList()
                machines[machines.size - 1] = machines.last().copy(res = Point2D(pos[0], pos[1]))
            }
        }

        private fun Machine.findPrise() {
            val y = ((res.y) * a.x - a.y * (res.x)).toFloat() / (b.y * a.x - a.y * b.x)
            val x = ((res.x) - b.x * y) / a.x

            if (y * 10 % 10 == 0f && x * 10 % 10 == 0f && x < 100 && y < 100) {
                intRes += x.toInt() * 3 + y.toInt()
            }
            println("$x, $y")
        }

        override fun finish() {
            println(machines)
            machines.forEach { it.findPrise() }
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Long = 0
        private val machines = mutableListOf<Machine>()

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.contains("Button A:")) {

                val regexFull = """(?i)\d{1,6}""".toRegex()
                val pos = regexFull.findAll(inputStr.subSequence("Button A:".length, inputStr.length))
                    .map { it.value.toInt() }.toList()
                machines.add(Machine(Point2D(pos[0], pos[1])))
            }
            if (inputStr.contains("Button B:")) {

                val regexFull = """(?i)\d{1,6}""".toRegex()
                val pos = regexFull.findAll(inputStr.subSequence("Button A:".length, inputStr.length))
                    .map { it.value.toInt() }.toList()
                machines[machines.size - 1] = machines.last().copy(b = Point2D(pos[0], pos[1]))
            }
            if (inputStr.contains("Prize:")) {

                val regexFull = """(?i)\d{1,6}""".toRegex()
                val pos =
                    regexFull.findAll(inputStr.subSequence("Prize:".length, inputStr.length)).map { it.value.toInt() }
                        .toList()
                machines[machines.size - 1] = machines.last().copy(res = Point2D(pos[0], pos[1]))
            }
        }

        private fun Machine.findPrise() {
            val shift = 10000000000000
            val y = ((res.y + shift) * a.x - a.y * (res.x + shift)).toDouble() / (b.y * a.x - a.y * b.x)
            val x = ((res.x + shift) - b.x * y) / a.x

            if (y * 10 % 10 == 0.0 && x * 10 % 10 == 0.0) {
                intRes += x.toLong() * 3 + y.toLong()
            }
            println("$x, $y")
        }

        override fun finish() {
            println(machines)
            machines.forEach { it.findPrise() }
        }

        override fun obtainResult(): String = intRes.toString()
    }

    data class Machine(val a: Point2D, var b: Point2D = Point2D(0, 0), var res: Point2D = Point2D(0, 0)) {
        override fun toString(): String {
            return "a = $a, b = $b, prize = $res"
        }
    }
}
