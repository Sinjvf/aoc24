package com.example.aoc24.util

import com.example.aoc24.util.Direction.DOWN
import com.example.aoc24.util.Direction.LEFT
import com.example.aoc24.util.Direction.RIGHT
import com.example.aoc24.util.Direction.UP
import kotlin.math.abs

/**
 * @author Тамара Синева on 11.12.2023
 */
data class Point2D(var x: Int, var y: Int) : Comparable<Point2D> {
    override fun compareTo(other: Point2D): Int {
        if (x != other.x) return x.compareTo(other.x)
        return y.compareTo(other.y)
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    fun toDirection(direction: Direction, size: Int = 1): Point2D =
        when (direction) {
            UP -> Point2D(x - size, y)
            DOWN -> Point2D(x + size, y)
            LEFT -> Point2D(x, y - size)
            RIGHT -> Point2D(x, y + size)
        }

    fun toDirections(list: List<Pair<Int, Direction>>): Point2D =
        list.fold(this@Point2D) { point, dir -> point.toDirection(dir.second, dir.first) }


    fun <T> inMatrix(matrix: Matrix<T>): Boolean =
        (x in 0 until matrix.ySize) && (y in 0 until matrix.xSize)

    fun <T> inExpandMatrix(matrix: Matrix<T>, expandDir: List<Direction>): Boolean =
        !((UP !in expandDir) && (x < 0)
            || (DOWN !in expandDir) && (x >= matrix.ySize)
            || (LEFT !in expandDir) && (y < 0)
            || (RIGHT !in expandDir) && (y >= matrix.xSize))

    fun inRange(xR: IntRange, yR: IntRange) =
        x in xR && y in yR

    fun maxAbs() = maxOf(abs(x), abs(y))

    fun distanceTo(p:Point2D):Int =
        abs(p.x-x) +abs(p.y-y)

    fun vectorTo(p: Point2D):List< Pair< Int,Direction>> {
        val list = mutableListOf<Pair< Int,Direction>>()
        val xDiff = p.x - x
        val yDiff = p.y - y
        if (xDiff> 0) list.add(abs(xDiff) to DOWN) else if (xDiff<0) list.add(abs(xDiff)  to UP)
        if (yDiff > 0) list.add(abs(yDiff) to RIGHT) else if (yDiff<0) list.add(abs(yDiff) to LEFT)
   //     println(list)
        return list
    }
}

