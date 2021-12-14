fun day14() {
    val lines: List<String> = readFile("day14.txt")

    day14part1(lines)
    day14part2(lines)
}

fun day14part1(lines: List<String>) {
    val answer = findFinalPolymer(lines, 10)

    println("14a: $answer")
}

fun day14part2(lines: List<String>) {
    val answer = findFinalPolymer(lines, 40)

    println("14b: $answer")
}

fun findFinalPolymer(lines: List<String>, rounds: Int): Long {
    var polymer = lines[0]
    val insertionRules: MutableMap<String, String> = mutableMapOf()

    for (i in 2 until lines.size) {
        val line = lines[i]
        insertionRules[line.split(" -> ")[0]] = line.split(" -> ")[1]
    }

    var pairOccurrences: MutableMap<String, Long> = mutableMapOf()
    val charFrequency = LongArray(25) { 0L }

    for (i in 0..polymer.length - 2) {
        val pair = polymer[i].toString() + polymer[i + 1].toString()
        val oldValue = pairOccurrences.getOrDefault(pair, 0)
        pairOccurrences[pair] = oldValue + 1
    }

    for (i in 0 until rounds) {
        val updatedPairOccurrences: MutableMap<String, Long> = mutableMapOf()
        pairOccurrences.keys.forEach {
            val firstPair = it[0].toString().plus(insertionRules[it]!!)
            val secondPair = insertionRules[it]!!.plus(it[1])

            val firstPairOldValue = updatedPairOccurrences.getOrDefault(firstPair, 0)
            val secondPairOldValue = updatedPairOccurrences.getOrDefault(secondPair, 0)

            updatedPairOccurrences[firstPair] = firstPairOldValue + pairOccurrences[it]!!
            updatedPairOccurrences[secondPair] = secondPairOldValue + pairOccurrences[it]!!
        }
        pairOccurrences = updatedPairOccurrences
    }

    pairOccurrences.keys.forEach {
        val first = it[0]
        val second = it[1]
        val occurrences = pairOccurrences[it]!!

        charFrequency[first.code - 65] += occurrences
        charFrequency[second.code - 65] += occurrences
    }

    for (i in charFrequency.indices) {
        if (charFrequency[i] % 2 != 0L) {
            charFrequency[i]++
        }
        charFrequency[i] /= 2L
    }

    val highestFrequency = charFrequency.maxOf { it }
    val lowestFrequency = charFrequency.filter { it > 0 }.minOf { it }

    return highestFrequency - lowestFrequency
}