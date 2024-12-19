package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Rearrangement
import com.example.aoc24.util.StringData
import com.example.aoc24.util.toStringData

class DaySolution19(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Long = 0L
        private val towels = mutableListOf<StringData>()
        private var tParsed = false
        private var prints = mutableListOf<StringData>()

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) {
                tParsed = true
                return
            }
            if (!tParsed) {
                towels.addAll(inputStr.split(", ").map { it.toStringData() })
            } else {
                prints.add(inputStr.toStringData())
            }
        }

        override fun finish() {
            // пЫхаем все полотенчики
            val rearrangement = Rearrangement(towels)

            prints.forEach {
                if (rearrangement.canComposite(it)) {
                    intRes++
                }
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }

    override val part2 = object : DaySolutionPart {
        private var intRes: Long = 0L
        private val towels = mutableListOf<StringData>()
        private var tParsed = false
        private var prints = mutableListOf<StringData>()

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) {
                tParsed = true
                return
            }
            if (!tParsed) {
                towels.addAll(inputStr.split(", ").map { it.toStringData() })
            } else {
                prints.add(inputStr.toStringData())
            }
        }

        override fun finish() {
            // пЫхаем все полотенчики
            val rearrangement = Rearrangement(towels)

            prints.forEach {
                val comp = rearrangement.count(it)
                println("$it,  $comp")
                intRes += comp
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }
}
