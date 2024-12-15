package com.example.solutions

import com.example.ILogger
import com.example.aoc24.util.Direction
import com.example.aoc24.util.OldMatrix
import com.example.aoc24.util.Point2D
import com.example.aoc24.util.PositionData

class DaySolution4(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val oldMatrix = OldMatrix<Char>()

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
            oldMatrix.addRaw(pos, inputStr.toList())
            intRes += countInStr(inputStr, "row")
        }

        override fun finish() {
            //get col

            println("----COLUMNS----")
            for (i in 0 until oldMatrix.xSize) {
                val column = buildString { oldMatrix.getCol(i).forEach { append(it) } }

                intRes += countInStr(column, "column")
            }


            println("----DIAGONALS----")
            oldMatrix.onEachDiagonal {
                val diagonalStr = buildString { it.forEach { append(it) } }
                intRes += countInStr(diagonalStr, "diagonal")
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val oldMatrix = OldMatrix<Char>()

        override fun handleLine(inputStr: String, pos: Int) {
            oldMatrix.addRaw(pos, inputStr.toList())
        }

        override fun finish() {

            val iterator = oldMatrix.iterator()
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

            addtoSet(positionData.pos.toOldDirection(Direction.UP).toOldDirection(Direction.LEFT), set1)
            addtoSet(positionData.pos.toOldDirection(Direction.DOWN).toOldDirection(Direction.RIGHT), set1)

            addtoSet(positionData.pos.toOldDirection(Direction.DOWN).toOldDirection(Direction.LEFT), set2)
            addtoSet(positionData.pos.toOldDirection(Direction.UP).toOldDirection(Direction.RIGHT), set2)
            return checkSet(set1) && checkSet(set2)
        }

        private fun checkSet(set: Set<Char>) =
            set.size == 2 && set.contains('M') && set.contains('S')

        private fun addtoSet(p: Point2D, set: MutableSet<Char>) {
            if (oldMatrix.pointInRange(p)) {
                set.add(oldMatrix.get(p))
            }
        }


        override fun obtainResult(): String = intRes.toString()
    }
}
