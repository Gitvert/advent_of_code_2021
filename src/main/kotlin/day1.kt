fun day1() {
    val lines: List<String> = readFile("day01.txt")

    day1part1(lines)
    day1part2(lines)
}

fun day1part1(lines: List<String>) {
    val answer = findAnswer(formatInput(lines))

    println("1a: $answer")
}

fun day1part2(lines: List<String>) {
    val numbers = formatInput(lines)
    val windows: MutableList<Int> = mutableListOf()

    for (i in 0..numbers.size - 3) {
        windows.add(numbers[i] + numbers[i + 1] + numbers[i + 2])
    }

    val answer = findAnswer(windows)

    println("1b: $answer")
}

fun formatInput(lines: List<String>): List<Int> {
    return lines.map { Integer.valueOf(it) }
}

fun findAnswer(numbers: List<Int>): Int {
    var answer = 0
    var prevNumber: Int? = null
    for (number in numbers) {
        if (prevNumber == null) {
            prevNumber = number
            continue
        }

        if (prevNumber < number) {
            answer++
        }

        prevNumber = number
    }

    return answer
}