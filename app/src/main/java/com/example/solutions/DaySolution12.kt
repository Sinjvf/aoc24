package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Direction
import com.example.aoc24.util.Graph
import com.example.aoc24.util.GraphData
import com.example.aoc24.util.Matrix
import com.example.aoc24.util.NodesWithData
import com.example.aoc24.util.Point2D
import com.example.aoc24.util.getDirs
import com.example.aoc24.util.opposite
import com.example.aoc24.util.search

class DaySolution12(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val matrix = Matrix<Char>()
        private val graph = Graph12(matrix)
        private val visited = mutableSetOf<Point2D>()

        override fun handleLine(inputStr: String, pos: Int) {
            for (ch in inputStr) {
                matrix.addToEnd(pos, ch)
            }
        }

        override fun finish() {
            val iterator = matrix.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()

                if (visited.contains(next.pos)) continue
                val res = graph.search(
                    startNode = Node(next.pos),
                    startData = IntGraphData(graph.getNeighbords1(next.pos)),
                    isFinish = { false },
                )
                var square = 0
                val perim =
                    res.searchTree.keys.map {
                        visited.add(it.point)
                        square++
                        graph.getNeighbords1(it.point)
                    }.fold(0) { res, next -> res + next }
                //       println("$square, $perim")
                intRes += square * perim
            }


        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val matrix = Matrix<Char>()
        private val graph = Graph12(matrix)
        private val areas = mutableListOf<Set<Point2D>>()
        private val visited = mutableSetOf<Point2D>()

        override fun handleLine(inputStr: String, pos: Int) {
            for (ch in inputStr) {
                matrix.addToEnd(pos, ch)
            }
        }

        override fun finish() {
            val iterator = matrix.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()

                if (visited.contains(next.pos)) continue
                val res = graph.search(
                    startNode = Node(next.pos),
                    startData = IntGraphData(graph.getNeighbords1(next.pos)),
                    isFinish = { false },
                    onVisited = { visited.add(it.node.point) }
                )
                val square = res.searchTree.keys.size

                val areaVisited = mutableSetOf<Pair<Point2D, Direction>>()
                var perim = 0
                val area = res.searchTree.keys.map { it.point }

                area.forEach { areaPoint ->
                    /*val dirs =*/ getFenceDir(areaPoint, area).forEach { dir ->
                    if (!areaVisited.contains(areaPoint to dir)) {
                        perim++

                        //   println("($areaPoint, $dir)")
                        Graph2(area, dir).search(
                            areaPoint,
                            IntGraphData(0),
                            { false },
                            onVisited = { node -> areaVisited.add(node.node to dir) }
                        )
                    }
                }
                }

                println("$square, $perim")

                intRes += square * perim
            }


        }

        override fun obtainResult(): String = intRes.toString()
    }

    data class Node(val point: Point2D)
    class Graph12(val matrix: Matrix<Char>) : Graph<Node, IntGraphData> {

        override fun next(data: NodesWithData<Node, IntGraphData>): List<NodesWithData<Node, IntGraphData>> {
            return possibleDir
                .map { it to data.node.point.toDirection(it) }
                .filter { it.second.inMatrix(matrix) }
                .filter { matrix.get(it.second) == matrix.get(data.node.point) }
                .map {
                    val newNode = matrix.get(it.second)
                    val newVal = getNeighbords1(it.second)
                    val nextNode = Node(it.second)
                    NodesWithData(data.node, nextNode, IntGraphData(newVal))
                }
        }

        fun getNeighbords1(pos: Point2D) = possibleDir
            .map { it to pos.toDirection(it) }
            //   .filter { }
            .filter {
                !it.second.inMatrix(matrix)
                    || matrix.get(it.second) != matrix.get(pos)
            }

            .size

        val possibleDir =
            mutableSetOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
    }

    class Graph2(val area: List<Point2D>, val direction: Direction /*val matrix: Matrix<Char>*/) :
        Graph<Point2D, IntGraphData> {
        override fun next(data: NodesWithData<Point2D, IntGraphData>): List<NodesWithData<Point2D, IntGraphData>> {
            return direction.orientation.opposite().getDirs()
                .map { it to data.node.toDirection(it) }
                .filter { area.contains(it.second) }
                .filter { getFenceDir(it.second, area).contains(direction) }
                .map {
                    NodesWithData(data.node, it.second, IntGraphData(0))
                }
        }
    }

    class IntGraphData(val i: Int) : GraphData {
        override fun plus(a: GraphData): GraphData {
            if (a !is IntGraphData) throw IllegalArgumentException("expext IntGraphData class")


            return IntGraphData(a.i + i)
        }

        override fun getLong(): Long = i.toLong()

        override fun toString(): String {
            return i.toString()
        }
    }
}

fun getFenceDir(pos: Point2D, area: List<Point2D>) =
    mutableSetOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
        .map { it to pos.toDirection(it) }
        .filter {
            !area.contains(it.second)
        }
        .map { it.first }


