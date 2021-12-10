fun day9() {
    val lines: List<String> = readFile("day09.txt")

    day9part1(lines)
    day9part2(lines)
}

fun day9part1(lines: List<String>) {
    val heightMap = getHeightMap(lines)
    val lowPoints = getLowPoints(heightMap)

    val answer = lowPoints.sumOf { heightMap[it.first][it.second] + 1 }

    println("9a: $answer")
}

fun day9part2(lines: List<String>) {
    val heightMap = getHeightMap(lines)
    val lowPoints = getLowPoints(heightMap)
    var basinCoordinates: MutableSet<Pair<Int, Int>>
    val basinSizes: MutableList<Int> = mutableListOf()

    lowPoints.forEach {
        basinCoordinates = mutableSetOf()
        getBasinCoordinates(heightMap, it.first, it.second, basinCoordinates)
        basinSizes.add(basinCoordinates.size)
    }

    val answer = basinSizes.sortedDescending().subList(0, 3).reduce { product, i -> product * i }

    println("9b: $answer")
}

fun getLowPoints(heightMap: Array<IntArray>): List<Pair<Int, Int>> {
    val lowPoints: MutableList<Pair<Int, Int>> = mutableListOf()

    heightMap.forEachIndexed { i, row ->
        row.forEachIndexed { j, _ ->
            if (isLowPoint(heightMap, i, j)) {
                lowPoints.add(Pair(i, j))
            }
        }
    }

    return lowPoints
}

fun isLowPoint(heightMap: Array<IntArray>, i: Int, j: Int): Boolean {
    var isLowPoint = true

    if (i > 0 && heightMap[i][j] >= heightMap[i-1][j]) {
        isLowPoint = false
    }

    if (i < heightMap.size - 1 && heightMap[i][j] >= heightMap[i+1][j]) {
        isLowPoint = false
    }

    if (j > 0 && heightMap[i][j] >= heightMap[i][j-1]) {
        isLowPoint = false
    }

    if (j < heightMap[0].size - 1 && heightMap[i][j] >= heightMap[i][j+1]) {
        isLowPoint = false
    }

    return isLowPoint
}

fun getBasinCoordinates(heightMap: Array<IntArray>, i: Int, j: Int, basinCoordinates: MutableSet<Pair<Int, Int>>) {
    basinCoordinates.add(Pair(i, j))

    if (i > 0 && heightMap[i][j] < heightMap[i-1][j] && heightMap[i-1][j] != 9) {
        getBasinCoordinates(heightMap, i-1, j, basinCoordinates)
    }

    if (i < heightMap.size - 1 && heightMap[i][j] < heightMap[i+1][j] && heightMap[i+1][j] != 9) {
        getBasinCoordinates(heightMap, i+1, j, basinCoordinates)
    }

    if (j > 0 && heightMap[i][j] < heightMap[i][j-1] && heightMap[i][j-1] != 9) {
        getBasinCoordinates(heightMap, i, j-1, basinCoordinates)
    }

    if (j < heightMap[0].size - 1 && heightMap[i][j] < heightMap[i][j+1] && heightMap[i][j+1] != 9) {
        getBasinCoordinates(heightMap, i, j+1, basinCoordinates)
    }
}


fun getHeightMap(lines: List<String>): Array<IntArray> {
    val heightMap = Array(lines.size) { IntArray(lines[0].length) { 0 } }

    lines.forEachIndexed { i, row ->
        row.forEachIndexed { j, _ ->
            heightMap[i][j] = Integer.valueOf(row[j].toString())
        }
    }

    return heightMap
}
