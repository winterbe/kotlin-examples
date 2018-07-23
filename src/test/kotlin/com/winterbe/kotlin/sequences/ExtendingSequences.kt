package com.winterbe.kotlin.sequences

import org.junit.Test

class ExtendingSequences {

    private fun <T> Sequence<T>.shuffle(): Sequence<T> {
        return toMutableList()
            .apply { shuffle() }
            .asSequence()
    }

    @Test
    fun `writing your own sequence operations`() {
        val result = sequenceOf(1, 2, 3, 4, 5)
            .shuffle()
            .toList()

        print(result)
    }

}