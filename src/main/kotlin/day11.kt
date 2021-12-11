fun day11() {
    val lines: List<String> = readFile("day11.txt")

    day11part1(lines)
    day11part2(lines)
}

fun day11part1(lines: List<String>) {
    val octopuses = initOctopuses(lines)

    val answer = IntRange(0,99).sumOf { step(octopuses) }

    println("11a: $answer")
}

fun day11part2(lines: List<String>) {
    val octopuses = initOctopuses(lines)

    var stepCounter = 0

    while (true) {
        stepCounter++
        if (step(octopuses) == 100) {
            break
        }
    }

    val answer = stepCounter

    println("11b: $answer")
}

fun step(octopuses: List<List<Octopus>>): Int {
    for (i in 0 until 10) {
        for (j in 0 until 10) {
            octopuses[i][j].energyLevel++
        }
    }

    var totalFlashes = 0
    var shouldContinue = true

    while (shouldContinue) {
        shouldContinue = false

        for (i in 0 until 10) {
            for (j in 0 until 10) {
                if (octopuses[i][j].energyLevel > 9 && !octopuses[i][j].flashedThisStep) {
                    flash(octopuses, i, j)
                    totalFlashes++
                    shouldContinue = true
                }
            }
        }
    }

    resetFlashes(octopuses)

    return totalFlashes
}

fun flash(octopuses: List<List<Octopus>>, i: Int, j: Int) {
    octopuses[i][j].flashedThisStep = true

    // LEFT
    if (i > 0) {
        octopuses[i-1][j].energyLevel++
    }
    // UP
    if (j > 0) {
        octopuses[i][j-1].energyLevel++
    }
    //RIGHT
    if (i < 9) {
        octopuses[i+1][j].energyLevel++
    }
    //DOWN
    if (j < 9) {
        octopuses[i][j+1].energyLevel++
    }
    //LEFT UP
    if (i > 0 && j > 0) {
        octopuses[i-1][j-1].energyLevel++
    }
    //RIGHT UP
    if (i < 9 && j > 0) {
        octopuses[i+1][j-1].energyLevel++
    }

    //LEFT DOWN
    if (i > 0 && j < 9) {
        octopuses[i-1][j+1].energyLevel++
    }

    //RIGHT DOWN
    if (i < 9 && j < 9) {
        octopuses[i+1][j+1].energyLevel++
    }
}

fun resetFlashes(octopuses: List<List<Octopus>>) {
    for (i in 0 until 10) {
        for (j in 0 until 10) {
            if (octopuses[i][j].flashedThisStep) {
                octopuses[i][j].energyLevel = 0
                octopuses[i][j].flashedThisStep = false
            }
        }
    }
}

fun initOctopuses(lines: List<String>): List<List<Octopus>> {
    val octopuses: MutableList<MutableList<Octopus>> = mutableListOf()

    for (i in 0 until 10) {
        val octopus: MutableList<Octopus> = mutableListOf()
        for (j in 0 until 10) {
            octopus.add(Octopus(Integer.valueOf(lines[i][j].toString()), false))
        }
        octopuses.add(octopus)
    }

    return octopuses
}

data class Octopus(var energyLevel: Int, var flashedThisStep: Boolean)