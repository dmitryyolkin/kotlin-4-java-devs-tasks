Game of Fifteen

The board for the game of Fifteen is filled randomly with numbers from 1 to 15 and one empty space. You can move the neighboring value to the empty space. The goal is to get the sorted sequence from 1 to 15.

You can check the Game of Fifteen online here. Note that in the implementation for this assignment, the values are moved by arrows rather than mouse clicks.

Game of Fifteen is solvable only if the initial permutation of numbers is even. First, implement the function isEven declared in GameOfFifteenHelper.kt checking whether a permutation is even or odd. Source: GameOfFifteenHelper.kt; tests: TestGameOfFifteenHelper.kt.
You can use the following algorithm to check the given permutation. Let P is a permutation function on a range of numbers 1..n. For a pair (i, j) of elements such that i < j , if P(i) > P(j), then the permutation is said to invert the order of (i, j). The number of such inverted pairs is the parity of the permutation. If permutation inverts even number of such pairs, it is an even permutation; else it is an odd permutation.

Use the isEven function to produce only solvable permutations as initial permutations. Source: GameOfFifteenInitializer.kt; tests: TestGameOfFifteenInitializer.kt.

Implement the GameOfFifteen class from scratch describing the game process. It should implement the Game interface and make use of initializer argument. Note that this argument is used in tests to provide a different initial permutation. Source: GameOfFifteen.kt; tests: TestGameOfFifteen.kt.