package com.example.aoc24.util

import java.util.LinkedList
import java.util.PriorityQueue

/**
 * @author Тамара Синева on 17.12.2023
 */
interface Graph<Node, Data : GraphData> {
    fun next(data: NodesWithData<Node, Data>): List<NodesWithData<Node, Data>>

    fun nextExcept(data: NodesWithData<Node, Data>, except:List<List<Node>>): List<NodesWithData<Node, Data>> = next(data)
}


interface GraphData {
    fun plus(a: GraphData): GraphData
    fun getLong(): Long
}

fun <Node, Data : GraphData> Graph<Node, Data>.search(
    startNode: Node,
    startData: Data,
    isFinish: (NodesWithData<Node, Data>) -> Boolean,
    onVisited: (NodesWithData<Node, Data>) -> Unit = { _ -> },
    heuristic: (NodesWithData<Node, Data>)  -> Data? = { null },
    isFFFinish: (NodesWithData<Node, Data>, Map<Node, Pair<Node, Data>>, PriorityQueue<NodesWithData<Node, Data>>) -> Boolean = { _, _, _ -> false },
    clear:(Node)->Unit = {}
): SearchResult<Node, Data> {
    val queue = PriorityQueue(compareBy<NodesWithData<Node, Data>> { it.data.getLong() })
    queue.add(NodesWithData(null, startNode, startData))
    val searchTree = mutableMapOf(startNode to (startNode to startData))

    while (true) {
        val currData = queue.poll() ?: return SearchResult(startNode, null, searchTree)
        onVisited(currData)

        if (isFinish(currData) || isFFFinish(currData, searchTree, queue)) return SearchResult(
            startNode,
            currData.node,
            searchTree
        )

        next(currData)
            .filter { it.node !in searchTree }
            .forEach { nextData ->
                val newData = currData.data.plus(nextData.data) as Data
                if (newData.getLong() <= (searchTree[nextData.node]?.second?.getLong() ?: Long.MAX_VALUE)) {
                    val middleData = NodesWithData(
                        currData.node,
                        nextData.node,
                        newData)
                    val dataWithHeu: Data = (heuristic(middleData)?.let { it.plus(newData) as Data }) ?: newData
                    queue.add(
                        NodesWithData(
                            currData.node,
                            nextData.node,
                            dataWithHeu,
                        )
                    )
                    searchTree[nextData.node] = currData.node.apply { clear(this) } to newData
                }
            }
    }
}

data class SearchPath<Node, Data : GraphData>(
    val path: List<Node>,
    val cost: Data,
) : List<Node> by path

fun <Node, Data : GraphData> SearchResult<Node, Data>.pathTo(node: Node): SearchPath<Node, Data>? {
    val reversed = reversedPathTo(node)
    val cost = reversed?.cost ?: return null
    val path = reversed.path.asReversed()
    return SearchPath(path, cost)
}

fun <Node, Data : GraphData> SearchResult<Node, Data>.reversedPathTo(node: Node): SearchPath<Node, Data>? {
    val cost = searchTree[node]?.second ?: return null
    val path = buildList {
        var current = node
        while (true) {
            add(current)
            val previous = searchTree.getValue(current).first
            if (previous == current) break
            current = previous
        }
    }
    return SearchPath(path, cost)
}


fun <Node, Data : GraphData> SearchResult<Node, Data>.path(): SearchPath<Node, Data>? =
    when (destination) {
        null -> null
        else -> pathTo(destination)
    }

data class SearchResult<Node, Data : GraphData>(
    val startedFrom: Node,
    val destination: Node?,
    val searchTree: Map<Node, Pair<Node, Data>>,
)

// возвращает "был ли он прерван"
fun <Node, Data : GraphData> Graph<Node, Data>.fullPath(
    startNode: Node,
    startData: Data,
    isFinish: (NodesWithData<Node, Data>) -> Boolean,
    onVisited: (NodesWithData<Node, Data>) -> Unit = { _ -> }
): Boolean {
    val queue =/* PriorityQueue(compareBy<Pair<Node, Data>> { it.second.getLong() })*/
        LinkedList<NodesWithData<Node, Data>>()
    queue.add(NodesWithData(null, startNode, startData))

    while (true) {
        val nodesWithData = queue.poll() ?: return false
        onVisited(nodesWithData)

        if (isFinish(nodesWithData)) return true

        next(nodesWithData)
            .forEach { newNData ->
                val newData = nodesWithData.data.plus(newNData.data) as Data
                queue.add(NodesWithData(nodesWithData.node, newNData.node, newData))
            }
    }
}

open class NodesWithData<Node, Data : GraphData>(
    open val prev: Node?,
    open val node: Node,
    open val data: Data,
) {
    override fun toString(): String {
        return /*"prev = $prev,*/ "node = $node, data = $data"
    }
}

// возвращает все найденные пути(nолько количество)
fun <Node, Data : GraphData> Graph<Node, Data>.allPath(
    startNode: Node,
    startData: Data,
    endNode: Node
): List<Node > {
    val paths = mutableListOf<List<Node>>()
    val queue = PriorityQueue(compareBy<NodesWithDataAndPath<Node, Data>> { it.data.getLong() })
    queue.add(NodesWithDataAndPath(null, startNode, startData, paths))
    val result = mutableListOf<Node>()

    while (true) {
        val nodesData = queue.poll() ?: return result
        if (nodesData.node==endNode){
            result.add(startNode)
        }

        next(nodesData)
            .forEach { newNData ->
                val newData = nodesData.data.plus(newNData.data) as Data
                queue.add(NodesWithDataAndPath(nodesData.node, newNData.node, newData, listOf()))
            }
    }
}

class NodesWithDataAndPath<Node, Data : GraphData>(
    override val prev: Node?,
    override val node: Node,
    override val data: Data,
    val paths: List<List<Node>>
):NodesWithData<Node, Data>(prev, node, data) {
    override fun toString(): String {
        return /*"prev = $prev,*/ "node = $node, data = $data"
    }
}


