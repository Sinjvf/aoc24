package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Direction
import com.example.aoc24.util.Matrix
import com.example.aoc24.util.Point2D
import com.example.aoc24.util.symbolToDir

class DaySolution15(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val matrix = Matrix<Node>()
        private lateinit var current: Point2D
        private var beginReadDirections = false
        private val directions = mutableListOf<Direction>()


        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) {
                beginReadDirections = true
                return
            }

            if (!beginReadDirections) {
                val col = inputStr.toCharArray().toList().mapIndexed { id, it ->
                    var value = it.toNode()
                    if (value == Node.Robot) {
                        current = Point2D(id, pos)
                        value = Node.Space
                    }
                    value
                }

                matrix.addRaw(pos, col)
            } else {
                for (ch in inputStr) {
                    directions.add(ch.symbolToDir())
                }
            }
        }

        override fun finish() {
            directions.forEach {
                move(it)
            }
            val iterator = matrix.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (next.data == Node.Box) {
                    intRes += 100 * next.pos.y + next.pos.x
                }
            }
        }

        fun move(dir: Direction) {
            val tryPoint = current.toDirection(dir)
            when (matrix.get(tryPoint)) {
                Node.Space -> current = tryPoint
                Node.Wall -> Unit
                Node.Robot -> throw IllegalArgumentException("ROBOT")
                else -> {
                    var newTry = tryPoint
                    while (matrix.get(newTry) != Node.Wall) {
                        newTry = newTry.toDirection(dir)
                        if (matrix.get(newTry) == Node.Space) {
                            matrix.put(tryPoint, Node.Space)
                            matrix.put(newTry, Node.Box)
                            current = tryPoint
                            return
                        }
                    }
                }

            }
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val matrix = Matrix<Node>()
        private lateinit var current: Point2D
        private var beginReadDirections = false
        private val directions = mutableListOf<Direction>()


        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) {
                beginReadDirections = true
                return
            }

            if (!beginReadDirections) {
                val col = inputStr.toCharArray().toList().mapIndexed { id, it ->
                    var value = it.toBigNode(Point2D( id*2, pos))
                    if (value == Node.Robot) {
                        current = Point2D(id*2, pos)
                        value = Node.Space
                    }
                    value
                }


                matrix.addRaw(pos, col.flatMap { listOf(it, it) })
            } else {
                for (ch in inputStr) {
                    directions.add(ch.symbolToDir())
                }
            }
        }

        override fun finish() {
            printMatrix()
            directions.forEach {
                move(it)
            }
            val iterator = matrix.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (next.data is Node.BigBox && next.pos == next.data.pos.first) {
                    intRes += 100 * next.pos.y + next.pos.x
                }
            }
        }

        fun move(dir: Direction) {
            val tryPoint = current.toDirection(dir)
            when (matrix.get(tryPoint)) {
                Node.Space -> current = tryPoint
                Node.Wall -> Unit
                Node.Robot -> throw IllegalArgumentException("ROBOT")
                else -> {
                    val box = matrix.get(tryPoint) as Node.BigBox

                    var newTry = tryPoint
                    when (dir) {
                        Direction.RIGHT,
                        Direction.LEFT -> {

                            while (matrix.get(newTry) != Node.Wall) {
                                newTry = newTry.toDirection(dir)
                                if (matrix.get(newTry) == Node.Space) {
                                    current = tryPoint
                                    matrix.put(tryPoint, Node.Space)

                                    if (dir == Direction.RIGHT) {
                                        while (newTry > tryPoint) {
                                            val boxR = Node.BigBox(newTry.toDirection(Direction.LEFT) to newTry)
                                            matrix.put(
                                                newTry,
                                                boxR
                                            )
                                            matrix.put(
                                                newTry.toDirection(Direction.LEFT),
                                                boxR
                                            )
                                        //    println("shift to $newTry $boxR")
                                            newTry = newTry.toDirection(Direction.LEFT, 2)
                                        }
                                    } else {
                                        while (newTry < tryPoint) {
                                            val boxL = Node.BigBox(newTry to newTry.toDirection(Direction.RIGHT))
                                            matrix.put(
                                                newTry,
                                                boxL
                                            )
                                            matrix.put(
                                                newTry.toDirection(Direction.RIGHT),
                                                boxL
                                            )
                                        //    println("shift to $newTry $boxL")
                                            newTry = newTry.toDirection(Direction.RIGHT, 2)
                                        }
                                    }
                                    return
                                }
                            }
                        }

                        else -> {
                            var newTryBox = listOf(box)
                            while (newTryBox.flatMap { box1 -> listOf(box1.pos.first, box1.pos.second) }
                                    .all { matrix.get(it) != Node.Wall }) {

                                val next = newTryBox.flatMap { buildNext(it, dir) }
                                if (next.flatMap { box1 -> listOf(box1.pos.first, box1.pos.second) }
                                        .all { matrix.get(it) == Node.Space  || matrix.get(it) is Node.BigBox }) {
                                    current = tryPoint
                                    moveNext(matrix.get(newTry) as Node.BigBox, dir)
                                    matrix.put(tryPoint, Node.Space)
                                    return
                                }
                                newTryBox = next
                            }
                        }
                    }
                }
            }
        }

        fun printMatrix(){
            matrix.put(current, Node.Robot)
            matrix.print(logger, printString = { it, pos-> it.print(pos)})
            matrix.put(current, Node.Space)
        }

        fun buildNext(box: Node.BigBox, dir: Direction): Set<Node.BigBox> =
            buildSet {

                val f = box.pos.first.toDirection(dir)
                val s = box.pos.second.toDirection(dir)

                add(Node.BigBox(f to s))
                if (f.inMatrix(matrix) && matrix.get(f) is Node.BigBox) {
                    addAll(buildNext(matrix.get(f) as Node.BigBox, dir))
                }
                if (s.inMatrix(matrix) &&  matrix.get(s) is Node.BigBox) {
                    addAll(buildNext(matrix.get(s) as Node.BigBox, dir))
                }
            }

        fun moveNext(box: Node.BigBox, dir: Direction) {

            val f = box.pos.first.toDirection(dir)
            val s = box.pos.second.toDirection(dir)
            if (matrix.get(f) is Node.BigBox) {
                moveNext(matrix.get(f) as Node.BigBox, dir)
            }
            if (matrix.get(s) is Node.BigBox) {
                moveNext(matrix.get(s) as Node.BigBox, dir)
            }
            matrix.put(f, Node.BigBox(f to s))
            matrix.put(s, Node.BigBox(f to s))
            matrix.put(box.pos.first, Node.Space)
            matrix.put(box.pos.second, Node.Space)
        }
        override fun obtainResult(): String = intRes.toString()
    }

    sealed class Node() {
        open fun print(position:Point2D)=
            toString()

        object Wall : Node() {
            override fun toString(): String {
                return "#"
            }
        }

        object Box : Node() {
            override fun toString(): String {
                return "O"
            }
        }

        data class BigBox(val pos: Pair<Point2D, Point2D>) : Node() {
            override fun toString(): String {
                return "O"
            }

            override fun print(position:Point2D)=
                if (position==this.pos.first) "["
                else "]"

        }

        object Space : Node() {
            override fun toString(): String {
                return "."
            }
        }

        object Robot : Node() {
            override fun toString(): String {
                return "@"
            }
        }
    }

    fun Char.toNode() = when (this) {
        '#' -> Node.Wall
        'O' -> Node.Box
        '.' -> Node.Space
        '@' -> Node.Robot
        else -> throw IllegalArgumentException()
    }

    fun Char.toBigNode(point2D: Point2D) = when (this) {
        '#' -> Node.Wall
        'O' -> Node.BigBox(point2D to point2D.toDirection(Direction.RIGHT))
        '.' -> Node.Space
        '@' -> Node.Robot
        else -> throw IllegalArgumentException()
    }
}
