fun day16() {
    val lines: List<String> = readFile("day16.txt")

    day16part1(lines)
    day16part2(lines)
}

fun day16part1(lines: List<String>) {
    val message = lines[0].map { bm[it] }.joinToString("")

    val answer = parseTotalVersion(message)

    println("16a: $answer")
}

fun day16part2(lines: List<String>) {
    val answer = "---"

    println("16b: $answer")
}

fun parseTotalVersion(message: String): Int {
    var versionSize = 0
    var parserPosition = 0

    while (true) {
        if (message.substring(parserPosition).contains("^0+$".toRegex())) {
            break
        }

        val version = getIntValue(message, parserPosition, parserPosition + 3)
        parserPosition += 3
        val typeId = getIntValue(message, parserPosition, parserPosition + 3)
        parserPosition += 3

        versionSize += version

        if (typeId == 4) {
            var lastNumberIndicatorBit = 1
            var literalValue = ""

            while (lastNumberIndicatorBit != 0) {
                lastNumberIndicatorBit = getIntValue(message, parserPosition, parserPosition + 1)
                parserPosition += 1

                literalValue = literalValue.plus(message.substring(parserPosition, parserPosition + 4))
                parserPosition += 4
            }

        } else {
            val subPacketBit = getIntValue(message, parserPosition, parserPosition + 1)
            parserPosition += 1

            if (subPacketBit == 0) {
                val subPacketsLength = getIntValue(message, parserPosition, parserPosition + 15)
                parserPosition += 15
                continue
            } else {
                val noOfSubPackets = getIntValue(message, parserPosition, parserPosition + 11)
                parserPosition += 11
                continue
            }
        }
    }

    return versionSize
}

fun parseValue(message: String, parseStartPosition: Int): Int {
    var parserPosition = parseStartPosition
    var values = mutableListOf<Int>()

    while (true) {
        if (message.substring(parserPosition).contains("^0+$".toRegex())) {
            break
        }

        val version = getIntValue(message, parserPosition, parserPosition + 3)
        parserPosition += 3
        val typeId = getIntValue(message, parserPosition, parserPosition + 3)
        parserPosition += 3

        if (typeId == 4) {
            var lastNumberIndicatorBit = 1
            var literalValue = ""

            while (lastNumberIndicatorBit != 0) {
                lastNumberIndicatorBit = getIntValue(message, parserPosition, parserPosition + 1)
                parserPosition += 1

                literalValue = literalValue.plus(message.substring(parserPosition, parserPosition + 4))
                parserPosition += 4
            }

            values.add(Integer.valueOf(literalValue, 2))

        } else {
            val subPacketBit = getIntValue(message, parserPosition, parserPosition + 1)
            parserPosition += 1

            if (subPacketBit == 0) {
                val subPacketsLength = getIntValue(message, parserPosition, parserPosition + 15)
                parserPosition += 15

            } else {
                val noOfSubPackets = getIntValue(message, parserPosition, parserPosition + 11)
                parserPosition += 11

            }

            when (typeId) {
                0 -> 1
                1 -> 2
                2 -> 3
                3 -> 4
                5 -> 6
                6 -> 7
                7 -> 8
            }
        }
    }

    return 0
}

fun getIntValue(message: String, startPosition: Int, endPosition: Int): Int {
    return Integer.valueOf(message.substring(startPosition, endPosition), 2)
}

val bm: Map<Char, String> = mapOf(
    Pair('0', "0000"),
    Pair('1', "0001"),
    Pair('2', "0010"),
    Pair('3', "0011"),
    Pair('4', "0100"),
    Pair('5', "0101"),
    Pair('6', "0110"),
    Pair('7', "0111"),
    Pair('8', "1000"),
    Pair('9', "1001"),
    Pair('A', "1010"),
    Pair('B', "1011"),
    Pair('C', "1100"),
    Pair('D', "1101"),
    Pair('E', "1110"),
    Pair('F', "1111"),
)