const val SIZE = 1000

fun day5() {
    day5part1()
    day5part2()
}

fun day5part1() {
    val answer = findAnswer(false)

    println("5a: $answer")
}

fun day5part2() {
    val answer = findAnswer(true)

    println("5b: $answer")
}

fun findAnswer(considerDiagonal: Boolean): Int {
    val lines: List<String> = readFile("day05.txt")

    val lineSegments: MutableList<LineSegment> = mutableListOf()
    val oceanFloor = initOceanFloor()

    lines.forEach { lineSegments.add(inputToLineSegment(it.split(" -> "))) }

    val horizontalAndVerticalLineSegments = lineSegments.filter { it.start.x == it.end.x || it.start.y == it.end.y }

    horizontalAndVerticalLineSegments.forEach { lineSegment ->
        getCoordinatesFromHorizontalAndVerticalLineSegment(lineSegment).forEach { coordinate ->
            oceanFloor.matrix[coordinate.x][coordinate.y]++
        }
    }

    if (considerDiagonal) {
        val diagonalLineSegments = lineSegments.filter { it.start.x != it.end.x && it.start.y != it.end.y }
        diagonalLineSegments.forEach { lineSegment ->
            getCoordinatesFromDiagonalLineSegment(lineSegment).forEach { coordinate ->
                oceanFloor.matrix[coordinate.x][coordinate.y]++
            }
        }
    }

    return findOverlaps(oceanFloor)
}

fun inputToLineSegment(inputs: List<String>): LineSegment {
    val startList = inputs[0].split(",").map{ Integer.valueOf(it) }
    val endList = inputs[1].split(",").map{ Integer.valueOf(it) }

    return LineSegment(
        Coordinate(startList[0], startList[1]),
        Coordinate(endList[0], endList[1])
    )
}

fun initOceanFloor(): OceanFloor {
    return OceanFloor(Array(SIZE) { IntArray(SIZE) { 0 } })
}

fun getCoordinatesFromHorizontalAndVerticalLineSegment(lineSegment: LineSegment): List<Coordinate> {
    val coordinates: MutableList<Coordinate> = mutableListOf()
    var orderedLineSegment = lineSegment
    if (lineSegment.start.x > lineSegment.end.x || lineSegment.start.y > lineSegment.end.y) {
        orderedLineSegment = LineSegment(
            Coordinate(lineSegment.end.x, lineSegment.end.y),
            Coordinate(lineSegment.start.x, lineSegment.start.y),
        )
    }

    if (orderedLineSegment.start.x == orderedLineSegment.end.x) {
        for (i in orderedLineSegment.start.y..orderedLineSegment.end.y) {
            coordinates.add(Coordinate(orderedLineSegment.start.x, i))
        }
    } else {
        for (i in orderedLineSegment.start.x..orderedLineSegment.end.x) {
            coordinates.add(Coordinate(i, orderedLineSegment.start.y))
        }
    }

    return coordinates
}

fun getCoordinatesFromDiagonalLineSegment(lineSegment: LineSegment): List<Coordinate> {
    val coordinates: MutableList<Coordinate> = mutableListOf()
    var orderedLineSegment = lineSegment
    if (lineSegment.start.x > lineSegment.end.x) {
        orderedLineSegment = LineSegment(
            Coordinate(lineSegment.end.x, lineSegment.end.y),
            Coordinate(lineSegment.start.x, lineSegment.start.y),
        )
    }

    val steps = orderedLineSegment.end.x - orderedLineSegment.start.x

    if (orderedLineSegment.start.y > orderedLineSegment.end.y) {
        for (i in 0..steps) {
            coordinates.add(Coordinate(orderedLineSegment.start.x + i, orderedLineSegment.start.y - i))
        }
    } else {
        for (i in 0..steps) {
            coordinates.add(Coordinate(orderedLineSegment.start.x + i, orderedLineSegment.start.y + i))
        }
    }

    return coordinates
}

fun findOverlaps(oceanFloor: OceanFloor): Int {
    var answer = 0

    for (i in 0 until SIZE) {
        for (j in 0 until SIZE) {
            if (oceanFloor.matrix[i][j] > 1) {
                answer++
            }
        }
    }

    return answer
}

data class Coordinate(val x: Int, val y: Int)

data class LineSegment(val start: Coordinate, val end: Coordinate)

data class OceanFloor(val matrix: Array<IntArray>)