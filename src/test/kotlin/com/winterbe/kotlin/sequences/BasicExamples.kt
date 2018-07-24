package com.winterbe.kotlin.sequences

import org.junit.Test

class BasicExamples {

    @Test
    fun `basic introduction example`() {
        data class Person(val name: String, val age: Int)

        val persons = listOf(
            Person("Peter", 16),
            Person("Anna", 23),
            Person("Anna", 28),
            Person("Sonya", 39)
        )

        val names = persons
            .asSequence()
            .filter { it.age > 18 }
            .map { it.name }
            .distinct()
            .sorted()
            .toList()

        print(names)     // [Anna, Sonya]
    }

    @Test
    fun `create sequence without input collection`() {
        val result = sequenceOf(1, 2, 3, 4, 5)
            .filter { it % 2 == 1 }
            .toList()

        print(result)     // [1, 3, 5]
    }

    @Test
    fun `generate sequence from function`() {
        val result = generateSequence(0) { it + 1 }
            .take(5)
            .filter { it % 2 == 1 }
            .toList()

        print(result)     // [1, 3, 5]
    }

    @Test
    fun `create sequence from a range of numbers`() {
        val result = (1..6)
            .asSequence()
            .filter { it % 2 == 1 }
            .toList()

        print(result)   // [1, 3, 5]
    }

}