package com.yolkin.fromjava.coursera.week4.board

import com.yolkin.fromjava.coursera.week4.board.Direction.*
import kotlin.math.abs

open class SquareBoardImpl(final override val width: Int) : SquareBoard {
    private val board: List<List<Cell>>

    init {
        val rows = mutableListOf<List<Cell>>()
        // Note that the numbering of cells starts with 1 (not 0)
        for (i in 1..width) {
            val columns = mutableListOf<Cell>()
            for (j in 1..width) {
                columns.add(Cell(i, j))
            }
            rows.add(columns)
        }
        // make board immutable
        this.board = rows.toList()
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        if (i < 1 || i > board.size) {
            return null
        }

        val columns = board[i - 1]
        return if (j < 1 || j > columns.size) null else columns[j - 1]
    }

    override fun getCell(i: Int, j: Int): Cell {
        return getCellOrNull(i, j) ?: throw IllegalArgumentException("Cell is null")
    }

    override fun getAllCells(): Collection<Cell> {
        return board
                .flatten()
                .toList()
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        if (board.size < i) {
            // return empty list
            return listOf()
        }

        val columns = board[i - 1]
        val result = mutableListOf<Cell>()
        for (j in jRange) {
            if (columns.size >= j) {
                result.add(columns[j - 1])
            }
        }
        return result.toList()
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val result = mutableListOf<Cell>()
        for (i in iRange) {
            if (board.size >= i) {
                val columns = board[i - 1]
                if (columns.size >= j) {
                    result.add(columns[j - 1])
                }
            }
        }
        return result.toList()
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            UP -> getCellOrNull(i - 1, j)
            DOWN -> getCellOrNull(i + 1, j)
            LEFT -> getCellOrNull(i, j - 1)
            RIGHT -> getCellOrNull(i, j + 1)
        }
    }
}