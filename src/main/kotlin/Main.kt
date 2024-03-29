import java.io.File
import kotlin.system.measureTimeMillis

suspend fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
   //  println("Program arguments: ${args.joinToString()}")

    val intArgs: List<Int> = args.map { Integer.valueOf(it) }

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
                13 -> day13()
                14 -> day14()
                15 -> day15()
                16 -> day16()
                17 -> day17()
                18 -> day18()
                19 -> day19()
                20 -> day20()
                21 -> day21()
                22 -> day22()
                25 -> day25()
            }
        }
        println("Done")
    }

    /*println("Solving")
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
    println("---- Took ${measureTimeMillis{ day13() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day14() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day15() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day16() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day17() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day18() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day19() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day20() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day21() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day22() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day24() }} ms to execute ----")
    println("---- Took ${measureTimeMillis{ day25() }} ms to execute ----")
    println("Done")*/
}

fun readFile(fileName: String): List<String> {
    val lines: MutableList<String> = mutableListOf()

    File("src/main/kotlin/inputs/$fileName").useLines { line -> line.forEach { lines.add(it) } }

    return lines
}