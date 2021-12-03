import java.io.File

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")


    println("solving")
    day1part1()
    day1part2()
    day2part1()
    day2part2()
}

fun day1part1() {
    val lines: List<String> = readFile("/Users/jesperevertsson/Code/advent_of_code/src/main/kotlin/inputs/day01.txt")
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
    val lines: List<String> = readFile("/Users/jesperevertsson/Code/advent_of_code/src/main/kotlin/inputs/day01.txt")
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

fun day2part1() {
    val lines: List<String> = readFile("/Users/jesperevertsson/Code/advent_of_code/src/main/kotlin/inputs/day02.txt")
    val instruments: List<Pair<String, Int>> = lines.map {
        val instruction = it.split(" ")
        Pair(instruction[0], Integer.valueOf(instruction[1]))
    }

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

    println("2a $answer")
}

fun day2part2() {
    val lines: List<String> = readFile("/Users/jesperevertsson/Code/advent_of_code/src/main/kotlin/inputs/day02.txt")
    val instruments: List<Pair<String, Int>> = lines.map {
        val instruction = it.split(" ")
        Pair(instruction[0], Integer.valueOf(instruction[1]))
    }

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

    println("2b $answer")
}

fun day3part1() {
    val lines: List<String> = readFile("/Users/jesperevertsson/Code/advent_of_code/src/main/kotlin/inputs/day03.txt")

    
}

fun readFile(fileName: String): List<String> {
    val lines: MutableList<String> = mutableListOf()

    File(fileName).useLines { line -> line.forEach { lines.add(it) } }

    return lines
}