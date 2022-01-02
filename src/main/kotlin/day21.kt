fun day21() {
    val lines: List<String> = readFile("day21.txt")

    day21part1(lines)
    day21part2(lines)
}

var dieRolls = 0
var currentDieValue = 1

val rollOccurrences = mapOf(
    Pair(3, 1),
    Pair(4, 3),
    Pair(5, 6),
    Pair(6, 7),
    Pair(7, 6),
    Pair(8, 3),
    Pair(9, 1),
)

var scoreAtTurn = IntArray(22) { 0 }

fun day21part1(lines: List<String>) {
    var player1Turn = true

    val player1 = DiePlayer(0, Integer.valueOf(lines[0].split(": ")[1]))
    val player2 = DiePlayer(0, Integer.valueOf(lines[1].split(": ")[1]))

    while (player1.score < 1000 && player2.score < 1000) {
        if (player1Turn) {
            playOneRound(player1)

            player1Turn = false
        } else {
            playOneRound(player2)

            player1Turn = true
        }
    }

    val answer = (if (player1.score >= 1000) {player2.score} else {player1.score}) * dieRolls

    println("21a: $answer")
}

fun day21part2(lines: List<String>) {
    val answer = "---"

    println("21b: $answer")
}

fun playOneRound(player: DiePlayer) {
    var positionToIncrease = currentDieValue
    currentDieValue = getNextDieValue(currentDieValue)
    positionToIncrease += currentDieValue
    currentDieValue = getNextDieValue(currentDieValue)
    positionToIncrease += currentDieValue
    currentDieValue = getNextDieValue(currentDieValue)

    player.position += positionToIncrease

    player.position %= 10

    player.score += if (player.position == 0) {10} else {player.position}
}

fun getNextDieValue(currentDieValue: Int): Int {
    val nextValue = currentDieValue + 1
    dieRolls++

    return if (nextValue > 100) {
        1
    } else {
        nextValue
    }
}

data class DiePlayer(var score: Int, var position: Int)