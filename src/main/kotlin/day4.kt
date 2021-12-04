fun day4part1() {
    val lines: List<String> = readFile("day04.txt")

    val bingoNumbers: List<Int> = lines[0].split(",").map { Integer.valueOf(it) }

    val bingoCards = createBingoCards(lines.subList(2, lines.size-1))

    val answer = playToWin(bingoNumbers, bingoCards)

    println("4a: $answer")
}

fun day4part2() {
    val lines: List<String> = readFile("day04.txt")

    val bingoNumbers: List<Int> = lines[0].split(",").map { Integer.valueOf(it) }

    val bingoCards = createBingoCards(lines.subList(2, lines.size-1))

    val answer = playToLose(bingoNumbers, bingoCards)

    println("4b: $answer")
}

fun createBingoCards(lines: List<String>): List<BingoCard> {
    val bingoCards: MutableList<BingoCard> = mutableListOf()

    var activeBingoCard = BingoCard(mutableListOf(), false)

    lines.forEach { line ->
        if (line.isEmpty()) {
            bingoCards.add(activeBingoCard)
            activeBingoCard = BingoCard(mutableListOf(), false)
        } else {
            activeBingoCard.matrix.add(line.replace("  ", " ").trimStart().split(" ").map{ Integer.valueOf(it) } as MutableList<Int>)
        }
    }

    return bingoCards
}

fun playToWin(bingoNumbers: List<Int>, bingoCards: List<BingoCard>): Int {
    bingoNumbers.forEach { number ->
        bingoCards.forEach { bingoCard ->
            markNumber(bingoCard, number)
            if (checkWin(bingoCard)) {
                return calculateFinalScore(bingoCard, number)
            }
        }
    }

    return 0
}

fun playToLose(bingoNumbers: List<Int>, bingoCards: List<BingoCard>): Int {
    val noOfBingoCards = bingoCards.size
    var foundWinningBingoCards = 0

    bingoNumbers.forEach { number ->
        bingoCards.forEach { bingoCard ->
            markNumber(bingoCard, number)
            if (checkWin(bingoCard)) {
                foundWinningBingoCards++
                if (foundWinningBingoCards == noOfBingoCards) {
                    return calculateFinalScore(bingoCard, number)
                }
            }
        }
    }

    return 0
}

fun markNumber(bingoCard: BingoCard, number: Int) {
    bingoCard.matrix.forEachIndexed  { i, row ->
        row.forEachIndexed  { j, cell ->
            if (cell == number) {
                bingoCard.matrix[i][j] = -1
            }
        }
    }
}

fun checkWin(bingoCard: BingoCard): Boolean {
    if (bingoCard.hasWon) {
        return false
    }
    val rowSeenNumbers: MutableList<Int> = mutableListOf()
    val columnSeenNumbers: MutableList<Int> = mutableListOf()

    for (i in 0 until bingoCard.matrix.size) {
        for (j in 0 until bingoCard.matrix.size) {
            rowSeenNumbers.add(bingoCard.matrix[i][j])
            columnSeenNumbers.add(bingoCard.matrix[j][i])
        }

        if (rowSeenNumbers.all { n -> n == -1 } || columnSeenNumbers.all { n -> n == -1 }) {
            bingoCard.hasWon = true
            return true
        }

        rowSeenNumbers.clear()
        columnSeenNumbers.clear()
    }

    return false
}

fun calculateFinalScore(bingoCard: BingoCard, number: Int): Int {
    var finalScore = 0
    bingoCard.matrix.forEach { row ->
        row.forEach { cell ->
            if (cell != -1) {
                finalScore += cell
            }
        }
    }

    return finalScore * number
}

data class BingoCard(val matrix: MutableList<MutableList<Int>>, var hasWon: Boolean)