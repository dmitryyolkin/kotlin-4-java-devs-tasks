package com.yolkin.fromjava.coursera.week3.nicestring

val vowels = setOf('a', 'e', 'i', 'o', 'u')
val banned = setOf("bu", "ba", "be")

fun String.isNice(): Boolean {
    var counter = 0;

    // there is no substring
    counter += if (banned.none() { contains(it) }) 1 else 0

    // contains three vowels
    counter += if (count{ it in vowels} >= 3) 1 else 0

    // check double letters
    // all cases below are valid and possible
    // counter += if ((0 until lastIndex).any { this[it] == this[it + 1] }) 1 else 0
    // counter += if (windowed(2).any { it[0] == it[1]}) 1 else 0
    counter += if (zipWithNext().any { it.first == it.second}) 1 else 0

    return counter >= 2;
}