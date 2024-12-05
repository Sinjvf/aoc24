package com.example.solutions

import com.example.ILogger

class DaySolution5(private val logger: ILogger) : DaySolution {
    private val orders: MutableList<Order> = mutableListOf()

    override val part1 = object : DaySolutionPart {
        private var intRes: Int = 0
        private val orders: MutableList<Order> = mutableListOf()
        private var ordersInput = true
        private val sections: MutableList<List<Page>> = mutableListOf()

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) {
                ordersInput = false
                return
            }
            if (ordersInput) {
                val order = inputStr.split("|").toOrder(orders)
                order?.let { orders.add(order) }
            } else {
                sections.add(inputStr.split(",").map { Page(it.toInt(), orders) })
            }
        }

        override fun finish() {
            sections.forEach {
                if (isCorrect(it, orders)) {
                    intRes += it.getMiddle()
                }
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Int = 0
        private var ordersInput = true
        private val sections: MutableList<List<Page>> = mutableListOf()

        override fun handleLine(inputStr: String, pos: Int) {
            if (inputStr.isEmpty()) {
                ordersInput = false
                return
            }
            if (ordersInput) {
                val order = inputStr.split("|").toOrder(orders)
                order?.let { orders.add(order) }
            } else {
                sections.add(inputStr.split(",").map { Page(it.toInt(), orders) })
            }
        }

        override fun finish() {

            sections.forEach {
                if (!isCorrect(it, orders)) {
                    intRes += it.sorted().getMiddle()
                }
            }
        }

        override fun obtainResult(): String = intRes.toString()
    }
}

data class Page(val page: Int, val orders: List<Order>) : Comparable<Page> {
    override fun compareTo(other: Page): Int {
        val order = orders.firstOrNull {
            it.contains(this, other)
        } ?: return 0

        return if (order.o1 == this) -1
        else 1
    }
}

private fun Order.contains(page1: Page, page2: Page) =
    (o1 == page1 || o1 == page2) && (o2 == page1 || o2 == page2)

private fun inOrder(section: List<Page>, order: Order): Boolean {
    if (!section.contains(order.o1) || !section.contains(order.o2)) return true
    return (section.indexOf(order.o1) < section.indexOf(order.o2))
}

private fun isCorrect(section: List<Page>, orders: List<Order>) =
    orders.all { inOrder(section, it) }

private fun List<Page>.getMiddle(): Int = get((size - 1) / 2).page

private fun List<String>.toOrder(orders: List<Order>): Order? {
    if (isEmpty() || size != 2) return null
    return Order(Page(get(0).toInt(), orders), Page(get(1).toInt(), orders))
}

class Order(val o1: Page, val o2: Page)







