package com.example.solutions

import com.example.ILogger

class DaySolution7(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Long = 0

        override fun handleLine(inputStr: String, pos: Int) {
            val res = inputStr.split(":").filter { it.isNotEmpty() }
            intRes += compute(res[0].toLong(), res[1].split(" ").filter { it.isNotEmpty() }.map { it.toLong() })

        }

        private fun compute(res: Long, nums: List<Long>): Long {
            val finder = FindOps(res, nums)
            return if (finder.findOperators()) res
            else 0
        }


        override fun finish() {
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Long = 0

        override fun handleLine(inputStr: String, pos: Int) {
            val res = inputStr.split(":").filter { it.isNotEmpty() }
            intRes += compute(res[0].toLong(), res[1].split(" ").filter { it.isNotEmpty() }.map { it.toLong() })
        }

        private fun compute(res: Long, nums: List<Long>): Long {
            val finder = FindOpsWithConcat(res, nums)
            return if (finder.findOperators()) {
                println(res)
                res
            }
            else 0
        }

        override fun finish() {
        }

        override fun obtainResult(): String = intRes.toString()
    }
}

class FindOps(val res: Long, val nums: List<Long>) {

    fun findOperators(): Boolean = handleNode(nums[0], 0)

    private fun handleNode(currentRes: Long, level: Int): Boolean {
        if (level == nums.size - 1) return currentRes == res
        return handleNode(currentRes * nums[level + 1], level + 1) ||
            handleNode(currentRes + nums[level + 1], level + 1)
    }
}

class FindOpsWithConcat(val res: Long, val nums: List<Long>) {

    fun findOperators(): Boolean = handleNode(0, -1)

    private fun handleNode(currentRes: Long, level: Int): Boolean {
        if (level >= nums.size - 1) return currentRes == res
        return handleNode(conc(currentRes,  nums[level + 1]), level + 1)
            || handleNode(currentRes * nums[level + 1], level + 1)
            || handleNode(currentRes + nums[level + 1], level + 1)
    }
}
fun conc(a1: Long, a2: Long?) =
    if (a2 == null) a1
    else (a1.toString() + a2.toString()).toLong()
