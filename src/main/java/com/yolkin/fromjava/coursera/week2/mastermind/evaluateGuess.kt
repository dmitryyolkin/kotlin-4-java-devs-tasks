package com.yolkin.fromjava.coursera.week2.mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

// please see more elegant solution in evaluateGuess2 below
// it was provided by Coursera authors in the course
fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0

    val secretPositions = mutableSetOf<Int>()
    val guessedPositions = mutableSetOf<Int>()

    //check by right position
    for ((secretIndex, secretCh) in secret.withIndex()) {
        val guessIndex = guess.indexOf(secretCh, secretIndex, true)
        if (guessIndex == secretIndex) {
            rightPosition++
            secretPositions.add(secretIndex)
            guessedPositions.add(guessIndex)
        }
    }

    // check by wrong position
    for ((secretIndex, secretCh) in secret.withIndex()) {
        if (secretIndex in secretPositions) {
            // this char of secret string has already guessed with right position before
            continue
        }

        // try to guess wrong position
        var guessIndex = -1
        do {
            guessIndex = guess.indexOf(secretCh, guessIndex + 1, true)
        } while (guessIndex >= 0 && guessIndex in guessedPositions)

        if (guessIndex >= 0) {
            wrongPosition++
            secretPositions.add(secretIndex)
            guessedPositions.add(guessIndex)
        }
    }

    return Evaluation(rightPosition, wrongPosition)
}

// This solution was provided by Coursera
fun evaluateGuess2(secret: String, guess: String): Evaluation {
    val rightPositions = secret.zip(guess).count {
        p -> p.first == p.second
    }

    val commonLetters = "ABCDEF".sumBy { ch ->
        Math.min(
                secret.count { it == ch },
                guess.count { it == ch }
        )
    }
    return Evaluation(rightPositions, commonLetters - rightPositions)
}

