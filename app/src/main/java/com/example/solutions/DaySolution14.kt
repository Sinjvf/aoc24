package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.OldMatrix
import com.example.aoc24.util.Point2D

class DaySolution14(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val robots = mutableListOf<Robot>()

        private val oldMatrix = OldMatrix(101, 103, ".")

        // private val matrix = Matrix(11, 7, ".")
        private val sec = 100

        override fun handleLine(inputStr: String, pos: Int) {
            val split = inputStr.split(" ")

            val regexFull = """(?i)-?\d{1,6}""".toRegex()
            val robPos = regexFull.findAll(split[0]).map { it.value.toInt() }.toList()
            val robVel = regexFull.findAll(split[1]).map { it.value.toInt() }.toList()

            robots.add(Robot(pos = Point2D(robPos[0], robPos[1]), vel = Point2D(robVel[0], robVel[1])))
        }

        override fun finish() {
            //  matrix.put(robots[0].pos ,"#")

            //   matrix.print(logger)
            toMatrix()
            println()
            println()
            for (i in 1..sec) {

                println(robots)
                robots.forEachIndexed { id, rob ->
                    robots[id] = singleMove(rob)
                }
                println(i)
                println()
                toMatrix()
            }

            intRes = robots
                .groupBy { it.pos.toQuad() }
                .filter { it.key != 5 }
                .map { it.value.size }
                .fold(1) { i, res -> i * res }
            toMatrix()
            //  matrix.print(logger)
        }

        private fun singleMove(rob: Robot): Robot {
            var pos = rob.pos.plus(rob.vel)
            if (!pos.inMatrix(oldMatrix)) {
                pos = Point2D((pos.x + oldMatrix.ySize) % oldMatrix.ySize, (pos.y + oldMatrix.xSize) % oldMatrix.xSize)
            }

            return Robot(pos = pos, vel = rob.vel)
        }

        fun toMatrix() {
            val oldMatrix1 = OldMatrix(oldMatrix.ySize, oldMatrix.xSize, ".")
            robots
                .map {
                    if (oldMatrix1.get(it.pos) == ".") {
                        oldMatrix1.put(it.pos, "1")
                    } else {
                        val num = oldMatrix1.get(it.pos).toInt()
                        oldMatrix1.put(it.pos, (num + 1).toString())
                    }
                    it
                }
            oldMatrix1.print(logger = logger)
        }

        override fun obtainResult(): String = intRes.toString()

        private fun Point2D.toQuad(): Int {
            return when {
                (x < oldMatrix.ySize / 2 && y < oldMatrix.xSize / 2) -> 1
                (x > oldMatrix.ySize / 2 && y < oldMatrix.xSize / 2) -> 2
                (x < oldMatrix.ySize / 2 && y > oldMatrix.xSize / 2) -> 3
                (x > oldMatrix.ySize / 2 && y > oldMatrix.xSize / 2) -> 4
                else -> 5
            }
        }
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val robots = mutableListOf<Robot>()

        private val oldMatrix = OldMatrix(101, 103, ".")

        override fun handleLine(inputStr: String, pos: Int) {
            val split = inputStr.split(" ")

            val regexFull = """(?i)-?\d{1,6}""".toRegex()
            val robPos = regexFull.findAll(split[0]).map { it.value.toInt() }.toList()
            val robVel = regexFull.findAll(split[1]).map { it.value.toInt() }.toList()

            robots.add(Robot(pos = Point2D(robPos[0], robPos[1]), vel = Point2D(robVel[0], robVel[1])))
        }

        override fun finish() {
            toMatrix()
            println()
            println()
            var i = 1
            while (i < 10000) {

                robots.forEachIndexed { id, rob ->
                    robots[id] = singleMove(rob)
                }
                if (tree()) {
                    println(i)
                    println()
                    toMatrix()

                    intRes = i
                    return
                }
                i++
            }
        }

        private fun tree(): Boolean {
            /*
            * Вначале нашла елку глазами, а потом уже подгоняла функцию под ее вид =(
            */

            val x = robots.groupBy { it.pos.x }
            val max = x.maxOf { it.value.size }
            if (max < 23) return false
            if (x.filter { it.value.size > 23 - 1 }.size < 3) return false


            if (x.filter { inLine(33, it.value) }.size < 2) return false
            if (x.filter { inLine(23, it.value) }.size < 3) return false
            if (x.filter { inLine(22, it.value) }.size < 5) return false

            return true
        }

        private fun inLine(num: Int, robots: List<Robot>): Boolean {
            for (i in 0..oldMatrix.xSize - num) {
                if (inLineFrom(i, num, robots)) return true
            }
            return false
        }

        private fun inLineFrom(y: Int, num: Int, robots: List<Robot>): Boolean {
            var j = 0
            while (j < num) {
                robots.find { it.pos.y == y + j } ?: return false
                j++
            }
            return true
        }

        private fun singleMove(rob: Robot): Robot {
            var pos = rob.pos.plus(rob.vel)
            if (!pos.inMatrix(oldMatrix)) {
                pos = Point2D((pos.x + oldMatrix.ySize) % oldMatrix.ySize, (pos.y + oldMatrix.xSize) % oldMatrix.xSize)
            }

            return Robot(pos = pos, vel = rob.vel)
        }

        fun toMatrix() {
            val oldMatrix1 = OldMatrix(oldMatrix.ySize, oldMatrix.xSize, ".")
            robots
                .map {
                    if (oldMatrix1.get(it.pos) == ".") {
                        oldMatrix1.put(it.pos, "1")
                    } else {
                        val num = oldMatrix1.get(it.pos).toInt()
                        oldMatrix1.put(it.pos, (num + 1).toString())
                    }
                    it
                }
            oldMatrix1.printMirrow(logger = logger)
        }

        override fun obtainResult(): String = intRes.toString()
    }

    private data class Robot(val pos: Point2D, val vel: Point2D) {
        override fun toString(): String {
            return pos.toString()
        }
    }

}
