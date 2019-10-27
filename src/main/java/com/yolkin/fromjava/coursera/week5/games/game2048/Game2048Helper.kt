package com.yolkin.fromjava.coursera.week5.games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    var i = 0
    val filtered = filterNotNull()
    val result = mutableListOf<T>()
    while (i < filtered.size) {
        val currV = filtered[i]
        if (i + 1 >= filtered.size || currV != filtered[i + 1]) {
            // last element or elements are different
            result.add(currV)
            i++
        } else {
            // curr and next elements are the same
            result.add(merge(currV))
            i += 2
        }
    }
    return result
}