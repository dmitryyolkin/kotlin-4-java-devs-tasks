package com.yolkin.fromjava.coursera.week5.games.game2048

import com.yolkin.fromjava.coursera.week5.board.Cell
import com.yolkin.fromjava.coursera.week5.board.Direction
import com.yolkin.fromjava.coursera.week5.board.Direction.*
import com.yolkin.fromjava.coursera.week5.board.GameBoard
import com.yolkin.fromjava.coursera.week5.board.createGameBoard
import com.yolkin.fromjava.coursera.week5.games.game.Game

/*
 * Your task is to implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game).
 * Implement the utility methods below.
 *
 * After implementing it you can try to play the game running 'PlayGame2048'.
 */
fun newGame2048(initializer: Game2048Initializer<Int> = RandomGame2048Initializer): Game =
        Game2048(initializer)

class Game2048(private val initializer: Game2048Initializer<Int>) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        repeat(2) {
            board.addNewValue(initializer)
        }
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon() = board.any { it == 2048 }

    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addNewValue(initializer)
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}

/*
 * Add a new value produced by 'initializer' to a specified cell in a board.
 */
fun GameBoard<Int?>.addNewValue(initializer: Game2048Initializer<Int>) {
    val pair : Pair<Cell, Int>? = initializer.nextValue(this)
    pair?.let { set(it.first, it.second) }
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" in a specified rowOrColumn only.
 * Use the helper function 'moveAndMergeEqual' (in Game2048Helper.kt).
 * The values should be moved to the beginning of the row (or column),
 * in the same manner as in the function 'moveAndMergeEqual'.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {
    // find new values list
    val newValuesList = rowOrColumn
            .map { get(it)}
            .toList()
            .moveAndMergeEqual { 2 * it }

    // update values
    var result = false
    for (i in 0 until rowOrColumn.size) {
        val cell = rowOrColumn[i]
        val oldValue = get(cell)
        val newValue = if (i < newValuesList.size) newValuesList[i] else null
        if (oldValue != newValue) {
            set(cell, newValue)
            result = true
        }
    }
    return result
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" to the specified direction
 * following the rules of the 2048 game .
 * Use the 'moveValuesInRowOrColumn' function above.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValues(direction: Direction): Boolean {
    var result = false
    for (i in 1..width) {
        val iterationResult = when (direction) {
            UP -> moveValuesInRowOrColumn(getColumn(1..width, i))
            DOWN -> moveValuesInRowOrColumn(getColumn(width.downTo(1), i))
            LEFT -> moveValuesInRowOrColumn(getRow(i, 1..width))
            RIGHT -> moveValuesInRowOrColumn(getRow(i, width.downTo(1)))
        }
        result = result or iterationResult
    }
    return result
}