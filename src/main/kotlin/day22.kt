fun day22() {
    val lines: List<String> = readFile("day22.txt")

    day22part1(lines)
    day22part2(lines)
}

fun day22part1(lines: List<String>) {
    val rebootSteps = mutableListOf<RebootStep>()

    lines.forEach {
        rebootSteps.add(RebootStep(
            it.split(" ")[0] == "on",
            findRange(it, 'x'),
            findRange(it, 'y'),
            findRange(it, 'z')
        ))
    }

    val reactor = Array (150) {Array(150) { IntArray(150) { 0 } } }

    rebootSteps.filter { 
        it.xRange.first >= 0 && it.xRange.first <= 100 && it.xRange.second >= 0 && it.xRange.second <= 100 &&
        it.yRange.first >= 0 && it.yRange.first <= 100 && it.yRange.second >= 0 && it.yRange.second <= 100 &&
        it.zRange.first >= 0 && it.zRange.first <= 100 && it.zRange.second >= 0 && it.zRange.second <= 100
    }.forEach {
        val xFrom = if (it.xRange.first < it.xRange.second) {it.xRange.first} else {it.xRange.second}
        val xTo = if (it.xRange.first > it.xRange.second) {it.xRange.first} else {it.xRange.second}
        val yFrom = if (it.yRange.first < it.yRange.second) {it.yRange.first} else {it.yRange.second}
        val yTo = if (it.yRange.first > it.yRange.second) {it.yRange.first} else {it.yRange.second}
        val zFrom = if (it.zRange.first < it.zRange.second) {it.zRange.first} else {it.zRange.second}
        val zTo = if (it.zRange.first > it.zRange.second) {it.zRange.first} else {it.zRange.second}

        val newNumber = if (it.on) {1} else {0}

        for (x in xFrom..xTo) {
            for (y in yFrom..yTo) {
                for (z in zFrom..zTo) {
                    reactor[x][y][z] = newNumber
                }
            }
        }
    }

    val answer = reactor.flatMap { it.asIterable() }.flatMap { it.asIterable() }.sumOf { it }

    println("22a: $answer")
}

fun day22part2(lines: List<String>) {
    val answer = "---"

    println("22b: $answer")
}

fun findRange(line: String, direction: Char): Pair<Int, Int> {
    val startIndex = line.indexOf(direction)
    val endIndex = line.indexOf(',', startIndex + 1)

    val range = if (endIndex > 0) {line.substring(startIndex + 2, endIndex)} else {line.substring(startIndex + 2)}

    return Pair(Integer.valueOf(range.split("..")[0]) + 50, Integer.valueOf(range.split("..")[1]) + 50)
}

data class RebootStep(val on: Boolean, val xRange: Pair<Int, Int>, val yRange: Pair<Int, Int>, var zRange: Pair<Int, Int>)