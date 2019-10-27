package com.yolkin.fromjava.coursera.week5.games.gameOfFifteen

import com.yolkin.fromjava.coursera.week5.board.Cell
import com.yolkin.fromjava.coursera.week5.board.Direction
import com.yolkin.fromjava.coursera.week5.board.Direction.*
import com.yolkin.fromjava.coursera.week5.board.createGameBoard
import com.yolkin.fromjava.coursera.week5.games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
        GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        var randomIndex = 0
        for (i in 1..board.width) {
            for (j in 1..board.width) {
                val cell = board.getCell(i, j)
                val value = if (randomIndex < initializer.initialPermutation.size) {
                    initializer.initialPermutation[randomIndex++]
                } else {
                    null
                }
                // set new value
                board.set(cell, value)
            }
        }
    }

    override fun canMove(): Boolean {
        return true
    }

    override fun hasWon(): Boolean {
        val list = mutableListOf<Int?>()
        for (i in 1..board.width) {
            for (j in 1..board.width) {
                list.add(get(i, j))
            }
        }

        return list[list.size - 1] == null && isEven(list.filterNotNull())
    }

    override fun processMove(direction: Direction) {
        val emptyCell : Cell = (board.find { it == null } as Cell)
        var nextCell : Cell? = null
        when (direction) {
            UP -> if (emptyCell.i < board.width) nextCell = board.getCell(emptyCell.i + 1, emptyCell.j)
            DOWN -> if (emptyCell.i > 1) nextCell = board.getCell(emptyCell.i - 1, emptyCell.j)
            LEFT -> if (emptyCell.j < board.width) nextCell = board.getCell(emptyCell.i, emptyCell.j + 1)
            RIGHT -> if (emptyCell.j > 1) nextCell = board.getCell(emptyCell.i, emptyCell.j - 1)
        }

        if (nextCell != null) {
            board.set(emptyCell, get(nextCell.i, nextCell.j))
            board.set(nextCell, null)
        }
    }

    override fun get(i: Int, j: Int): Int? {
        return board.run { get(getCell(i, j)) }
    }

}