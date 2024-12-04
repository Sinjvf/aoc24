package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Direction
import com.example.aoc24.util.Matrix
import com.example.aoc24.util.Point2D
import com.example.aoc24.util.PositionData

class DaySolution4(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val matrix = Matrix<Char>()

        fun countInStr(inputStr: String, type: String = ""): Int {
            var copyStr = inputStr
            var count: Int = 0
            val list = mutableListOf<String>()
            val regexFull = """(?i)(XMAS)|(SAMX)""".toRegex()
            while (regexFull.containsMatchIn(copyStr)) {
                val replace = if (
                    regexFull.find(copyStr)?.value == "XMAS") {
                    ".S"
                } else {
                    ".X"
                }
                count++

                copyStr = regexFull.replaceFirst(copyStr, replace)
            }
            return count
        }

        override fun handleLine(inputStr: String, pos: Int) {
            matrix.addRaw(pos, inputStr.toList())
            intRes += countInStr(inputStr, "row")
        }

        override fun finish() {
            //get col

            println("----COLUMNS----")
            for (i in 0 until matrix.xSize) {
                val column = buildString { matrix.getCol(i).forEach { append(it) } }

                intRes += countInStr(column, "column")
            }


            println("----DIAGONALS----")
            matrix.onEachDiagonal {
                val diagonalStr = buildString { it.forEach { append(it) } }
                intRes += countInStr(diagonalStr, "diagonal")
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val matrix = Matrix<Char>()

        override fun handleLine(inputStr: String, pos: Int) {
            matrix.addRaw(pos, inputStr.toList())
        }

        override fun finish() {

            val iterator = matrix.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (next.data=='A' && checkXmas(next)) {
                    println(next.pos)
                    intRes++
                }
            }

        }

        fun checkXmas(positionData: PositionData<Char>): Boolean {
            val set1 = mutableSetOf<Char>()
            val set2 = mutableSetOf<Char>()

            addtoSet(positionData.pos.toDirection(Direction.UP).toDirection(Direction.LEFT), set1)
            addtoSet(positionData.pos.toDirection(Direction.DOWN).toDirection(Direction.RIGHT), set1)

            addtoSet(positionData.pos.toDirection(Direction.DOWN).toDirection(Direction.LEFT), set2)
            addtoSet(positionData.pos.toDirection(Direction.UP).toDirection(Direction.RIGHT), set2)
            return checkSet(set1) && checkSet(set2)
        }

        private fun checkSet(set: Set<Char>) =
            set.size == 2 && set.contains('M') && set.contains('S')

        private fun addtoSet(p: Point2D, set: MutableSet<Char>) {
            if (matrix.pointInRange(p)) {
                set.add(matrix.get(p))
            }
        }


        override fun obtainResult(): String = intRes.toString()
    }
}
