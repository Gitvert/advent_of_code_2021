

fun day10() {
    val lines: List<String> = readFile("day10.txt")

    day10part1(lines)
    day10part2(lines)
}

fun day10part1(lines: List<String>) {
    val answer = lines.map { getCorruptedPoints(it) }.sumOf{ it }

    println("10a: $answer")
}

fun day10part2(lines: List<String>) {
    val incompleteLines = lines.filter { getCorruptedPoints(it) == 0 }

    val incompleteLinePoints = incompleteLines.map { getIncompletePoints(it) }.sorted()

    val answer = incompleteLinePoints[(incompleteLinePoints.size - 1) / 2]

    println("10b: $answer")
}

fun getCorruptedPoints(line: String): Int {
    val stack = ArrayDeque<Char>()

    line.forEach {
        if (listOf('(', '[', '{', '<').contains(it)) {
            stack.add(it)
        } else {
            val closer = stack.removeLast()
            if (chunks[closer] != it) {
                when (it) {
                    ')' -> return 3
                    ']' -> return 57
                    '}' -> return 1197
                    '>' -> return 25137
                }
            }
        }
    }

    return 0
}

fun getIncompletePoints(line: String): Long {
    val stack = ArrayDeque<Char>()
    var points = 0L

    line.forEach {
        if (listOf('(', '[', '{', '<').contains(it)) {
            stack.add(it)
        } else {
            stack.removeLast()
        }
    }

    while (stack.isNotEmpty()) {
        val closer = chunks[stack.removeLast()]
        points *= 5
        when (closer) {
            ')' -> points += 1
            ']' -> points += 2
            '}' -> points += 3
            '>' -> points += 4
        }
    }

    return points
}

val chunks: Map<Char, Char> = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
