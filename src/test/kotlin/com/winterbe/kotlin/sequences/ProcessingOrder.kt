package com.winterbe.kotlin.sequences

import org.junit.Test

class ProcessingOrder {

    @Test
    fun `missing terminal operator prints nothing`() {
        sequenceOf("A", "B", "C")
            .filter {
                println("filter: $it")
                true
            }
    }

    @Test
    fun `elements are processed vertically one by one`() {
        sequenceOf("A", "B", "C")
            .filter {
                println("filter: $it")
                true
            }
            .forEach {
                println("forEach: $it")
            }
        // filter:  A
        // forEach: A
        // filter:  B
        // forEach: B
        // filter:  C
        // forEach: C
    }

    @Test
    fun `early exit`() {
        val result = sequenceOf("A", "B", "C")
            .map {
                println("map: $it")
                it.toUpperCase()
            }
            .any {
                println("any: $it")
                it.startsWith("B")
            }

        println(result)

        // map: A
        // any: A
        // map: B
        // any: B
        // true
    }

    @Test
    fun `unoptimized order`() {
        sequenceOf("a", "b", "c", "d")
            .map {
                println("map: $it")
                it.toUpperCase()
            }
            .filter {
                println("filter: $it")
                it.startsWith("a", ignoreCase = true)
            }
            .forEach {
                println("forEach: $it")
            }

        // map:     a
        // filter:  A
        // forEach: A
        // map:     b
        // filter:  B
        // map:     c
        // filter:  C
        // map:     d
        // filter:  D
    }

    @Test
    fun `optimized order`() {
        sequenceOf("a", "b", "c", "d")
            .filter {
                println("filter: $it")
                it.startsWith("a", ignoreCase = true)
            }
            .map {
                println("map: $it")
                it.toUpperCase()
            }
            .forEach {
                println("forEach: $it")
            }

        // filter:  a
        // map:     a
        // forEach: A
        // filter:  b
        // filter:  c
        // filter:  d
    }

}