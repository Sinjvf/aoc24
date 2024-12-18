package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Direction
import com.example.aoc24.util.Graph
import com.example.aoc24.util.GraphData
import com.example.aoc24.util.Matrix
import com.example.aoc24.util.NodesWithData
import com.example.aoc24.util.Point2D
import com.example.aoc24.util.path
import com.example.aoc24.util.search

class DaySolution18(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val size = 71
        private val sizeW = 1024
        private var i = 0
        private val matrix = Matrix<Node>(size, size, { point -> Node.Space(point) })

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) return
            val pointS = inputStr.split(",")
            val point = Point2D(pointS[0].toInt(), pointS[1].toInt())

            if(i<sizeW) {
                matrix.put(point, Node.Wall(point))
            }
            i++
        }

        override fun finish() {
            matrix.print(logger, printString = { node, _ -> node.print() })
            intRes = Graph18(matrix).search(
                startNode = Node.Space(Point2D(0, 0)),
                startData = IntGraphData(0),
                isFinish = { node -> node.node == Node.Space(Point2D(size - 1, size - 1)) }
            ).path()?.cost?.getLong()?.toInt()?:0
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val size = 71
        private val sizeW = 1024
        private val matrix = Matrix<Node>(size, size, { point -> Node.Space(point) })
        private val bytes = mutableListOf<Node.Wall>()
        private lateinit var res:Point2D

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) return
            val pointS = inputStr.split(",")
            val point = Point2D(pointS[0].toInt(), pointS[1].toInt())

            bytes.add(Node.Wall(point))

        }

        override fun finish() {
            //matrix.print(logger, printString = { node, _ -> node.print() })
            var i = sizeW
            for(i in 0..sizeW){
                matrix.put(point = bytes[i]._point, bytes[i])
            }
            while(true) {

                matrix.put(point = bytes[i]._point, bytes[i])
                if (Graph18(matrix).search(
                        startNode = Node.Space(Point2D(0, 0)),
                        startData = IntGraphData(0),
                        isFinish = { node -> node.node == Node.Space(Point2D(size - 1, size - 1)) }
                    ).path() == null
                ){
                    res = bytes[i]._point
                    break
                }
            i++
            }

        }

        override fun obtainResult(): String = "${res.x},${res.y}"
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

    class Graph18(val matrix: Matrix<Node>) : Graph<Node, IntGraphData> {

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

            return IntGraphData(i + 1)
        }

        override fun getLong(): Long = i.toLong()

        override fun toString(): String {
            return i.toString()
        }
    }
}
