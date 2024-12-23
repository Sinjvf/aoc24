package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Graph
import com.example.aoc24.util.GraphData
import com.example.aoc24.util.NodesWithData
import com.example.aoc24.util.searchLoop
import org.jgrapht.alg.clique.BronKerboschCliqueFinder
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

class DaySolution23(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val nodes = mutableMapOf<Node, MutableList<Node>>()
        private val gr = Graph23(nodes)

        override fun handleLine(inputStr: String, pos: Int) {
            val s = inputStr.split("-")
            val l = nodes.getOrPut(Node(s[0])) { mutableListOf() }.also { it.add(Node(s[1])) }
            nodes.put(Node(s[0]), l)

            val d = nodes.getOrPut(Node(s[1])) { mutableListOf() }.also { it.add(Node(s[0])) }
            nodes.put(Node(s[1]), d)
        }

        override fun finish() {

            println(nodes.size)
            var i = 0
            val set = mutableSetOf<Path>()
            nodes.forEach {
                val start = it.key
                val s = gr.searchLoop(
                    startNode = start,
                    startData = IntGraphData(1),
                )

                //       println()
                //    println(s)
                s.forEach {
                    if (it.isNotEmpty() && it.size == 3 && it.con()) {
                        set.add(Path(it))
                    }
                }
                if (i % 100 == 0) {
                    println(i)
                }
                i++
            }
            //     println("$set, ")
            intRes = set.size
        }

        private fun List<Pair<Node, Node>>.con() =
            any { it.first.s.startsWith("t") || it.second.s.startsWith("t") }

        override fun obtainResult(): String = intRes.toString()
    }

    data class Path(val l: Set<Node>) {
        constructor(l: List<Pair<Node, Node>>) : this(l.flatMap { listOf(it.first, it.second) }.toSet())
    }

    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val edges = mutableMapOf<Node, MutableList<Node>>()
        private val gr = Graph23(edges)

        private

        val graph = SimpleGraph<Node, DefaultEdge>(DefaultEdge::class.java)

        override fun handleLine(inputStr: String, pos: Int) {
            val s = inputStr.split("-")
            graph.addVertex(Node(s[0]))
            graph.addVertex(Node(s[1]))

            graph.addEdge(Node(s[0]), Node(s[1]))
        }

        override fun finish() {}

        override fun obtainResult(): String =
            BronKerboschCliqueFinder(graph).maxBy { it.size }.map { it.s }.sorted().joinToString(",")
    }

    data class Node(val s: String)

    class Graph23(val nodes: Map<Node, MutableList<Node>>) : Graph<Node, IntGraphData> {

        override fun next(data: NodesWithData<Node, IntGraphData>): List<NodesWithData<Node, IntGraphData>> {
            return nodes[data.node]!!.filter { it != data.node }.map {
                val nextNode = it
                NodesWithData(data.node, nextNode, IntGraphData(1))
            }
        }
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
