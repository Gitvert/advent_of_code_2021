import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.math.abs

suspend fun day7() {
    val lines: List<String> = readFile("day07.txt")

    day7part1(lines)
    day7part2(lines)
}

fun day7part1(lines: List<String>) {
    val positions = lines[0].split(",").map { Integer.valueOf(it) }
    val min = positions.minOrNull() ?: 0
    val max = positions.maxOrNull() ?: 0

    var lowestFuelCost = Int.MAX_VALUE

    for (i in min..max) {
        var cost = 0
        positions.forEach {
            cost += abs(it - i)
        }

        if (cost < lowestFuelCost) {
            lowestFuelCost = cost
        }
    }

    val answer = lowestFuelCost

    println("7a: $answer")
}

suspend fun day7part2(lines: List<String>) {
    val positions = lines[0].split(",").map { Integer.valueOf(it) }
    val min = positions.minOrNull() ?: 0
    val max = positions.maxOrNull() ?: 0

    var lowestFuelCost = Int.MAX_VALUE

    IntRange(min, max).mapIndexed { index, _ ->
        GlobalScope.async {
            var cost = 0
            positions.forEach {
                cost += calculateFuelCost(it, index)
            }

            if (cost < lowestFuelCost) {
                lowestFuelCost = cost
            }
        }
    }.forEach { it.await() }

    val answer = lowestFuelCost

    println("7b: $answer")
}

fun calculateFuelCost(startPosition: Int, goalPosition: Int): Int {
    var currentCost = 1
    var totalCost = 0
    var distanceMoved = 0
    val distance = abs(startPosition - goalPosition)

    while (distanceMoved < distance) {
        distanceMoved++
        totalCost += currentCost++
    }

    return totalCost
}