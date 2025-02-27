package com.example.aoc24.util

import com.example.ILogger
import kotlin.math.max
import kotlin.math.min

/**
 * @author Тамара Синева on 11.12.2023
 */
@Deprecated("Слишком много времени уходит разобраться в строках и стобцах. Работа с матрицей не интуитивна" +
    "вместо нее надо исползовать Matrix")
class OldMatrix<T>() : Iterable<PositionData<T>> {
    private val data = mutableListOf<MutableList<T>>()

    constructor(x: Int, y: Int, space: T) : this() {
        val raw = buildList {
            repeat(y) {
               /* val col = listOf(space)
                for (i in 0 until data.size) {
                    data[i].add(y, col[i])
                }*/
                add(space)
            }
        }
        repeat(x ) {
            data.add(0, mutableListOf())
            data[0].addAll(raw)
        }
    }

    fun addToEnd(x: Int, node: T) {
        if (x > data.size - 1) {
            for (i in 0..x - data.size) {
                data.add(mutableListOf())
            }
        }
        data[x].add(node)
    }

    fun addToDirection(from: Point2D, dir: Direction, size: Int, element: T, spaceElement: T): Point2D {
        var to = from
        repeat(size) { to = to.toOldDirection(dir) }
        if (to.x != from.x) {

            val raw = buildList {
                repeat(xSize) { add(spaceElement) }
            }
            if (to.x >= ySize) {
                repeat(to.x - ySize + 1) {
                    addRaw(ySize, raw)
                }
            }
            if (0 - to.x > 0) {
                repeat(0 - to.x) {
                    addRaw(0, raw)
                }
                from.x = from.x - to.x
                to.x = 0
            }
        }
        if (to.y != from.y) {
            val col = buildList {
                repeat(ySize) { add(spaceElement) }
            }
            if (to.y >= xSize) {
                repeat(to.y - xSize + 1) {
                    addCol(xSize, col)
                }
            }
            if (0 - to.y > 0) {
                repeat(0 - to.y) {
                    addCol(0, col)
                }
                from.y = from.y - to.y
                to.y = 0
            }
        }
        //  print(null)
        for (ix in min(from.x, to.x)..max(from.x, to.x)) {
            data[ix][from.y] = element
        }

        for (iy in min(from.y, to.y)..max(from.y, to.y)) {
            data[from.x][iy] = element
        }
        return to
    }

    fun getLastPoint() = Point2D(data.size - 1, data[data.size - 1].size - 1)
    fun getNextPoint(x: Int) =
        if (data.isEmpty()) Point2D(0, 0)
        else if (x == data.size) Point2D(data.size, 0)
        else Point2D(x, data[x].size)

    fun getNextMirr(x: Int) =
        if (data.isEmpty()) Point2D(0, 0)
        else if (x == data.size) Point2D(data.size, 0)
        else Point2D(x, data[x].size)

    fun addAll(x: Int, nodes: Collection<T>) {
        if (x > data.size - 1) {
            for (i in 0..x - data.size) {
                data.add(mutableListOf())
            }
        }
        data[x].addAll(nodes)
    }

    fun addRaw(x: Int, nodes: Collection<T>) {
        /* if (x > data.size - 1) {
             for (i in 0..x - data.size) {
                 data.add(mutableListOf())
             }
         }*/
        data.add(x, mutableListOf())
        data[x].addAll(nodes)
    }

    fun addColumn(x: Int, nodes: Collection<T>, size: Int = nodes.size) {
        /* if (x > data.size - 1) {
             for (i in 0..x - data.size) {
                 data.add(mutableListOf())
             }
         }*/
        if (x == 0) {
            repeat(size) {
                data.add(mutableListOf())
            }
        }
        nodes.forEach {
            data[x].add(it)
        }
    }

    fun get(point: Point2D): T {
        if (!pointInRange(point)) throw IllegalArgumentException("bad point $point")
        return data[point.x][point.y]
    }

    fun getExpand(point: Point2D, dir: List<Direction>): Pair<T, Point2D> {
        if (!point.inExpandMatrix(this, dir)) throw IllegalArgumentException("bad point $point")
        var newx: Int = point.x
        var newy: Int = point.y
        var countx = point.x / data.size
        var county = point.y / data[0].size
        if (Direction.UP in dir && point.x < 0) {
            countx--
            newx = -point.x % data.size
        }

        if (Direction.DOWN in dir && point.x >= data.size) {
            newx = point.x % data.size
        }

        if (Direction.LEFT in dir && point.y < 0) {
            county--
            newy = -point.y % data[0].size
        }

        if (Direction.RIGHT in dir && point.y >= data[0].size) {
            newy = point.y % data[0].size
        }

        return data[newx][newy] to Point2D(countx, county)
    }

    fun get(x: Int, y: Int): T {
        val point = Point2D(x, y)
        if (!pointInRange(point)) throw IllegalArgumentException("bad point $point")
        return data[point.x][point.y]
    }

    fun put(point: Point2D, node: T) {
        if (!pointInRange(point)) throw IllegalArgumentException("bad point $point")
        data[point.x][point.y] = node
    }

    fun put(x: Int, y: Int, node: T) {
        val point = Point2D(x, y)
        if (!pointInRange(point)) throw IllegalArgumentException("bad point $point")
        data[point.x][point.y] = node
    }

    fun pointInRange(point: Point2D): Boolean =
        point.x in 0 until data.size && point.y in 0 until data[point.x].size

    override fun iterator(): Iterator<PositionData<T>> = iterator {
        for (x in 0 until data.size) {
            for (y in 0 until data[x].size) {
                yield(PositionData(Point2D(x, y), data[x][y]))
            }
        }
    }

    val ySize
        get() = data.size
    val xSize
        get() = data[0].size

    fun print(logger: ILogger?, separator: String = "", printString:((T, Point2D)->String)? = null) {
        for (x in 0 until data.size) {
            val builder = StringBuilder()
            for (y in 0 until data[x].size) {
                builder.append((printString?.invoke(data[x][y], Point2D( x, y)))?:data[x][y].toString()).append(separator)
            }
            logger?.logD(builder.toString()) ?: println(builder.toString())
        }
    }

    fun printMirrow(logger: ILogger?, separator: String = "", printString:((T)->String)? = null) {
        for (y in 0 until xSize) {
            val builder = StringBuilder()
            for (x in 0 until data.size) {
                builder.append((printString?.invoke(data[x][y]))?:data[x][y].toString()).append(separator)
            }
            logger?.logD(builder.toString()) ?: println(builder.toString())
        }
    }
    fun getCol(y: Int): List<T> = buildList {
        for (i in 0 until data.size) {
            add(data[i][y])
        }
    }

    fun getRaw(x:Int):List<T> = data[x]

    fun addCol(y: Int, col: List<T>) {
        if (col.size != data.size) throw IllegalArgumentException("bad col size ${col.size}")
        for (i in 0 until data.size) {
            data[i].add(y, col[i])
        }
    }
    fun clear(){
        data.clear()
    }

    fun onEachDiagonal(action: (List<T>) -> Unit) {

        //get diagonals
        var i = 0
        var j = 0
        println("----D1----")
        val sum = xSize + ySize - 1
        var last = false
        while (!last) {
            val diagonal = buildList {
                var k = 0
                var p = i

                while (k <= j) {
                    val point1 = Point2D(k, p)
                    if (pointInRange(point1)) {
                        add(get(point1))
                    }
                    k++
                    p--
                }
            }
            if (i == sum && j == sum) last = true
            if (i < sum) {
                i++
            }
            if (j < sum) {
                j++
            }
            if (diagonal.isNotEmpty()) {
                action(diagonal)
            }
        }

        println("----D2----")
        i = 0
        j = -ySize
        last = false
        while (!last) {
            val diagonal = buildList {
                var k = 0
                var p = j

                while (k <= j) {
                    val point1 = Point2D(k, xSize - 1 - p)
                    if (pointInRange(point1)) {
                        add(get(point1))
                    }
                    k++
                    p--
                }
            }
            if (i == sum && j == sum) last = true
            if (i < sum) {
                i++
            }
            if (j < sum) {
                j++
            }
            if (diagonal.isNotEmpty()) {
                action(diagonal)
            }
        }
    }
}

data class PositionData<T>(val pos:Point2D, val data:T)