fun day3() {
    day3part1()
    day3part2()
}

fun day3part1() {
    val lines: List<String> = readFile("day03.txt")

    val zeroes: MutableList<Int> = createZeroesList(lines)

    var gammaBinary = ""
    var epsilonBinary = ""

    zeroes.forEach{
        when {
            it > 500 -> {
                gammaBinary += "1"
                epsilonBinary += "0"
            }
            else -> {
                gammaBinary += "0"
                epsilonBinary += "1"
            }
        }
    }

    val answer = Integer.valueOf(gammaBinary, 2) * Integer.valueOf(epsilonBinary, 2)

    println("3a: $answer")
}

fun day3part2() {
    val lines: List<String> = readFile("day03.txt")

    var zeroes: MutableList<Int> = createZeroesList(lines)
    var reducedLines: List<String> = lines.toMutableList()
    var index = 0

    do {
        reducedLines = removeUnwantedNumbers(
            when {
                zeroes[index] > (reducedLines.size/2) -> "0"
                else -> "1"
            },
            index,
            reducedLines
        )
        index += 1
        zeroes = createZeroesList(reducedLines)
    } while (reducedLines.size > 1)

    val oxygenGeneratorRating = reducedLines[0]

    reducedLines = lines.toMutableList()
    index = 0
    zeroes = createZeroesList(lines)

    do {
        reducedLines = removeUnwantedNumbers(
            when {
                zeroes[index] > (reducedLines.size/2) -> "1"
                else -> "0"
            },
            index,
            reducedLines
        )
        index += 1
        zeroes = createZeroesList(reducedLines)
    } while (reducedLines.size > 1)

    val co2ScrubberRating = reducedLines[0]

    val answer = Integer.valueOf(oxygenGeneratorRating, 2) * Integer.valueOf(co2ScrubberRating, 2)

    println("3b: $answer")
}

fun createZeroesList(lines: List<String>): MutableList<Int> {
    var index = 0
    val zeroes: MutableList<Int> = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    lines.forEach{ line ->
        line.forEach {
            if (it.toString() == "0") {
                zeroes[index]++
            }
            index++
        }
        index = 0
    }

    return zeroes
}

fun removeUnwantedNumbers(wantedNumber: String, index: Int, lines: List<String>): List<String> {
    return lines.filter { it[index].toString() == wantedNumber }
}