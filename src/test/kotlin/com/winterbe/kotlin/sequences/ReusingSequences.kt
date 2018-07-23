package com.winterbe.kotlin.sequences

import org.junit.Test

class ReusingSequences {

    @Test
    fun `reusing sequences`() {
        val sequence = sequenceOf(1, 2, 3, 4, 5)
            .filter { it % 2 == 1 }
        println(sequence.toList())      // [1, 3, 5]
        println(sequence.first())       // 1
    }
}