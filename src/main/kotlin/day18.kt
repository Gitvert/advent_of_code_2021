fun day18() {
    val lines: List<String> = readFile("day18.txt")

    day18part1(lines)
    day18part2(lines)
}

fun day18part1(lines: List<String>) {
    val test = addSnailFishNumber(lines[0], lines[1])

    val test2 = explodeSnailFishNumber("[7,[6,[5,[4,[3,2]]]]]")

    val answer = test2

    println("18a: $answer")
}

fun day18part2(lines: List<String>) {
    val answer = "TBD"

    println("18b: $answer")
}

fun addSnailFishNumber(left: String, right: String): String {
    return "[$left,$right]"
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

    val leftSide: String
    val rightSide: String

    val leftExplodeIndex = findLastNumberIndex(number.substring(0 until explodeIndex))
    leftSide = if (leftExplodeIndex > 0) {
        val newNumber = Integer.valueOf(number[leftExplodeIndex].toString()) + Integer.valueOf(number[explodeIndex].toString())
        if (number[explodeIndex - 3].isDigit()) {
            number.substring(0..explodeIndex - 2).replaceRange(leftExplodeIndex..leftExplodeIndex, newNumber.toString())
        } else {
            (number.substring(0..explodeIndex - 2) + "0,").replaceRange(leftExplodeIndex..leftExplodeIndex, newNumber.toString())
        }
    } else {
        number.substring(0..explodeIndex - 2) + "0,"
    }

    val rightExplodeIndex = findFirstNumberIndex(number.substring(explodeIndex + 3 until number.length), explodeIndex + 3)
    rightSide = if (rightExplodeIndex > 0) {
        val newNumber = Integer.valueOf(number[rightExplodeIndex].toString()) + Integer.valueOf(number[explodeIndex + 2].toString())
        if (number[explodeIndex + 5].isDigit()) {
            number.substring(explodeIndex + 5 until number.length)
                .replaceRange(rightExplodeIndex - explodeIndex - 5 until rightExplodeIndex - explodeIndex - 4, newNumber.toString())
        } else {
            val temp = number.substring(explodeIndex + 4 until number.length)
            "0" + temp.replaceRange(rightExplodeIndex - explodeIndex - 4 until rightExplodeIndex - explodeIndex - 3, newNumber.toString())
        }
    } else {
        "0" + number.substring(explodeIndex + 4 until number.length)
    }

    return leftSide + rightSide
}

fun findLastNumberIndex(number: String): Int {
    var lastNumberIndex = -1

    number.forEachIndexed { index, it ->
        if (it.isDigit()) {
            lastNumberIndex = index
        }
    }

    return lastNumberIndex
}

fun findFirstNumberIndex(number: String, offset: Int): Int {
    number.forEachIndexed { index, it ->
        if (it.isDigit()) {
            return index + offset
        }
    }

    return -1
}