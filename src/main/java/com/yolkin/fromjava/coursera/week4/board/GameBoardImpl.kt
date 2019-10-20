package board

class GameBoardImpl<T>(width: Int) : SquareBoardImpl(width), GameBoard<T> {
    private val values : MutableMap<Cell, T?> = mutableMapOf()

    init {
        // put null values for all cells
        for (i in 1..width) {
            for (j in 1..width) {
                values[getCell(i, j)] = null
            }
        }
    }

    override fun get(cell: Cell): T? {
        return values[cell]
    }

    override fun set(cell: Cell, value: T?) {
        values[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return values.filterValues(predicate).keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return values.filterValues(predicate).keys.firstOrNull()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return values.values.any(predicate)
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return values.values.all(predicate)
    }
}