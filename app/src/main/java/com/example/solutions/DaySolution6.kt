package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Direction
import com.example.aoc24.util.Matrix
import com.example.aoc24.util.Point2D
import com.example.aoc24.util.toRight

class DaySolution6(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private var matrix = Matrix<Place>()
        private lateinit var current: Point2D
        private var direction: Direction = Direction.UP

        override fun handleLine(inputStr: String, pos: Int) {
            for (ch in inputStr) {
                if (ch == '^') {
                    current = matrix.getNextPoint(pos)
                }
                matrix.addToEnd(pos, (ch.takeIf { ch != '^' } ?: '.').toPlace())
            }
        }

        override fun finish() {
            intRes = SingleSearch(matrix, current, direction).go().size
        }

        override fun obtainResult(): String = intRes.toString()

    }

    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private var matrix = Matrix<Place>()
        private lateinit var current: Point2D
        private var direction: Direction = Direction.UP
        private val approved = mutableSetOf<Point2D>()
        private lateinit var start: Point2D

        override fun handleLine(inputStr: String, pos: Int) {
            for (ch in inputStr) {
                if (ch == '^') {
                    current = matrix.getNextPoint(pos)
                    start = current
                }
                matrix.addToEnd(pos, (ch.takeIf { ch != '^' } ?: '.').toPlace())
            }
        }

        override fun finish() {
            SingleSearch(matrix, current, direction).go().forEach {
                val check = LoopCheck(matrix, start, Direction.UP, it)
                if (check.isInLoop()) {
                    approved.add(it)
                }
            }
            intRes = approved.size
        }
        override fun obtainResult(): String = intRes.toString()
    }
}

private fun next(matrix: Matrix<Place>, current: Point2D, direction: Direction): Point2D? {
    val nextP = current.toDirection(direction)
    return if (matrix.pointInRange(nextP))
        nextP/*.also { println( "next" + it) }*/
    else
        null
}

enum class Place {
    Space {
        override fun toString(): String {
            return "."
        }
    },
    Obstr {
        override fun toString(): String {
            return "#"
        }
    }
}

fun Char.toPlace(): Place =
    when (this) {
        '.' -> Place.Space
        '#' -> Place.Obstr
        else -> throw IllegalArgumentException("bad symbpl $this")
    }

class SingleSearch(
    val matrix: Matrix<Place>,
    val _current: Point2D,
    val _direction: Direction,
) {

    private var current: Point2D = _current
    private var direction: Direction = _direction

    private val visited = mutableSetOf<Point2D>()

    fun go(): Set<Point2D> {
        visited.add(current)
        while (true) {
            goForward() ?: break
        }
        return visited
    }

    private fun goForward(): Point2D? {
        while (true) {
            val next: Point2D = next(matrix, current, direction) ?: return null
            val nextPlace = matrix.get(next)
            if (nextPlace == Place.Obstr) {
                direction = direction.toRight()

                println(direction)
                return current
            }
            current = next
            visited.add(next)
            println(current)
        }
    }
}

class LoopCheck(
    val matrix: Matrix<Place>,
    val _current: Point2D,
    val _direction: Direction,
    val changed: Point2D,
) {
    private var current: Point2D = _current
    private var direction: Direction = _direction
    private val visited = mutableSetOf<Pair<Point2D, Direction>>()
    fun isInLoop(
    ): Boolean {
        //  println("isInLoop $changed")
        visited.add(current to direction)
        while (true) {
            val forward = goForward()
            forward.first ?: return false
            if (forward.second) {

                return true
            }
        }
    }

    private fun goForward(
    ): Pair<Point2D?, Boolean> {
        while (true) {

            val next: Point2D = next(matrix, current, direction) ?: return null to false
            val nextPlace = if (next == changed) {
                Place.Obstr
            } else {
                matrix.get(next)
            }
            if (nextPlace == Place.Obstr) {
                direction = direction.toRight()
                return current to false
            }
            if (visited.contains(next to direction)) {
                return current to true
            }
            visited.add(next to direction)
            current = next
        }
    }
}
