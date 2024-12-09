package com.example.solutions

import com.example.ILogger

class DaySolution9(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Long = 0

        override fun handleLine(_inputStr: String, pos: Int) {
            val inputStr = _inputStr.map { it.toString().toInt() }
            val data = buildList {
                for (i in 0..((inputStr.size.toFloat()) / 2).toInt()) {
                    if (i * 2 >= inputStr.size) break
                    val block = inputStr[i * 2]
                    repeat(block) {
                        add(Node.File(i))
                    }
                    val spacePos = i * 2 + 1
                    if (spacePos < inputStr.size) {
                        repeat(inputStr[spacePos]) {
                            add(Node.Space)
                        }
                    }
                }
            }
     //       println(data)
                  buildFile(data)
        }

        private fun buildFile(str: List<Node>) {
            val last = str.size - 1
            var i = 0
            var j = last

            while (i <= j) {
                while (true) {
                    if (i > j) break
                    if (str[i] == Node.Space) {
                        break
                    } else {
                        intRes += i * ((str[i] as Node.File).num)
                    }
                    i++
                }

                while (true) {
                    if (j < i) break
                    if (str[j] != Node.Space) break
                    j--
                }

                if (i <= j && str[i] == Node.Space && str[j] != Node.Space) {
                    intRes += i * ((str[j] as Node.File).num)
                }
                i++
                j--
            }
        }

        override fun finish() {
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Long = 0

        override fun handleLine(_inputStr: String, pos: Int) {
            val inputStr = _inputStr.map { it.toString().toInt() }
            val data = buildList {
                for (i in 0..((inputStr.size.toFloat()) / 2).toInt()) {
                    if (i * 2 >= inputStr.size) break
                    val block = inputStr[i * 2]
                    add(Node2.File(i, block))
                    val spacePos = i * 2 + 1
                    if (spacePos < inputStr.size && inputStr[spacePos] > 0) {
                        add(Node2.Space(inputStr[spacePos]))
                    }
                }
            }
        //    println(data)
            buildList2(data)
        }

        private fun buildList2(list: List<Node2>) {

            val last = list.size - 1
            var j = last

            var newList = mutableListOf<Node2>()
            newList.addAll(list)


            while (j > 0) {
                while (true) {
                    if (j < 0) break
                    if (list[j] is Node2.File) break
                    j--
                }

                newList = rebuildList(j, newList)
                j--
            }

            val buildLit = newList
                .map {
                    it.toNode()
                }
                .flatten()
    //        println(buildLit)
            buildLit
                .forEachIndexed { index, node ->
                    if (node is Node.File) {
                        intRes += index.toLong() * node.num
                    }
                }
        }

        private fun rebuildList(j: Int, list: List<Node2>): MutableList<Node2> {

            val newList = mutableListOf<Node2>()
            newList.addAll(list)
            if (newList[j] is Node2.File) {
                for (i in 0..j) {
                    val nodei = newList[i]
                    val nodej = newList[j]
                    if (nodei is Node2.Space && nodei.count >= nodej.count) {
                        newList[j] = Node2.Space(nodej.count)
                        if (nodei.count > nodej.count) {
                            newList[i] = Node2.Combined(mutableListOf(nodej, Node2.Space(nodei.count - nodej.count)))
                        }else{
                            newList[i] = nodej
                        }

                    //    println("_j = $j    " + newList)
                        break
                    }
                    if (nodei is Node2.Combined && nodei.count >= nodej.count) {
                        newList[j] = Node2.Space(nodej.count)
                        if (nodei.count > nodej.count) {
                            val space = Node2.Space(nodei.count - nodej.count)
                            (newList[i] as Node2.Combined).data[nodei.data.size - 1] = nodej
                            (newList[i] as Node2.Combined).data.add(space)
                        }else{
                            (newList[i] as Node2.Combined).data[nodei.data.size - 1] = nodej
                            (newList[i] as Node2.Combined).data.add(Node2.Space(0))
                        }

                  //      println("j = $j    " + newList)
                        break
                    }
                }
            }
            return newList
        }

        override fun finish() {
        }

        override fun obtainResult(): String = intRes.toString()
    }

    sealed class Node() {
        object Space : Node() {
            override fun toString(): String {
                return "."
            }
        }

        data class File(val num: Int) : Node() {
            override fun toString(): String {
                return "$num"
            }
        }
    }

    sealed class Node2() {
        abstract val count: Int
        data class Space(override val count: Int) : Node2() {
            override fun toString(): String {
                return buildString { repeat(count) { append(".") } }
            }
        }

        data class File(val num: Int, override val count: Int) : Node2() {
            override fun toString(): String {
                return buildString { repeat(count) { append(num) } }
            }
        }

        data class Combined(val data: MutableList<Node2>) : Node2() {
            override val count: Int
                get() = data.filterIsInstance<Space>().fold(0) { i, j -> i + j.count }
            override fun toString(): String {
                return buildString { append("{")
                    data.forEach { append(it.toString()) }
                    append("}")
                }
            }
        }
    }

    fun Node2.toNode(): List<Node> = when (this) {
        is Node2.File -> buildList { repeat(count) { add(Node.File(num)) } }
        is Node2.Space -> buildList { repeat(count) { add(Node.Space) } }
        is Node2.Combined -> buildList { data.forEach { addAll(it.toNode()) }}.also {
          //  println(it)
        }
    }
}
