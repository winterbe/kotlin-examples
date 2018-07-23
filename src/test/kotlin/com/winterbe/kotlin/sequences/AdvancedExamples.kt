package com.winterbe.kotlin.sequences

import org.junit.Test

class AdvancedExamples {

    data class Person(val name: String, val age: Int)

    private val persons = listOf(
        Person("Peter", 16),
        Person("Anna", 28),
        Person("Anna", 23),
        Person("Sonya", 39)
    )

    @Test
    fun `reduce sequence of numbers into digit sum`() {
        val result = sequenceOf(1, 2, 3, 4, 5)
            .asSequence()
            .reduce { acc, num ->
                acc + num
            }

        print(result)   // 15
    }

    @Test
    fun flatMap() {
        val result = sequenceOf(listOf(1, 2, 3), listOf(4, 5, 6))
            .flatMap {
                it.asSequence().filter { it % 2 == 1 }
            }
            .toList()

        print(result)   // [1, 3, 5]
    }

    @Test
    fun `flatten elements`() {
        val result = sequenceOf(listOf(1, 2, 3), listOf(4, 5, 6))
            .flatten()
            .toList()

        print(result)   // [1, 2, 3, 4, 5, 6]
    }

    @Test
    fun `associate by a given property`() {
        val result = persons
            .asSequence()
            .associateBy { it.name }

        print(result)   // {Peter=Person(name=Peter, age=16), Anna=Person(name=Anna, age=23), Sonya=Person(name=Sonya, age=39)}
    }

    @Test
    fun `group by a given property`() {
        val result = persons
            .asSequence()
            .groupBy { it.name }

        print(result)   // {Peter=[Person(name=Peter, age=16)], Anna=[Person(name=Anna, age=28), Person(name=Anna, age=23)], Sonya=[Person(name=Sonya, age=39)]}
    }

    @Test
    fun `any matches predicate`() {
        val result = sequenceOf(1, 2, 3, 4, 5)
            .filter { it % 2 == 1 }
            .any { it % 2 == 0 }

        print(result)   // false
    }

    @Test
    fun `map sequences`() {
        val map = mapOf(
            "a" to 10,
            "b" to 20,
            "c" to 30
        )

        val result = map
            .asSequence()
            .filter { it.value < 20 }
            .map { it.key }
            .single()

        print(result)   // a
    }

    @Test
    fun `sort by property`() {
        val result = persons
            .asSequence()
            .sortedBy { it.age }
            .toList()

        print(result)   // [Person(name=Peter, age=16), Person(name=Anna, age=23), Person(name=Anna, age=28), Person(name=Sonya, age=39)]
    }

    @Test
    fun `distinct by property`() {
        val result = persons
            .asSequence()
            .distinctBy { it.name }
            .toList()

        print(result)   // [Person(name=Peter, age=16), Person(name=Anna, age=28), Person(name=Sonya, age=39)]
    }

    @Test
    fun `max by property`() {
        val result = persons
            .asSequence()
            .maxBy { it.age }

        print(result)   // Person(name=Sonya, age=39)
    }

    @Test
    fun `operate on element indices`() {
        val result = sequenceOf("a", "b", "c", "d")
            .withIndex()
            .filter { it.index % 2 == 0 }
            .map { it.value }
            .toList()

        print(result)   // [a, c]
    }

    @Test
    fun `add and remove elements from sequence`() {
        val result = sequenceOf("a", "b", "c")
            .plus("d")
            .minus("c")
            .map { it.toUpperCase() }
            .toList()

        print(result)   // [A, B, D]
    }

}