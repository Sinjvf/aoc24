package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Direction
import com.example.aoc24.util.Graph
import com.example.aoc24.util.GraphData
import com.example.aoc24.util.Matrix
import com.example.aoc24.util.NodesWithData
import com.example.aoc24.util.Point2D
import com.example.aoc24.util.pathTo
import com.example.aoc24.util.search

class DaySolution20(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
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

            val nei = mutableSetOf<Point2D>()
            val search = Graph20(matrix).search(
                startNode = Node.Space(start),
                startData = IntGraphData(0),
                isFinish = { node -> node.node == Node.Space(end) },
                onVisited = { node ->
                    nei.addAll(node
                        .node
                        .point
                        .getNei(matrix)
                        .filter { matrix.get(it) is Node.Wall }
                        .filter {
                            it.getNei(matrix).filter { it != node.node.point }.any { matrix.get(it) is Node.Space }
                        }
                    )
                }
            )
            val minCost = search.pathTo(Node.Space(end))?.cost?.getLong() ?: 0



            println(nei.size)
            nei.forEachIndexed { id, it ->
                val copy = matrix.copy()
                copy.put(it, Node.Space(it))
                val newS = Graph20(copy).search(
                    startNode = Node.Space(start),
                    startData = IntGraphData(0),
                    isFinish = { node -> node.node == Node.Space(end) },
                ).pathTo(Node.Space(end))?.cost?.getLong() ?: 0

                copy.put(it, Node.Wall(it))
                if (id % 1000 == 0) {
                    println(id)
                }
                if (newS < minCost) {
                    // println(minCost - newS)
                    if (newS + 100 <= minCost) {
                        intRes++
                    }
                }
            }
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

            val visited = mutableSetOf<Point2D>()
            val search = Graph20(matrix).search(
                startNode = Node.Space(start),
                startData = IntGraphData(0),
                isFinish = { node -> node.node == Node.Space(end) },
                onVisited = { node ->
                    visited.add(node.node.point)
                }
            )

            println(visited.size)
            val costs = mutableMapOf<Long, Int>()
            visited.forEachIndexed { id, startCheet ->

                val toStartCost = search.pathTo(Node.Space(startCheet))?.cost?.getLong() ?: 0

                val ends = startCheet.getDistansedNei(matrix, 20).filter {
                    matrix.get(it) is Node.Space
                }
                if (startCheet == start) println(ends)
                ends.forEach { endCheet ->
                    val cheetCost = endCheet.distanceTo(startCheet)

                    val toCheetEnd = search.pathTo(Node.Space(endCheet))?.cost?.getLong() ?: 0
                    val cheetDiff = toCheetEnd - toStartCost - cheetCost
                    if (cheetDiff >= 100) {
                        val c = costs.getOrPut(cheetDiff) { 0 } + 1
                        costs.put(cheetDiff, c)
                        intRes++
                    }

                }
                if (id % 1000 == 0) {
                    println(id)
                }
            }
            println(costs)
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
    }

    class Graph20(val matrix: Matrix<Node>) : Graph<Node, IntGraphData> {

        override fun next(data: NodesWithData<Node, IntGraphData>): List<NodesWithData<Node, IntGraphData>> {
            return possibleDirPart1
                .map { it to data.node.point.toDirection(it) }
                .filter { it.second.inMatrix(matrix) && matrix.get(it.second) is Node.Space }
                .map {
                    val nextNode = Node.Space(it.second)
                    NodesWithData(data.node, nextNode, IntGraphData(1))
                }
        }

        private val possibleDirPart1 =
            mutableSetOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
    }

    class IntGraphData(val i: Int) : GraphData {
        override fun plus(a: GraphData): GraphData {
            if (a !is IntGraphData) throw IllegalArgumentException("expext IntGraphData class")

            return IntGraphData(i + a.i)
        }

        override fun getLong(): Long = i.toLong()

        override fun toString(): String {
            return i.toString()
        }
    }
}
