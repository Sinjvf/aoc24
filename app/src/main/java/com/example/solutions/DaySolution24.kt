package com.example.solutions

import com.example.ILogger

class DaySolution24(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Long = 0
        var q = true
        private val input = mutableListOf<Node>()
        private val output = mutableListOf<Out>()

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) {
                q = false
                return
            }
            if (q) {
                val s = inputStr.split(": ")
                input.add(Node(s[0], s[1] == "1"))
            } else {
                val s = inputStr.split(" ")
                output.add(Out(Node(s[0]), Node(s[2]), s[1].toOp(), Node(s[4])))
            }
        }

        override fun finish() {
            val full = output.size
            var handled = 0
            println("full = ${output.size}")
            while (handled != full) {
                output.forEach {
                    if (!it.handled) {
                        if (input.contains(it.f) && input.contains(it.s)) {
                            it.res.v = it.op.op(input.first { inp -> inp == it.f }, input.first { inp -> inp == it.s })
                            input.add(it.res)
                            it.handled = true
                            handled++
                        }
                    }
                }
                println(handled)
            }
            val z = input.filter { it.s.startsWith("z") }.sortedBy { it.s }.reversed()
            println(z)
            val str = z.map { if (it.v) "1" else "0" }.joinToString("")
            println(str)
            intRes = str.toLong(2)
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        var q = true
        private val _input = mutableListOf<Node>()
        private var input = mutableListOf<Node>()
        private val output = mutableMapOf<Node, Out>()

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) {
                q = false
                return
            }
            if (q) {
                val s = inputStr.split(": ")
                _input.add(Node(s[0], s[1] == "1"))
            } else {
                val s = inputStr.split(" ")
                output.put(Node(s[4]), Out(Node(s[0]), Node(s[2]), s[1].toOp(), Node(s[4])))
            }
            input = _input.toMutableList()
        }

        override fun finish() {
            var handled = 0
            println("full = ${output.size}")
            while (handled != output.size) {
                output.values.forEach {
                    if (!it.handled) {
                        if (input.contains(it.f) && input.contains(it.s)) {
                            it.res.v = it.op.op(input.first { inp -> inp == it.f }, input.first { inp -> inp == it.s })
                            input.add(it.res)
                            it.handled = true
                            handled++
                        }
                    }
                }
                //   println(handled)
            }

            val z = input.filter { it.s.startsWith("z") }
                .sortedBy { it.s }/*.map { it.v }*//*.map { if(it.v) "1" else "0" }*/
            /*      val x = input.filter { it.s.startsWith("x") }
                      .sortedBy { it.s }*//*.map { it.v }*//**//*.map { if(it.v) "1" else "0" }*//*
            val y = input.filter { it.s.startsWith("y") }
                .sortedBy { it.s }*//*.map { it.v }*//**//*.map { if(it.v) "1" else "0" }*/
//println(_input)
            val badZ = mutableSetOf<Node>()
            val good = mutableSetOf<Node>()
            var id = 0
            var size = 4

            while (id < z.size) {
                val it = z[id]
                val expandSize = it.expandSize(_input, output)
                //   println("$it  = ${expandSize} ${it.expand(_input, output)}")

                /*     if (id > 0) {
                         if (expandSize != size) {
                             badZ.add(it)
                             size = expandSize
                             it.expand(_input, output) { output[it]?.setGood(false) }
                         } else {
                             it.expand(_input, output) { output[it]?.setGood(true) }
                         }
                         size += 4
                     }*/

                val expand = it.expand(_input, output)
                val builded = buildZ(id, id == z.size - 1)
                if (expand != builded) {
                    println("$it  = ${expandSize})")
                    /*   println(" expand - ${expand}")
                       println(" bilded - ${builded}")*/
                    val r4 = buildR(id - 1)
                    val fr4 = findNode(r4)
                    println("fr4 = ${fr4.res}")
                    val cxor = Out(nodex(id), nodey(id), "XOR".toOp())
                    val fxor = findNode(cxor)

                    println("fxor = ${fxor.res}")
                    val find = findNode(builded)

                    println("find = ${find.res}")
                    val newn = output[it]?.copy()!!
                    output[it] = Out(find.f, find.s, find.op, it)
                    output[find.res] = Out(newn.f, newn.s, newn.op, newn.res)
                }

                //     println()
                id++
            }

            /*  val out = output.filter { it.key.s=="frn" ||it.key.s=="z05" ||it.key.s=="gmq"||it.key.s=="z21" }.flatMap {
                  listOf(it.value.f, it.value.s)
              }.map { (it as Node).s }.sorted().joinToString(",")*/
            // println(badZ)
            // println(out)

            /*var r = false
            val badOp = mutableListOf <Node>()
            for (i in 0..z.size-1){
                if (x[i].v xor y[i].v xor r){
                    badOp.addAll(output.filter { it.res== z[i] }.map { it.res })
                    r = x[i].v && y[i].v
                }
            }
            println(badOp)*/
        }

        private fun findNode(out: Out) =
            output.filter { it.value.expand(_input, output) == out }.toList().first().second

        override fun obtainResult(): String = listOf("frn", "z05", "gmq", "z21", "wtt", "z39", "vtj", "wnf").sorted().joinToString (",")
    }

    /*  fun toInputOperations(out: Out):Out {

      }*/

    fun nodex(i: Int) = Node(String.format("x%02d", i))
    fun nodey(i: Int) = Node(String.format("y%02d", i))

    fun buildZ(i: Int, last: Boolean = false): Out {
        if (i == 0) return Out(Node("x00"), Node("y00"), Op("XOR"))
        val r = buildR(i - 1)
        if (!last) {
            val xorOp = Out(nodex(i), nodey(i), "XOR".toOp())
            return Out(xorOp, r, "XOR".toOp())
        } else {
            return r
        }
    }

    fun buildR(i: Int): Out {
        if (i == 0) return Out(Node("x00"), Node("y00"), Op("AND"))

        val andOp = Out(buildR(i - 1), Out(nodex(i), nodey(i), Op("XOR")), Op("AND"))
        return Out(andOp, Out(nodex(i), nodey(i), Op("AND")), Op("OR"))
    }

    fun String.toOp(): Op = Op(this)

    data class Op(val s: String) {
        val op: (Node, Node) -> Boolean = when (s) {
            "AND" -> { n1, n2 -> n1.v && n2.v }
            "OR" -> { n1, n2 -> n1.v || n2.v }
            "XOR" -> { n1, n2 -> n1.v xor n2.v }
            else -> throw IllegalArgumentException("bad operation $this")
        }

        override fun toString(): String {
            return s
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Op

            return s == other.s
        }

        override fun hashCode(): Int {
            return s.hashCode()
        }
    }

    data class Node(val s: String, var v: Boolean = false) : Nodable {
        private var size = 0
        private var exp: Nodable? = null
        override fun expandable(input: List<Node>) = !input.contains(this)

        override fun expand(input: List<Node>, out: MutableMap<Node, Out>, onEach: (Nodable) -> Unit): Nodable {

            onEach(this)
            if (exp != null) return exp!!
            else {

                return (if (!expandable(input)) {
                    this
                } else {

                    val next = out[this]!!
                    Out(next.f.expand(input, out, onEach), next.s.expand(input, out, onEach), next.op, next.res)
                }).also {
                    exp = it
                }
            }
        }

        override fun expandSize(input: List<Node>, out: Map<Node, Out>): Int {
            if (size != 0) return size
            if (!expandable(input)) return 1

            val next = out[this]!!
            return next.f.expandSize(input, out) + next.s.expandSize(input, out)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Node

            return s == other.s
        }

        override fun hashCode(): Int {
            return s.hashCode()
        }

        override fun toString(): String {
            return "$s"
        }
    }

    data class Out(val f: Nodable, val s: Nodable, val op: Op, val res: Node = Node("")) : Nodable {
        private var size = 0
        var handled: Boolean = false
        var good: Boolean? = null
        var exp: Nodable? = null
        fun setGood(boolean: Boolean) {
            if (good == null) {
                good = boolean
                if (good == false) println(res)
            }
        }

        override fun expandable(input: List<Node>): Boolean = f.expandable(input) || s.expandable(input)
        override fun expand(input: List<Node>, out: MutableMap<Node, Out>, onEach: (Nodable) -> Unit): Nodable {

            onEach(this)
            if (exp != null) return exp!!
            if (!expandable(input)) return this

            val nextF = if (f.expandable(input)) {
                val expandf = out[f]!!
                Out(expandf.f.expand(input, out, onEach), expandf.s.expand(input, out, onEach), expandf.op, expandf.res)
            } else {
                f
            }
            val nextS = if (s.expandable(input)) {
                val expandS = out[s]!!
                Out(expandS.f.expand(input, out, onEach), expandS.s.expand(input, out, onEach), expandS.op, expandS.res)
            } else {
                s
            }
            return Out(nextF, nextS, op, res).also { exp = it }
        }

        override fun expandSize(input: List<Node>, out: Map<Node, Out>): Int {
            if (size != 0) return size
            if (!expandable(input)) return 2

            val nextF: Int = if (f.expandable(input)) {
                val expandf = out[f]!!
                expandf.f.expandSize(input, out) + expandf.s.expandSize(input, out)
            } else {
                1
            }
            val nextS: Int = if (s.expandable(input)) {
                val expandS = out[s]!!
                expandS.f.expandSize(input, out) + expandS.s.expandSize(input, out)
            } else {
                1
            }
            return nextF + nextS
        }

        override fun toString(): String {
            return "($f $op $s)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Out

            if (!((f == other.f && s == other.s) || (f == other.s && s == other.f))) return false
            if (op != other.op) return false

            return true
        }

        override fun hashCode(): Int {
            var result = f.hashCode()
            result += s.hashCode()
            result += 31 * result + op.hashCode()
            return result
        }
    }

    interface Nodable {
        fun expandable(input: List<Node>): Boolean
        fun expand(input: List<Node>, out: MutableMap<Node, Out>, onEach: (Nodable) -> Unit = {}): Nodable

        fun expandSize(input: List<Node>, out: Map<Node, Out>): Int
    }
}
