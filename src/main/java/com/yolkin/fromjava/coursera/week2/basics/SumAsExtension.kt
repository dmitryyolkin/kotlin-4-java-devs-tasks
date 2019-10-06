package com.yolkin.fromjava.coursera.week2.basics

fun sumAsFunction(list: List<Int>): Int {
    var result = 0
    for (i in list) {
        result += i
    }
    return result
}

// sum as extension function
// of standard sumAsFunction method
fun List<Int>.sum(): Int {
    var result = 0
    for (i in this) {
        result += i
    }
    return result
}

fun main(args: Array<String>) {
    val sum = listOf(1, 2, 3).sum()
    println(sum)    // 6
}