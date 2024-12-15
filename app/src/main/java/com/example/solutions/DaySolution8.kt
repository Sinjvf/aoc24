package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.OldMatrix
import com.example.aoc24.util.Point2D

class DaySolution8(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private var oldMatrix = OldMatrix<Place>()
        private val map = mutableMapOf<Char, MutableList<Pair<Place.Antn, Point2D>>>()
        private val set = mutableSetOf<Point2D>()


        override fun handleLine(inputStr: String, pos: Int) {
            for (ch in inputStr) {
                val current = ch.toPlace()
                if (current is Place.Antn) {
                    val set = map.getOrPut(ch) { mutableListOf() }
                    set.add(current to oldMatrix.getNextPoint(pos))
                }

                oldMatrix.addToEnd(pos, current)
            }
        }

        override fun finish() {
            map.forEach { t, list ->
                println(list)
                list.forEachIndexed { id, pair ->
                    val current = pair.second
                    for (i in id + 1 until list.size) {
                        val next = list[i].second
                        val vectors = current.oldVectorTo(next)

                        val antinod1 = next.toOldDirections(vectors)
                        if (oldMatrix.pointInRange(antinod1)) {
                            set.add(antinod1)
                            oldMatrix.put(antinod1, Place.Antn('#'))
                        }
                        val antinod2 = current.toOldDirections(vectors.map { it.first to it.second.opposite })
                        if (oldMatrix.pointInRange(antinod2)) {
                            set.add(antinod2)
                            oldMatrix.put(antinod2, Place.Antn('#'))
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
        private var oldMatrix = OldMatrix<Place>()
        private val map = mutableMapOf<Char, MutableList<Pair<Place.Antn, Point2D>>>()
        private val set = mutableSetOf<Point2D>()


        override fun handleLine(inputStr: String, pos: Int) {
            for (ch in inputStr) {
                val current = ch.toPlace()
                if (current is Place.Antn) {
                    val set = map.getOrPut(ch) { mutableListOf() }
                    set.add(current to oldMatrix.getNextPoint(pos))
                }

                oldMatrix.addToEnd(pos, current)
            }
        }

        override fun finish() {
            map.forEach { t, list ->
                list.forEachIndexed { id, pair ->
                    val current = pair.second
                    for (i in id + 1 until list.size) {
                        val added = list[i].second
                        val vectors = current.oldVectorTo(added)



                        var antinod1 = added
                        while (oldMatrix.pointInRange(antinod1)){

                            if (oldMatrix.pointInRange(antinod1)) {
                                set.add(antinod1)
                                oldMatrix.put(antinod1, Place.Antn('#'))
                            }
                            antinod1 = antinod1.toOldDirections(vectors)
                        }



                        var antinod2 = current
                        while (oldMatrix.pointInRange(antinod2)){
                            if (oldMatrix.pointInRange(antinod2)) {
                                set.add(antinod2)
                                oldMatrix.put(antinod2, Place.Antn('#'))
                            }
                            antinod2 = antinod2.toOldDirections(vectors.map { it.first to it.second.opposite })
                        }
                    }
                }
            }
            oldMatrix.print(logger, "")
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