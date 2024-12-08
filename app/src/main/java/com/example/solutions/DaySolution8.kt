package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Matrix
import com.example.aoc24.util.Point2D

class DaySolution8(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private var matrix = Matrix<Place>()
        private val map = mutableMapOf<Char, MutableList<Pair<Place.Antn, Point2D>>>()
        private val set = mutableSetOf<Point2D>()


        override fun handleLine(inputStr: String, pos: Int) {
            for (ch in inputStr) {
                val current = ch.toPlace()
                if (current is Place.Antn) {
                    val set = map.getOrPut(ch) { mutableListOf() }
                    set.add(current to matrix.getNextPoint(pos))
                }

                matrix.addToEnd(pos, current)
            }
        }

        override fun finish() {
            map.forEach { t, list ->
                println(list)
                list.forEachIndexed { id, pair ->
                    val current = pair.second
                    for (i in id + 1 until list.size) {
                        val next = list[i].second
                        val vectors = current.vectorTo(next)

                        val antinod1 = next.toDirections(vectors)
                        if (matrix.pointInRange(antinod1)) {
                            set.add(antinod1)
                            matrix.put(antinod1, Place.Antn('#'))
                        }
                        val antinod2 = current.toDirections(vectors.map { it.first to it.second.opposite })
                        if (matrix.pointInRange(antinod2)) {
                            set.add(antinod2)
                            matrix.put(antinod2, Place.Antn('#'))
                        }
                    }
                }
            }
            //      matrix.print(logger, "")
            intRes = set.size
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private var matrix = Matrix<Place>()
        private val map = mutableMapOf<Char, MutableList<Pair<Place.Antn, Point2D>>>()
        private val set = mutableSetOf<Point2D>()


        override fun handleLine(inputStr: String, pos: Int) {
            for (ch in inputStr) {
                val current = ch.toPlace()
                if (current is Place.Antn) {
                    val set = map.getOrPut(ch) { mutableListOf() }
                    set.add(current to matrix.getNextPoint(pos))
                }

                matrix.addToEnd(pos, current)
            }
        }

        override fun finish() {
            map.forEach { t, list ->
                list.forEachIndexed { id, pair ->
                    val current = pair.second
                    for (i in id + 1 until list.size) {
                        val added = list[i].second
                        val vectors = current.vectorTo(added)



                        var antinod1 = added
                        while (matrix.pointInRange(antinod1)){

                            if (matrix.pointInRange(antinod1)) {
                                set.add(antinod1)
                                matrix.put(antinod1, Place.Antn('#'))
                            }
                            antinod1 = antinod1.toDirections(vectors)
                        }



                        var antinod2 = current
                        while (matrix.pointInRange(antinod2)){
                            if (matrix.pointInRange(antinod2)) {
                                set.add(antinod2)
                                matrix.put(antinod2, Place.Antn('#'))
                            }
                            antinod2 = antinod2.toDirections(vectors.map { it.first to it.second.opposite })
                        }
                    }
                }
            }
            matrix.print(logger, "")
            intRes = set.size
        }
        override fun obtainResult(): String = intRes.toString()
    }

    sealed class Place(val char: Char) {
        object Space : Place('.') {
            override fun toString(): String {
                return "."
            }
        }

        data class Antn(val _char: Char) : Place(_char) {
            override fun toString(): String {
                return "$_char"
            }
        }
    }

    fun Char.toPlace(): Place =
        when (this) {
            '.' -> Place.Space
            else -> Place.Antn(this)
        }
}