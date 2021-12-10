import java.io.File
import kotlin.system.measureTimeMillis

suspend fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    // println("Program arguments: ${args.joinToString()}")

    println("solving")
    println("---- Took ${measureTimeMillis{ day1() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day2() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day3() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day4() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day5() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day6() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day7() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day8() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day9() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day10() }} ms to execute ----")
}

fun readFile(fileName: String): List<String> {
    val lines: MutableList<String> = mutableListOf()

    File("src/main/kotlin/inputs/$fileName").useLines { line -> line.forEach { lines.add(it) } }

    return lines
}