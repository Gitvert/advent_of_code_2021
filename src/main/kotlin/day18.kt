import kotlin.math.ceil
import kotlin.math.floor

fun day18() {
    val lines: List<String> = readFile("day18.txt")

    day18part1(lines)
    day18part2(lines)
}

fun day18part1(lines: List<String>) {

    val sum = sumListOfSnailFishNumbers(lines)

    val answer = getMagnitudeOfNumber(sum)

    println("18a: $answer")
}

fun day18part2(lines: List<String>) {
    val answer = "TBD"

    println("18b: $answer")
}

fun sumListOfSnailFishNumbers(lines: List<String>): String {
    var finalSum = ""

    lines.forEachIndexed { index, it ->
        if (index < lines.size - 1) {
            if (finalSum.isEmpty()) {
                finalSum = addSnailFishNumber(lines[index], lines[index + 1])
            } else {
                finalSum = addSnailFishNumber(finalSum, lines[index + 1])
            }
        }
    }

    return finalSum
}

fun addSnailFishNumber(left: String, right: String): String {
    var newNumber = "[$left,$right]"

    while (true) {
        val startOfRoundNumber = newNumber
        newNumber = explodeSnailFishNumber(newNumber)
        if (startOfRoundNumber != newNumber) {
            continue
        }
        newNumber = splitSnailFishNumber(newNumber)
        if (startOfRoundNumber != newNumber) {
            continue
        }

        break
    }

    return newNumber
}

fun splitSnailFishNumber(number: String): String {
    var lastWasNumber = false

    number.forEachIndexed { index, it ->
        lastWasNumber = if (it.isDigit()) {
            if (lastWasNumber) {
                return number.replaceRange(index - 1..index, splitNumber(Integer.valueOf(number.substring(index - 1..index))))
            }
            true
        } else {
            false
        }
    }

    return number
}

fun getMagnitudeOfNumber(number: String): Int {
    var newNumber = number

    while (true) {
        val startOfRoundNumber = newNumber
        newNumber = getInnerMagnitude(newNumber)
        if (newNumber == startOfRoundNumber) {
            break
        }
    }

    return Integer.valueOf(newNumber)
}

fun getInnerMagnitude(number: String): String {
    val regex = "\\d+,\\d+".toRegex()
    val result = regex.find(number)

    if (result != null) {
        val range = result.range
        val values = number.substring(range).split(",")
        val pair = Pair(Integer.valueOf(values[0]), Integer.valueOf(values[1]))
        val sum = pair.first * 3 + pair.second * 2

        return number.replaceRange(range.first - 1..range.last + 1, sum.toString())
    }

    return number
}

fun splitNumber(number: Int): String {
    val first = floor(number / 2.0).toInt()
    val second = ceil(number / 2.0).toInt()

    return "[$first,$second]"
}

fun explodeSnailFishNumber(number: String): String {
    var bracketDepth = 0
    var explodeIndex = -1
    run loop@ {
        number.forEachIndexed { index, it ->
            if (bracketDepth >= 5 && it.isDigit()) {
                explodeIndex = index

                return@loop
            }

            if (it == '[') {
                bracketDepth++
            } else if (it == ']') {
                bracketDepth--
            }
        }
    }

    if (explodeIndex == -1) {
        return number
    }

    val endExplodeIndex = number.indexOf(']', explodeIndex) + 1

    val leftValue = Integer.valueOf(number.substring(explodeIndex, number.indexOf(',', explodeIndex)))
    val rightValue = Integer.valueOf(number.substring(number.indexOf(',', explodeIndex) + 1, endExplodeIndex - 1))

    var leftSide  = number.substring(0..explodeIndex - 2)
    var rightSide = number.substring(endExplodeIndex until number.length)

    val leftExplodeIndex = findLastNumberEndIndex(number.substring(0 until explodeIndex))
    val rightExplodeIndex = findFirstNumberStartIndex(number.substring(endExplodeIndex until number.length))

    if (leftExplodeIndex > 0) {
        val leftExplodeIndexStart = findNumberStartIndex(number.substring(0..leftExplodeIndex), leftExplodeIndex)
        val newNumber = Integer.valueOf(number.substring(leftExplodeIndexStart..leftExplodeIndex)) + leftValue
        leftSide = leftSide.replaceRange(leftExplodeIndexStart..leftExplodeIndex, newNumber.toString())
    }

    if (rightExplodeIndex > 0) {
        val rightExplodeIndexEnd = findNumberEndIndex(rightSide.substring(rightExplodeIndex until rightSide.length), rightExplodeIndex)
        val newNumber = Integer.valueOf(rightSide.substring(rightExplodeIndex..rightExplodeIndexEnd)) + rightValue
        rightSide = rightSide.replaceRange(rightExplodeIndex..rightExplodeIndexEnd, newNumber.toString())
    }

    return leftSide + "0" + rightSide
}

fun findLastNumberEndIndex(number: String): Int {
    var lastNumberIndex = -1

    number.forEachIndexed { index, it ->
        if (it.isDigit()) {
            lastNumberIndex = index
        }
    }

    return lastNumberIndex
}

fun findFirstNumberStartIndex(number: String): Int {
    number.forEachIndexed { index, it ->
        if (it.isDigit()) {
            return index
        }
    }

    return -1
}

fun findNumberEndIndex(number: String, startIndex: Int): Int {
    number.forEachIndexed { index, it ->
        if (!it.isDigit()) {
            return index - 1 + startIndex
        }
    }

    return startIndex
}

fun findNumberStartIndex(number: String, endIndex: Int): Int {
    number.reversed().forEachIndexed { index, it ->
        if (!it.isDigit()) {
            return endIndex - index + 1
        }
    }

    return endIndex
}