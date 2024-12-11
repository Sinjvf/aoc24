package com.example.solutions

import com.example.ILogger

class DaySolution11(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val line = mutableListOf<Long>()

        override fun handleLine(inputStr: String, pos: Int) {
            line.addAll(inputStr.split(" ").map { it.toLong() })


            intRes = blinking(pos, line).size
        }

        override fun finish() {
        }

        override fun obtainResult(): String = intRes.toString()

        private fun blinking(times: Int, value: List<Long>): List<Long> {
            return if (times == 1) {
                value.flatMap { handleSingle(it) }/*.also { println("blinking_last $times $value -> $it") }*/
            } else {
                blinking(
                    times - 1,
                    value.flatMap { handleSingle(it) })/*.also { println("blinking $times $value -> $it") }*/
            }
        }

        private fun handleSingle(value: Long): List<Long> {
            return buildList {
                when {
                    value == 0L -> add(1L)
                    value.toString().length % 2 == 0 -> {
                        val str = value.toString()
                        add(str.substring(0 until str.length / 2).toLong())
                        add(str.substring(str.length / 2 until str.length).toLong())
                    }

                    else -> add(value * 2024)
                }
            }
        }
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Long = 0
        private val line = mutableListOf<Long>()
        private val map = mutableMapOf<Pair<Long, Int>, Long>()

        override fun handleLine(inputStr: String, pos: Int) {
            line.addAll(inputStr.split(" ").map { it.toLong() })

            map.put(0L to 1, 1L)
            intRes = line.fold(0) { res, next ->
                res + blinking(pos, next)
            }
        }

        override fun finish() {
        }

        override fun obtainResult(): String = intRes.toString()

        private fun blinking(times: Int, value: Long): Long {

            val cache = map[value to times]
            if (cache != null) return cache
            return if (times == 1) {
                handleSingle(value)
            } else {

                val next = oneBlink(value).fold(0L) { res, next ->
                    res + blinking(times - 1, next)
                }
                map.put(value to times, next)
                next
            }
        }

        private fun oneBlink(value: Long): List<Long> {
            return buildList {
                when {
                    value == 0L -> add(1L)
                    value.toString().length % 2 == 0 -> {
                        val str = value.toString()
                        add(str.substring(0 until str.length / 2).toLong())
                        add(str.substring(str.length / 2 until str.length).toLong())
                    }

                    else -> add(value * 2024)
                }
            }
        }

        private fun handleSingle(value: Long): Long {
            return (
                when {
                    value == 0L -> 1L

                    value.toString().length % 2 == 0 -> 2L

                    else -> 1L
                }
                ).also {
                    map.put(value to 1, it)
                }
        }
    }
}
