package com.example.solutions

import com.example.ILogger
import kotlin.math.pow

class DaySolution17(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private var program = listOf<Long>()
        private val abc = mutableListOf<Long>(0L, 0L, 0L)
        private val output = mutableListOf<Long>()

        override fun handleLine(inputStr: String, pos: Int) {
            when {
                inputStr.isEmpty() -> return
                inputStr.contains("Register A:") ->
                    abc[0] = inputStr.split(": ")[1].toLong()

                inputStr.contains("Register B:") ->
                    abc[1] = inputStr.split(": ")[1].toLong()

                inputStr.contains("Register C:") ->
                    abc[2] = inputStr.split(": ")[1].toLong()

                inputStr.contains("Program:") ->
                    program = inputStr.split(": ")[1].split(",").map { it.toLong() }
            }
        }

        override fun finish() {

            var i = 0
            while (i < program.size) {
                val out = program[i].toCode().calc(abc, program, i)
                i = out.pointer
                output.addAll(out.list)
            }
            println("abc = $abc")
        }

        override fun obtainResult(): String = output.joinToString(",")
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Long = 0
        private var program = listOf<Long>()
        private val abc = mutableListOf(0L, 0L, 0L)
        private val output = mutableListOf<Long>()

        lateinit var safeABC: List<Long>

        override fun handleLine(inputStr: String, pos: Int) {
            when {
                inputStr.isEmpty() -> return
                inputStr.contains("Register A:") ->
                    abc[0] = inputStr.split(": ")[1].toLong()

                inputStr.contains("Register B:") ->
                    abc[1] = inputStr.split(": ")[1].toLong()

                inputStr.contains("Register C:") ->
                    abc[2] = inputStr.split(": ")[1].toLong()

                inputStr.contains("Program:") ->
                    program = inputStr.split(": ")[1].split(",").map { it.toLong() }
            }
        }

        override fun finish() {
            safeABC = listOf(abc[0], abc[1], abc[2])
            reverse()
        }

        fun reverse() {
            var a = 0L
            var k = program.size - 1
            while (k >= 0) {
                a = a*8
            //    var j = 0L
               // val start = a//a.asReversed().fold(0L) { res, it -> res * 8 + it }*8
                val sublist = program.subList(k, program.size )
                while (true) {
                    run(a, safeABC[1], safeABC[2])

                   // println("a = $a, j = $j, k = $k, $sublist")
                    if (output.equals(sublist)) {
                    //    println("a = $a, j = $j")
                        break
                    }
                    a++
                }
                k--
            }
            println(a)
            intRes = a
        }


        private fun run(a: Long, b: Long, c: Long) {
            var i = 0
            output.clear()

            abc[0] = a
            abc[1] = b
            abc[2] = c
            while (i < program.size) {
                val out = program[i].toCode().calc(abc, program, i)
                i = out.pointer
                output.addAll(out.list)
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }

    class Combo(val code: Long) {
        fun cacl(a: Long, b: Long, c: Long): Long =
            when (code.toInt()) {
                0, 1, 2, 3 -> code
                4 -> a
                5 -> b
                6 -> c
                else -> throw IllegalArgumentException("bad Combo $code")
            }
    }

    private data class Output(
        val pointer: Int,
        val list: List<Long> = listOf()
    )

    private fun Long.toCode() = when (this) {
        0L -> Code.adv()
        1L -> Code.bxl()
        2L -> Code.bst()
        3L -> Code.jnz()
        4L -> Code.bxc()
        5L -> Code.out()
        6L -> Code.bdv()
        7L -> Code.cdv()
        else -> throw IllegalArgumentException("bad command $this")
    }

    private sealed class Code(val code: Int) {
        abstract fun calc(abc: MutableList<Long>, operands: List<Long>, pointer: Int): Output
        class adv() : Code(0) {
            override fun calc(abc: MutableList<Long>, operands: List<Long>, pointer: Int): Output {
                abc[0] =
                    (abc[0] / (2.0.pow(Combo(operands[pointer + 1]).cacl(abc[0], abc[1], abc[2]).toInt()))).toLong()
                return Output(pointer = pointer + 2)
            }
        }

        class bxl() : Code(1) {
            override fun calc(abc: MutableList<Long>, operands: List<Long>, pointer: Int): Output {
                abc[1] = abc[1].xor(operands[pointer + 1].toLong())
                return Output(pointer = pointer + 2)
            }
        }

        class bst() : Code(2) {
            override fun calc(abc: MutableList<Long>, operands: List<Long>, pointer: Int): Output {
                abc[1] = Combo(operands[pointer + 1] % 8).cacl(abc[0], abc[1], abc[2]) % 8
                return Output(pointer = pointer + 2)
            }
        }

        class jnz() : Code(3) {
            override fun calc(abc: MutableList<Long>, operands: List<Long>, pointer: Int): Output {
                if (abc[0] == 0L) return Output(pointer = pointer + 2)
                return Output(pointer = operands[pointer + 1].toInt())
            }
        }

        class bxc() : Code(4) {
            override fun calc(abc: MutableList<Long>, operands: List<Long>, pointer: Int): Output {
                abc[1] = abc[1].xor(abc[2])
                return Output(pointer = pointer + 2)
            }
        }

        class out() : Code(5) {
            override fun calc(abc: MutableList<Long>, operands: List<Long>, pointer: Int): Output {

                return Output(
                    pointer = pointer + 2,
                    listOf(Combo(operands[pointer + 1] % 8).cacl(abc[0], abc[1], abc[2]) % 8)
                )
            }
        }

        class bdv() : Code(6) {
            override fun calc(abc: MutableList<Long>, operands: List<Long>, pointer: Int): Output {
                abc[1] =
                    (abc[0] / (2.0.pow(Combo(operands[pointer + 1]).cacl(abc[0], abc[1], abc[2]).toInt()))).toLong()
                return Output(pointer = pointer + 2)
            }
        }

        class cdv() : Code(5) {
            override fun calc(abc: MutableList<Long>, operands: List<Long>, pointer: Int): Output {
                abc[2] =
                    (abc[0] / (2.0.pow(Combo(operands[pointer + 1]).cacl(abc[0], abc[1], abc[2]).toInt()))).toLong()
                return Output(pointer = pointer + 2)
            }
        }
    }
}
