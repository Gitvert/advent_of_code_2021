fun day23() {
    val lines: List<String> = readFile("day23.txt")

    day23part1(lines)
    day23part2(lines)
}

fun day23part1(burrow: List<String>) {
    val amphipods = initAmphipods(burrow)

    val answer = "TBD"

    println("23a: $answer")
}

fun day23part2(lines: List<String>) {
    val answer = "TBD"

    println("23b: $answer")
}

fun initAmphipods(burrow: List<String>): List<Amphipod> {
    return listOf(
        Amphipod(Position2D(2, 3), burrow[2][3], getTargetPositions(burrow[2][3]), getMoveCost(burrow[2][3]), 2),
        Amphipod(Position2D(3, 3), burrow[3][3], getTargetPositions(burrow[3][3]), getMoveCost(burrow[3][3]), 2),

        Amphipod(Position2D(2, 5), burrow[2][5], getTargetPositions(burrow[2][5]), getMoveCost(burrow[2][5]), 2),
        Amphipod(Position2D(3, 5), burrow[3][5], getTargetPositions(burrow[3][5]), getMoveCost(burrow[3][5]), 2),

        Amphipod(Position2D(2, 7), burrow[2][7], getTargetPositions(burrow[2][7]), getMoveCost(burrow[2][7]), 2),
        Amphipod(Position2D(3, 7), burrow[3][7], getTargetPositions(burrow[3][7]), getMoveCost(burrow[3][7]), 2),

        Amphipod(Position2D(2, 9), burrow[2][9], getTargetPositions(burrow[2][9]), getMoveCost(burrow[2][9]), 2),
        Amphipod(Position2D(3, 9), burrow[3][9], getTargetPositions(burrow[3][9]), getMoveCost(burrow[3][9]), 2),
    )
}

fun getTargetPositions(name: Char): Pair<Position2D, Position2D> {
    return when (name) {
        'A' -> Pair(Position2D(2, 3), Position2D(3, 3))
        'B' -> Pair(Position2D(2, 5), Position2D(3, 5))
        'C' -> Pair(Position2D(2, 7), Position2D(3, 7))
        'D' -> Pair(Position2D(2, 9), Position2D(3, 9))
        else -> Pair(Position2D(0, 0), Position2D(0, 0))
    }
}

fun getMoveCost(name: Char): Int {
    return when (name) {
        'A' -> 1
        'B' -> 10
        'C' -> 100
        'D' -> 1000
        else -> 0
    }
}

data class Position2D(val x: Int, val y: Int)

data class Amphipod(val position: Position2D, val Name: Char, val targetPositions: Pair<Position2D, Position2D>, val moveCost: Int, var movesLeft: Int)