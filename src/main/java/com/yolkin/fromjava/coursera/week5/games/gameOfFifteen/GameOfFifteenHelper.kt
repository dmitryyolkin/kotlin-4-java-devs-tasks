package com.yolkin.fromjava.coursera.week5.games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    var count = 0
    var copy : MutableList<Int> = permutation.toMutableList()
    while (true) {
        val currCount = copy.zipWithNext().count { it.first > it.second }
        if (currCount == 0) {
            // no need new permutations, we can stop
            return count % 2 == 0
        } else {
            count += currCount
            val tmp : MutableList<Int> = copy.toMutableList()
            for (i in 0 until copy.size - 1) {
                if (copy[i] > copy[i + 1]) {
                    tmp.swap(i, i + 1)
                }
            }
            copy = tmp
        }
    }
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}