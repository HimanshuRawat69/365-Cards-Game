package com.example.a365cards

class NumberDistributor {

    fun distributeNumbers(): List<List<Int>> {
        val numbers = (1..52).toList().shuffled()
        val group1 = numbers.subList(0, 13)
        val group2 = numbers.subList(13, 26)
        val group3 = numbers.subList(26, 39)
        val group4 = numbers.subList(39, 52)

        return listOf(group1, group2, group3, group4)
    }
}