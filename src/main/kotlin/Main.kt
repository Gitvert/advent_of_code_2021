import java.io.File

fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    // println("Program arguments: ${args.joinToString()}")

    println("solving")
    day1part1()
    day1part2()
    day2part1()
    day2part2()
    day3part1()
    day3part2()
    day4part1()
    day4part2()
    day5part1()
    day5part2()
}

fun readFile(fileName: String): List<String> {
    val lines: MutableList<String> = mutableListOf()

    File("src/main/kotlin/inputs/$fileName").useLines { line -> line.forEach { lines.add(it) } }

    return lines
}