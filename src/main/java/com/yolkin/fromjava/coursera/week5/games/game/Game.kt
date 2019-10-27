package com.yolkin.fromjava.coursera.week5.games.game

import com.yolkin.fromjava.coursera.week5.board.Direction

interface Game {
    fun initialize()
    fun canMove(): Boolean
    fun hasWon(): Boolean
    fun processMove(direction: Direction)
    operator fun get(i: Int, j: Int): Int?
}
