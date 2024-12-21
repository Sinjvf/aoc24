package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Direction
import com.example.aoc24.util.Graph
import com.example.aoc24.util.GraphData
import com.example.aoc24.util.Matrix
import com.example.aoc24.util.NodesWithData
import com.example.aoc24.util.Point2D
import com.example.aoc24.util.findAll
import com.example.aoc24.util.path
import kotlin.math.min

class DaySolution21(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Long = 0
        private val codes = mutableListOf<String>()
        val keyboard1 = Matrix<Char>(3, 4, '.')
        val keyboard2 = Matrix(3, 2, '.')

        override fun handleLine(inputStr: String, pos: Int) {
            codes.add(inputStr)
        }

        override fun finish() {
            initKey()


            codes.forEach { code ->
                val first = compute(code, 'A', keyboard1, null, false)
                /*  println(first)
                  println()
                  println()
  */

                val robots = 1
                var nextInput = first
                var min: Long? = null

                for (i in 0..robots - 1) {
                    val iterator = nextInput.iterator()
                    min = null
                    nextInput = buildSet {
                        while (iterator.hasNext()) {

                            val next = iterator.next()
                            val nextS = compute(next, 'B', keyboard2, min, false)
                            addAll(nextS)
                            val minNext = nextS.minOf { it.length }.toLong()
                            min = min(minNext, min ?: Long.MAX_VALUE)

                            println("first   $next , next = $nextS, min = $min")
                        }
                    }.filter { it.length == (min?.toInt() ?: 0) }
                }
                val res = (min ?: 0) * code.substring(0..code.length - 2).toInt()
                println("${min} * ${code.substring(0..code.length - 2).toInt()} = $res")
                intRes += res
            }
        }

        private fun initKey() {
            keyboard1.remove(Point2D(0, 3))
            keyboard2.remove(Point2D(0, 0))
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes = 0L
        private val codes = mutableListOf<String>()
        val keyboard1 = Matrix<Char>(3, 4, '.')
        val keyboard2 = Matrix(3, 2, '.')

        override fun handleLine(inputStr: String, pos: Int) {
            codes.add(inputStr)
        }

        override fun finish() {
            initKey()
            codes.forEach { code ->
                val first = compute(code, 'A', keyboard1, null, false)

                val count = 25  //-2

                val minI: Long = first.minOf {
                    //compute(it, 'B', keyboard2).minOf {
                        pressString(it, count, 'B', keyboard2)
                 //   }

                }

                val res = minI * (code.substring(0..code.length - 2).toLong())
                println("${minI} * ${code.substring(0..code.length - 2).toInt()} = $res")
                intRes += res
            }
        }


               private fun pressString(str:String, count: Int, startChar: Char, keyboard: Matrix<Char>):Long{
                  return  compute(str, startChar, keyboard).map { nextS ->
                       var start = startChar
                       var size =0L
                      var out = "$nextS, ${count-1} = "
                       for (i in 0..nextS.length-1){
                           val nextSize = press(nextS[i], count-1, start, keyboard)

                            //   if(count<=2){
                                   start = nextS[i]
                         //      }
                           out+="(${nextS[i]}, $nextSize)"
                           size+=nextSize
                       }
                  //    println(out)
                 //  return size
                       size
                   }.min()
               }

        private fun press(char: Char, count: Int, startChar: Char, keyboard: Matrix<Char>): Long {

            if (count == 0) return 1
            val cac = cccc[Key2(startChar, char, count)]
            if (cac != null) return cac

            val all = getAllPath(startChar.toPos(), char.toPos(), keyboard)
            return (if (count == 1) {
                all.minOf { it.length }.toLong()
            } else {
                all.minOf {

                    pressString(it, count-1, 'B', keyboard)

                }
            }).also { cccc.put(Key2(startChar, char, count), it) }
        }

        private fun initKey() {
            keyboard1.remove(Point2D(0, 3))
            keyboard2.remove(Point2D(0, 0))
        }

        override fun obtainResult(): String = intRes.toString()
    }

    class Graph21(val matrix: Matrix<Char>) : Graph<Point2D, CharGraphData> {

        override fun next(data: NodesWithData<Point2D, CharGraphData>): List<NodesWithData<Point2D, CharGraphData>> {
            return possibleDirPart1
                .map { it to data.node.toDirection(it) }
                .filter { it.second.inMatrix(matrix) }
                .map {
                    val nextNode = it.second
                    NodesWithData(data.node, nextNode, CharGraphData(it.first.toString()))
                }
        }

        private val possibleDirPart1 =
            mutableSetOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
    }

    class CharGraphData(val i: String) : GraphData {
        override fun plus(a: GraphData): GraphData {
            if (a !is CharGraphData) throw IllegalArgumentException("expext IntGraphData class")

            return CharGraphData("$i${a.i}")
        }

        override fun getLong(): Long = i.length.toLong()

        override fun toString(): String {
            return i.toString()
        }
    }


    private fun compute(
        str: String,
        startChar: Char,
        keyboard: Matrix<Char>,
        min: Long? = null,
        cansplit: Boolean = false
    ): List<String> {
        if (str.isEmpty()) return listOf("")

                val pos = str.map { it.toPos() }
                var start = startChar.toPos()

                var paths = listOf<String>("")

                for (end in pos) {
                    //       println("$start -> $end")

                    paths = appendAllPaths(start, end, paths, keyboard, min, single = startChar != 'A')
                    if (paths.isEmpty()) return emptyList()

                    //        paths = results.mapNotNull { l -> paths.map { p -> "$p$l" } }.flatten().toSet()
                    start = end
                }
        return   paths
    }

    fun getAllPath(start: Point2D, end: Point2D, keyboard: Matrix<Char>): List<String> {
        val cached = cache[Key(start, end)]
        val keyChar: Char = 'B'
        return if (cached != null) {
            cached
        } else {
            val searches = Graph21(keyboard).findAll(
                startNode = start,
                startData = CharGraphData(""),
                end = end,
            )

            searches.map {
                (it.path()?.cost?.i + keyChar)
            }.toSet().also {
                cache.put(Key(start, end), it.toList())
            }.toList()
        }
    }

    private fun appendAllPaths(
        start: Point2D,
        end: Point2D,
        paths: List<String>,
        keyboard: Matrix<Char>,
        min: Long?,
        single: Boolean
    ): List<String> {

        return getAllPath(start, end, keyboard).map { l ->
            paths.map { p -> "$p$l" }
        }
            .flatten()
    }

    fun Char.toPos() =
        when (this) {
            '0' -> Point2D(1, 3)
            '1' -> Point2D(0, 2)
            '2' -> Point2D(1, 2)
            '3' -> Point2D(2, 2)
            '4' -> Point2D(0, 1)
            '5' -> Point2D(1, 1)
            '6' -> Point2D(2, 1)
            '7' -> Point2D(0, 0)
            '8' -> Point2D(1, 0)
            '9' -> Point2D(2, 0)
            'A' -> Point2D(2, 3)

            'B' -> Point2D(2, 0)
            '^' -> Point2D(1, 0)
            'v' -> Point2D(1, 1)
            '>' -> Point2D(2, 1)
            '<' -> Point2D(0, 1)
            else -> throw IllegalArgumentException("bad char $this")
        }

    val cache = mutableMapOf<Key, List<String>>()
    data class Key(val start: Point2D, val end: Point2D)
}

data class Key2(val start: Char, val end: Char, val count: Int)

private val cccc = mutableMapOf<Key2, Long>()

