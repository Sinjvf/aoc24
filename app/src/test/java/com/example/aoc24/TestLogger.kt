package com.example.aoc24

import com.example.ILogger

class TestLogger : ILogger {
    override fun logD(str: String) {
        println(str)
    }
}

class NoLogger : ILogger {
    override fun logD(str: String) = Unit
}