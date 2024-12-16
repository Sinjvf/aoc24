package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Direction
import com.example.aoc24.util.Graph
import com.example.aoc24.util.GraphData
import com.example.aoc24.util.Matrix
import com.example.aoc24.util.NodesWithData
import com.example.aoc24.util.Point2D
import com.example.aoc24.util.pathTo
import com.example.aoc24.util.reversedPathTo
import com.example.aoc24.util.search
import kotlin.streams.toList

class DaySolution16(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Long = 0
        private val matrix = Matrix<Node>()
        private lateinit var start: Point2D
        private lateinit var end: Point2D

        override fun handleLine(inputStr: String, pos: Int) {
            val col = inputStr.toCharArray().toList().mapIndexed { id, it ->
                val point = Point2D(id, pos)
                when (it) {
                    '#' -> Node.Wall(point)
                    'S' -> {
                        start = point
                        Node.Space(point)
                    }

                    'E' -> {
                        end = point
                        Node.Space(point)
                    }

                    else -> Node.Space(point)
                }
            }

            matrix.addRaw(pos, col)
        }

        override fun finish() {
            val search = Graph16(matrix).search(
                startNode = Node.Space(start),
                startData = IntGraphData(0, Direction.RIGHT),
                isFinish = { node -> node.node == Node.Space(end) },

                )
            intRes = search.pathTo(Node.Space(end))?.cost?.getLong() ?: 0
            println(
                search.pathTo(Node.Space(end))?.stream()?.toList()?.map { it.point }
            )
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val matrix = Matrix<Node>()
        private lateinit var start: Point2D
        private lateinit var end: Point2D

        override fun handleLine(inputStr: String, pos: Int) {
            val col = inputStr.toCharArray().toList().mapIndexed { id, it ->
                val point = Point2D(id, pos)
                when (it) {
                    '#' -> Node.Wall(point)
                    'S' -> {
                        start = point
                        Node.Space(point)
                    }

                    'E' -> {
                        end = point
                        Node.Space(point)
                    }

                    else -> Node.Space(point)
                }
            }

            matrix.addRaw(pos, col)
        }

        override fun finish() {

            val search1 = Graph16(matrix).search(
                startNode = Node.Space(start),
                startData = IntGraphData(0, Direction.RIGHT),
                isFinish = { node -> node.node == Node.Space(end) },
                ).reversedPathTo(Node.Space(end))
            val set = mutableSetOf<Point2D>()
            val path = search1?.path?:return

            println(path)
            path.forEach {
                set.add(it.point)
                findAnother(it.point, search1.cost.getLong(), set)
            }
            set.forEach {
                matrix.put(it, Node.Z(it))
            }
            matrix.print(logger, printString = { node, _ -> node.print() })
            intRes = set.size
        }

        fun findAnother( exclude:Point2D, cost:Long, set: MutableSet<Point2D>){
            if (exclude==start || exclude==end) return

            matrix.put(exclude, Node.Wall(exclude))
            val itSearch = Graph16(matrix).search(
                startNode = Node.Space(start),
                startData = IntGraphData(0, Direction.RIGHT),
                isFinish = { node -> node.node == Node.Space(end) },
            ).reversedPathTo(Node.Space(end))
            if (itSearch?.cost?.getLong()==cost){
                itSearch.path.forEach {
                    if (!set.contains(it.point)){
                        set.add(it.point)
                        findAnother(it.point, cost, set)
                    }
                }
            }

            matrix.put(exclude, Node.Space(exclude))
        }

        override fun obtainResult(): String = intRes.toString()
    }

    sealed class Node(val point: Point2D) {
        open fun print() = toString()

        data class Wall(val _point: Point2D) : Node(_point) {
            override fun toString(): String {
                return "#"
            }
        }

        data class Space(val _point: Point2D) : Node(_point) {
            override fun toString(): String {
                return _point.toString()
            }

            override fun print(): String {
                return "."
            }
        }

        data class Z(val _point: Point2D) : Node(_point) {
            override fun toString(): String {
                return "0"
            }
        }
    }

    class Graph16(val matrix: Matrix<Node>) : Graph<Node, IntGraphData> {

        override fun next(data: NodesWithData<Node, IntGraphData>): List<NodesWithData<Node, IntGraphData>> {
            return possibleDirPart1
                .map { it to data.node.point.toDirection(it) }
                .filter { it.second.inMatrix(matrix) && matrix.get(it.second) is Node.Space }
                .map {
                    val nextNode = Node.Space(it.second)
                    NodesWithData(data.node, nextNode, IntGraphData(1, it.first))
                }
        }

        private val possibleDirPart1 =
            mutableSetOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
    }

    class IntGraphData(val i: Int, val direction: Direction) : GraphData {
        override fun plus(a: GraphData): GraphData {
            if (a !is IntGraphData) throw IllegalArgumentException("expext IntGraphData class")
            val sum = if (a.direction != direction) {
                1001
            } else {
                1
            }

            return IntGraphData(i + sum, a.direction)
        }

        override fun getLong(): Long = i.toLong()

        override fun toString(): String {
            return i.toString()
        }
    }
}
