package com.winterbe.kotlin.sequences

import org.junit.Test
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime

class PerformanceTest {

    @Test
    fun testPerf1() {
        val sequence = generateSequence(1) { it + 1 }.take(50000000)
        val list = sequence.toList()
        measure("list - filter - average") {
            list.filter { it % 3 == 0 }.average()
        }
        // 8644 ms
    }

    @Test
    fun testPerf2() {
        val sequence = generateSequence(1) { it + 1 }.take(50000000)
        val list = sequence.toList()
        measure("list - filter - sum") {
            list.filter { it % 3 == 0 }.sum()
        }
        // 11127 ms
    }

    @Test
    fun testPerf3() {
        val sequence = generateSequence(1) { it + 1 }.take(50000000)
        measure("sequence - filter - average") {
            sequence.filter { it % 3 == 0 }.average()
        }
        // 822 ms
    }

    @Test
    fun testPerf4() {
        val sequence = generateSequence(1) { it + 1 }.take(50000000)
        measure("sequence - filter - sum") {
            sequence.filter { it % 3 == 0 }.sum()
        }
        // 784 ms
    }

    private fun measure(name: String, block: () -> Unit) {
        val nanoTime = measureNanoTime(block)
        println("$name: ${TimeUnit.NANOSECONDS.toMillis(nanoTime)} ms")
    }
}