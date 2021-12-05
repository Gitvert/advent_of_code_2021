fun day2part1() {
    val instruments = findInstruments()

    var horizontalPosition = 0
    var depth = 0

    instruments.forEach {
        when (it.first) {
            "forward" -> horizontalPosition += it.second
            "up" -> depth -= it.second
            "down" -> depth += it.second
        }
    }

    val answer = horizontalPosition * depth

    println("2a: $answer")
}

fun day2part2() {
    val instruments = findInstruments()

    var horizontalPosition = 0
    var depth = 0
    var aim = 0

    instruments.forEach {
        when (it.first) {
            "forward" -> {
                horizontalPosition += it.second
                depth += it.second * aim
            }
            "up" -> aim -= it.second
            "down" -> aim += it.second
        }
    }

    val answer = horizontalPosition * depth

    println("2b: $answer")
}

fun findInstruments(): List<Pair<String, Int>> {
    val lines: List<String> = readFile("day02.txt")

    return lines.map {
        val instruction = it.split(" ")
        Pair(instruction[0], Integer.valueOf(instruction[1]))
    }
}