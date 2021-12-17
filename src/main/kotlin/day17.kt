import kotlin.math.abs

fun day17() {
    val lines: List<String> = readFile("day17.txt")

    day17part1(lines)
    day17part2(lines)
}

fun day17part1(lines: List<String>) {
    val xPos = lines[0].split(",")[0].split("=")[1].split("..")
    val yPos = lines[0].split(",")[1].split("=")[1].split("..")
    val xPair = Pair(Integer.valueOf(xPos[0]), Integer.valueOf(xPos[1]))
    val yPair = Pair(Integer.valueOf(yPos[0]), Integer.valueOf(yPos[1]))

    var highestHit = 0

    for (i in 1..xPair.second) {
        for (j in 0..abs(yPair.first)) {
            val attempt = findHighestPositionWhileReachingTarget(xPair, yPair, i, j)
            if (attempt > highestHit) {
                highestHit = attempt
            }

        }
    }

    val answer = highestHit

    println("17a: $answer")
}

fun day17part2(lines: List<String>) {
    val xPos = lines[0].split(",")[0].split("=")[1].split("..")
    val yPos = lines[0].split(",")[1].split("=")[1].split("..")
    val xPair = Pair(Integer.valueOf(xPos[0]), Integer.valueOf(xPos[1]))
    val yPair = Pair(Integer.valueOf(yPos[0]), Integer.valueOf(yPos[1]))

    var noOfHits = 0

    for (i in 1..xPair.second * 2) {
        for (j in yPair.first..abs(yPair.first)) {
            val attempt = findHighestPositionWhileReachingTarget(xPair, yPair, i, j)
            if (attempt > -1) {
                noOfHits++
            }

        }
    }

    val answer = noOfHits

    println("17b: $answer")
}

fun findHighestPositionWhileReachingTarget(xPair: Pair<Int, Int>, yPair: Pair<Int, Int>, xStartVelocity: Int, yStartVelocity: Int): Int {
    var xPosition = 0
    var yPosition = 0
    var xVelocity = xStartVelocity
    var yVelocity = yStartVelocity
    var maxY = 0

    while (true) {
        xPosition += xVelocity
        yPosition += yVelocity
        if (xVelocity > 0) {
            xVelocity--
        }
        yVelocity--

        if (yPosition > maxY) {
            maxY = yPosition
        }

        if (xPosition >= xPair.first && xPosition <= xPair.second && yPosition >= yPair.first && yPosition <= yPair.second) {
            return maxY
        } else if (xPosition > xPair.second || yPosition < yPair.first) {
            return -1
        }
    }
}