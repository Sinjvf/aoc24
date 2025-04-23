package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Matrix
import com.example.aoc24.util.Point2D

class DaySolution25(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        val matrixs = mutableListOf<Matrix<Node>>()

        var current: Matrix<Node> = Matrix()
        val keys = mutableListOf<List<Int>>()

        val locks = mutableListOf<List<Int>>()
        var raw = 0

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) {
                matrixs.add(current)
                current = Matrix()
                raw = 0
                return
            }

            val col = inputStr.toCharArray().toList().mapIndexed { id, it ->
                val point = Point2D(id, pos)
                when (it) {
                    '#' -> Node.Wall
                    else -> Node.Space
                }

            }

            current.addRaw(raw, col)
            raw++
        }

        override fun finish() {
            matrixs.add(current)
            matrixs.forEach {
               // it.print(logger)
               // println()
                if (it.getRaw(0).all { it==Node.Wall }) {
                    locks.add(buildList {
                        for(i in 0..it.xSize-1){
                            add(it.getCol(i).filter { it==Node.Wall }.count())
                        }
                    })
                }else{
                    keys.add(buildList {
                        for(i in 0..it.xSize-1){
                            add(it.getCol(i).filter { it==Node.Wall }.count())
                        }
                    })
                }
            }

            locks.forEach { lock->
                keys.forEach { key->
                    val check = lock.mapIndexed {id,  it->
                        it+key[id]
                    }
             //       println(check)
                    if (check.all{it<=7}) {
                        intRes++
                    }
                }
            }
       //     println(locks.size)
        //    println(keys.size)


        }

        override fun obtainResult(): String = intRes.toString()
    }


    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0

        override fun handleLine(inputStr: String, pos: Int) {
        }

        override fun finish() {
        }

        override fun obtainResult(): String = intRes.toString()
    }

    sealed class Node() {
        open fun print() = toString()

        object Wall : Node() {
            override fun toString(): String {
                return "#"
            }
        }

        object Space : Node() {
            override fun toString(): String {
                return "."
            }

            override fun print(): String {
                return "."
            }
        }
    }
}
