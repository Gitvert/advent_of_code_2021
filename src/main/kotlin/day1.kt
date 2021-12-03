fun day1part1() {
    val lines: List<String> = readFile("day01.txt")
    val numbers = lines.map {
        Integer.valueOf(it)
    }

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

    println("1a: $answer")
}

fun day1part2() {
    val lines: List<String> = readFile("day01.txt")
    val numbers = lines.map {
        Integer.valueOf(it)
    }
    val windows: MutableList<Int> = mutableListOf()

    for (i in 0..numbers.size - 3) {
        windows.add(numbers[i] + numbers[i + 1] + numbers[i + 2])
    }

    var answer = 0
    var prevWindow: Int? = null

    for (window in windows) {
        if (prevWindow == null) {
            prevWindow = window
            continue
        }

        if (prevWindow < window) {
            answer++
        }

        prevWindow = window
    }

    println("1b: $answer")
}