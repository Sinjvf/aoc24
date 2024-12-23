package com.example.solutions

import com.example.ILogger

class DaySolution22(private val logger: ILogger) : DaySolution {

    override val part1 = object : DaySolutionPart {
        private var intRes: Long = 0
        private val secret = mutableListOf<Long>()

        override fun handleLine(inputStr: String, pos: Int) {
            secret.add(inputStr.toLong())
        }

        override fun finish() {
            secret.forEach {
                println("secret $it")
                //      println()

                val n = nextCount(it, 2000)
//                println("next ${1} = $n")

                intRes += n
                println()
            }
        }

        private fun nextCount(current: Long, count: Int): Long =
            if (count == 1) {
                next(_current = current)
            } else {
                val next = nextCount(current, count - 1)
                //         println("next ${count-1} = $next")
                next(next)
            }

        private fun next(_current: Long): Long {
            var current = _current
            current = (current * 64 xor current) % 16777216
            current = ((current / 32).toInt().toLong() xor current) % 16777216
            return (current * 2048 xor current) % 16777216
        }

        // private inline fun Long.mix():Long =

        override fun obtainResult(): String = intRes.toString()
    }
    override val part2 = object : DaySolutionPart {
        private var intRes: Long = 0
        private val secret = mutableListOf<Long>()
        private val customers =  mutableListOf<Customer>()

        override fun handleLine(inputStr: String, pos: Int) {
            secret.add(inputStr.toLong())
        }

        override fun finish() {
            val count = 2000-1
            println(secret.size)
            var i=0

            val group = mutableMapOf<Q, Cost>()
            secret.forEach {
val first = nextCount_(it, count).second
                addTogroup(first, group)
                i++
            }
            intRes = group.maxBy { it.value }.let { println(it.key);it.value }
        }

        //private fun


        private fun fullGrouped_(customer1:Customer, customerRes: List<Pair<List<Q> , Cost>>? ): List<Pair<List<Q> , Cost>>{
            return  if(customerRes==null){
                grouped(customer1)
            }else{
                val first = grouped(customer1)


                val next =  customerRes //fullGrouped(customers.subList(1, customers.size))

                val map = mutableMapOf< Cost, MutableSet<Q> >()

                for (firstI in 0..first.size-1){
                    val f =  first[firstI]
                    f.first.forEach { q->
                        val s = next.firstOrNull{it.first.firstOrNull{it==q}!=null }?: (q to 0L)

                        val costList =  map.getOrPut( f.second +s.second){ mutableSetOf() }
                        costList.add(q)
                        map.put(f.second +s.second, costList)
                    }
                }
                for (nextI in 0..next.size-1){
                    val n =  next[nextI]
                    n.first.forEach { q->
                        val s = first.firstOrNull{it.first.firstOrNull{it==q}!=null }?: (q to 0L)

                        val costList =  map.getOrPut( n.second +s.second){ mutableSetOf() }
                        costList.add(q)
                        map.put(n.second +s.second, costList)
                    }
                }
                map.map { entry -> entry.value.toList()  to entry.key }

            }
        }

        private fun fullGrouped(customers: List<Customer>): List<Pair<List<Q> , Cost>>{
            return if (customers.size==0){
                emptyList<Pair<List<Q> , Cost>>()
            }else if(customers.size==1){
                grouped(customers.first())
            }else{
                val first = grouped(customers.first())

                val next = fullGrouped(customers.subList(1, customers.size))

                val map = mutableMapOf< Cost, MutableSet<Q> >()
                for (firstI in 0..first.size-1){
                    val f =  first[firstI]
                    f.first.forEach { q->
                        val s = next.firstOrNull{it.first.firstOrNull{it==q}!=null }?: (q to 0L)

                        val costList =  map.getOrPut( f.second +s.second){ mutableSetOf() }
                        costList.add(q)
                        map.put(f.second +s.second, costList)
                    }
                }
                for (nextI in 0..next.size-1){
                    val n =  next[nextI]
                    n.first.forEach { q->
                        val s = first.firstOrNull{it.first.firstOrNull{it==q}!=null }?: (q to 0L)

                        val costList =  map.getOrPut( n.second +s.second){ mutableSetOf() }
                        costList.add(q)
                        map.put(n.second +s.second, costList)
                    }
                }
                map.map { entry -> entry.value.toList()  to entry.key }

            }
        }

        private fun grouped_(customer: Customer): List< Pair<Cost,Q>>{
            return customer
                .mapIndexed { id, price -> id to price }
                .filter { it.first > 3 }
                .map {
                    it.second.price to customer.subList(it.first-3, it.first+1).map { it.diff }
                }
                .sortedBy { it.first }
                .reversed()
        }


        private fun addTogroup(customer: Customer, map:MutableMap<Q, Cost>){
            val new =customer
                .mapIndexed { id, price -> id to price }
                .filter { it.first > 3 }
                .map {
                     customer.subList(it.first-3, it.first+1).map { it.diff } to it.second.price
                }.filter { priceForQue(it.first, customer)==it.second }
                .toSet()
            new.forEach {
                val max = map.getOrPut(it.first){0}
                map.put(it.first, max+it.second)
            }
        }

        private fun grouped(customer: Customer): List<Pair<List<Q> , Cost>>{
            return customer
                .mapIndexed { id, price -> id to price }
                .filter { it.first > 3 }
               // .filter { it.second.price>0 }
                .groupBy { it.second.price }

                .map {entry-> /*prices.subList(it.first-3, it.first+1).map { it.diff }*/
                    val ids = entry.value.map { it.first }
                    val qs = ids.map{
                        customer.subList(it-3, it+1).map { it.diff }
                    }.filter { priceForQue(it, customer)==entry.key }
                    qs to entry.key
                }.sortedBy { it.second }.reversed()
        }

        private fun priceForQue(que: Q, customer: Customer): Cost {
            for (i in 0..customer.size - 4) {
                if (customer[i].diff == que[0] && customer[i + 1].diff == que[1] && customer[i + 2].diff == que[2] && customer[i + 3].diff == que[3])
                    return customer[i + 3].price
            }
            return 0
        }

        private fun nextCount_(current: Long, count: Int): Pair<Long, Customer> =
            (if (count == 1) {
                next(_current = current).let {
                    it to listOf(
                        Price(current % 10, current % 10),
                        Price(it % 10, it % 10 - current % 10)
                    )
                }
            } else {
                val list = mutableListOf<Price>()
                list.add(
                    Price(current % 10, current % 10),
                )
                var nextC =current
                for (i in 0..count){
                    val current = nextC
                    nextC = next(_current = current)

                         list.add (

                            Price(
                                nextC % 10,
                                nextC % 10 - current % 10
                            )
                        )

                }
                nextC to list.toList()
            })

        private fun nextCount(current: Long, count: Int): Pair<Long, Customer> =
            (if (count == 1) {
                next(_current = current).let {
                    it to listOf(
                        Price(current % 10, current % 10),
                        Price(it % 10, it % 10 - current % 10)
                    )
                }
            } else {
                val next = nextCount(current, count - 1)
                //
                next(next.first).let {
                    it to buildList {
                        addAll(next.second); add(
                        Price(
                            it % 10,
                            it % 10 - next.first % 10
                        )
                    )
                    }
                }
            })

        private fun next(_current: Long): Long {
            var current = _current
            current = (current * 64 xor current) % 16777216
            current = ((current / 32).toInt().toLong() xor current) % 16777216
            return (current * 2048 xor current) % 16777216
        }

        override fun obtainResult(): String = intRes.toString()
    }

    data class Price(val price: Long, val diff: Long) {
        override fun toString(): String {
            return "($diff,$price)"
        }
    }

}

typealias Cost = Long
typealias Q = List<Long>
typealias Customer = List<DaySolution22.Price>

