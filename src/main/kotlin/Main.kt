import java.io.File
import kotlin.system.measureTimeMillis

suspend fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
   //  println("Program arguments: ${args.joinToString()}")

    /*val intArgs: List<Int> = args.map { Integer.valueOf(it) }

    val iterations = intArgs[0]
    val daysToSolve = intArgs.filterIndexed { i, _ ->
        i > 0
    }

    for (i in 0 until iterations) {
        println("Solving")
        daysToSolve.forEach {
            when (it) {
                1 -> day1()
                2 -> day2()
                3 -> day3()
                4 -> day4()
                5 -> day5()
                6 -> day6()
                7 -> day7()
                8 -> day8()
                9 -> day9()
                10 -> day10()
                11 -> day11()
                12 -> day12()
            }
        }
        println("Done")
    }*/

    println("Solving")
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
    println("---- Took ${measureTimeMillis{ day11() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day12() }} ms to execute ----")
    println("Done")
}

fun readFile(fileName: String): List<String> {
    val lines: MutableList<String> = mutableListOf()

    File("src/main/kotlin/inputs/$fileName").useLines { line -> line.forEach { lines.add(it) } }

    return lines
}