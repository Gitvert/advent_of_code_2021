fun day8() {
    val lines: List<String> = readFile("day08.txt")

    day8part1(lines)
    day8part2(lines)
}

fun day8part1(lines: List<String>) {
    val answer = lines
        .map { it.split(" | ")[1].split(" ") }
        .flatten()
        .filter { intArrayOf(2, 3, 4, 7).contains(it.length)  }
        .size

    println("8a: $answer")
}

fun day8part2(lines: List<String>) {
    val entries = lines.map { Entry(it.split(" | ")[0].split(" "), it.split(" | ")[1].split(" ")) }
    val answer = entries.sumOf { decode(it) }

    println("8b: $answer")
}

fun decode(entry: Entry): Int {
    val signalsToProcess = entry.signals.filter { intArrayOf(5, 6).contains(it.length) }.map { it.toCharArray().sorted().joinToString("") }

    val digits: MutableList<String> = mutableListOf("TBD", "TBD", "TBD", "TBD", "TBD", "TBD", "TBD", "TBD", "TBD", "TBD")

    entry.signals.forEach {
        val sortedString = it.toCharArray().sorted().joinToString("")
        when (it.length) {
            2 -> digits[1] = sortedString
            3 -> digits[7] = sortedString
            4 -> digits[4] = sortedString
            7 -> digits[8] = sortedString
        }
    }

    signalsToProcess.forEach {
        val number = findNumber(it, digits)
        if (number > -1) {
            digits[number] = it
        }
    }

    signalsToProcess.forEach {
        val number = findNumber(it, digits)
        if (number > -1) {
            digits[number] = it
        }
    }

    val outputDigits = entry.outputs.map {
        val sortedOutput = it.toCharArray().sorted().joinToString("")
        val number = digits.indexOf(sortedOutput)
        if (number == -1) {
            2
        } else {
            number
        }
    }

    return Integer.valueOf(outputDigits.joinToString(""))
}

fun findNumber(pattern: String, digits: MutableList<String>): Int {
    return if (pattern.length == 5) {
        if (pattern.split("").containsAll(digits[1].split(""))) {
            3
        } else if (digits[9].split("").containsAll(pattern.split(""))) {
            return 5
        } else {
            -1
        }
    } else {
        if (pattern.split("").containsAll(digits[4].split(""))) {
            9
        } else  if (pattern.split("").containsAll(digits[7].split(""))) {
            0
        } else {
            6
        }
    }
}

data class Entry(val signals: List<String>, val outputs: List<String>)