package com.yolkin.fromjava.coursera.week3.nicestring

val vowels = setOf('a', 'e', 'i', 'o', 'u')
val banned = setOf("bu", "ba", "be")

fun String.isNice(): Boolean {
    var counter = 0;

    // there is no substring
    counter += if (banned.all { !contains(it) }) 1 else 0
    // contains three vowels
    counter += if (groupBy { it }
                    .filter { vowels.contains(it.key) }
                    .map { it.value.size }
                    .sumBy { it } >= 3) 1 else 0

    // check double letters
    fun hasDuplicates(): Boolean {
        for (i in 0 until length - 1) {
            val currChar = get(i)
            val nextChar = get(i + 1)
            if (currChar == nextChar) return true
        }
        return false
    }
    counter += if (hasDuplicates()) 1 else 0
    return counter >= 2;
}