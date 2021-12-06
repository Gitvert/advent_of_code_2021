import java.math.BigInteger

fun day6() {
    val lines: List<String> = readFile("day06.txt")

    day6part1(lines)
    day6part2(lines)
}

fun day6part1(lines: List<String>) {
    val answer = solve(lines, 79)

    println("6a: $answer")
}

fun day6part2(lines: List<String>) {
    val answer = solve(lines, 255)

    println("6b: $answer")
}

fun solve(lines: List<String>, iterations: Int): BigInteger {
    var fishMap = initFishMap(lines)

    for (i in 0..iterations) {
        fishMap = moveOneDay(fishMap)
    }

    return fishMap[0]!! + fishMap[1]!! + fishMap[2]!! + fishMap[3]!! + fishMap[4]!! + fishMap[5]!! + fishMap[6]!! + fishMap[7]!! + fishMap[8]!!
}

fun moveOneDay(fishMap: MutableMap<Int, BigInteger>): MutableMap<Int, BigInteger> {
    val newFishMap: MutableMap<Int, BigInteger> = mutableMapOf()

    newFishMap[0] = fishMap[1]!!
    newFishMap[1] = fishMap[2]!!
    newFishMap[2] = fishMap[3]!!
    newFishMap[3] = fishMap[4]!!
    newFishMap[4] = fishMap[5]!!
    newFishMap[5] = fishMap[6]!!
    newFishMap[6] = (fishMap[7]!! + fishMap[0]!!)
    newFishMap[7] = fishMap[8]!!
    newFishMap[8] = fishMap[0]!!

    return newFishMap
}

fun initFishMap(lines: List<String>): MutableMap<Int, BigInteger> {
    val fishMap: MutableMap<Int, BigInteger> = mutableMapOf()

    for (i in 0..8) {
        fishMap[i] = BigInteger.ZERO
    }

    lines[0].split(",").forEach {
        val internalTimer = Integer.valueOf(it)
        fishMap[internalTimer] = fishMap[internalTimer]!! + BigInteger.ONE
    }

    return fishMap
}
