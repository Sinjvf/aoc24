package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Direction
import com.example.aoc24.util.Graph
import com.example.aoc24.util.GraphData
import com.example.aoc24.util.NodesWithData
import com.example.aoc24.util.OldMatrix
import com.example.aoc24.util.Point2D
import com.example.aoc24.util.allPath
import com.example.aoc24.util.search

class DaySolution10(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val oldMatrix = OldMatrix<Int>()
        private lateinit var graph: Graph10
        private val starts = mutableSetOf<Point2D>()
        private val ends = mutableSetOf<Point2D>()

        override fun handleLine(inputStr: String, pos: Int) {
            for (ch in inputStr) {
                val value = ch.toString().toInt()
                val newPoint = oldMatrix.getNextPoint(pos)

                if (value == 0) starts.add(newPoint)
                if (value == 9) ends.add(newPoint)
                oldMatrix.addToEnd(pos, ch.toString().toInt())
            }
            graph = Graph10(oldMatrix)
        }

        override fun finish() {

            for (start in starts) {
                for (end in ends) {
                    val search = graph.search(
                        startNode = Node(0, start),
                        startData = IntGraphData(0),
                        isFinish = { data -> data.node.point == end }
                    )
                    if (search.destination!=null){
                        intRes++
                    }
                }
            }


        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val oldMatrix = OldMatrix<Int>()
        private lateinit var graph: Graph10
        private val starts = mutableSetOf<Point2D>()
        private val ends = mutableSetOf<Point2D>()

        override fun handleLine(inputStr: String, pos: Int) {
            for (ch in inputStr) {
                val value = ch.toString().toInt()
                val newPoint = oldMatrix.getNextPoint(pos)

                if (value == 0) starts.add(newPoint)
                if (value == 9) ends.add(newPoint)
                oldMatrix.addToEnd(pos, ch.toString().toInt())
            }
            graph = Graph10(oldMatrix)
        }

        override fun finish() {
            for (start in starts) {
                for (end in ends) {
                    val search = graph.allPath(
                        startNode = Node(0, start),
                        startData = IntGraphData(0),
                        endNode = Node(9, end)
                    )
                        intRes+=search.size
                }
            }

        }

        override fun obtainResult(): String = intRes.toString()
    }

    data class Node(val value: Int, val point: Point2D)
    class Graph10(val oldMatrix: OldMatrix<Int>) : Graph<Node, IntGraphData> {

        override fun next(data: NodesWithData<Node, IntGraphData>): List<NodesWithData<Node, IntGraphData>> {
            return possibleDirPart1
                .map { it to data.node.point.toOldDirection(it) }
                .filter { it.second.inMatrix(oldMatrix) }
                .filter { oldMatrix.get(it.second) - data.node.value == 1 }
                .map {
                    val newVal = oldMatrix.get(it.second)
                    val nextNode = Node(newVal, it.second)
                    NodesWithData(data.node, nextNode, IntGraphData(newVal))
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
