import java.util.*

fun day15() {
    val lines: List<String> = readFile("day15.txt")

    day15part1(lines)
    day15part2(lines)
}

fun day15part1(lines: List<String>) {
    val cave = getCave(lines)

    val answer = findShortestPath(cave) - cave[0][0]

    println("15a: $answer")
}

fun day15part2(lines: List<String>) {
    val cave = getBigCave(lines)

    val answer = findShortestPath(cave) - cave[0][0]

    println("15b: $answer")
}

fun getCave(lines: List<String>): Array<IntArray> {
    val cave = Array(lines.size) { IntArray(lines[0].length) { 0 } }

    lines.forEachIndexed { i, row ->
        row.forEachIndexed { j, _ ->
            cave[i][j] = Integer.valueOf(row[j].toString())
        }
    }

    return cave
}

fun getBigCave(lines: List<String>): Array<IntArray> {
    val size = lines.size
    val cave = Array(size * 5) { IntArray(size * 5) { 0 } }

    lines.forEachIndexed { i, row ->
        row.forEachIndexed { j, _ ->
            cave[i][j] = Integer.valueOf(row[j].toString())
            for (k in 1..4) {
                cave[i][j + size * k] = getNextNumber(cave[i][j + size * (k - 1)])
            }

        }
    }

    for (k in 1..4) {
        for (i in 0 until size) {
            for (j in 0 until size * 5) {
                    cave[i + size * k][j] = getNextNumber(cave[i + size * (k - 1)][j])
            }
        }
    }

    return cave
}

fun getNextNumber(number: Int): Int {
    return when (number) {
        9 -> 1
        else -> number + 1
    }
}

fun findShortestPath(cave: Array<IntArray>): Int {
    val dx = arrayOf(-1, 0, 1, 0)
    val dy = arrayOf(0, 1, 0, -1)
    val size = cave.size
    val distance = Array(size) { IntArray(size) { Int.MAX_VALUE } }

    distance[0][0] = cave[0][0]

    val pq: PriorityQueue<Cell> = PriorityQueue(size * size, compareBy { it.distance })

    pq.add(Cell(0, 0, distance[0][0]))

    while (pq.isNotEmpty()) {
        val current = pq.poll();

        for (i in 0..3) {
            val rows = current.x + dx[i]
            val cols = current.y + dy[i]

            if (isInGrid(rows, cols, size)) {
                if (distance[rows][cols] >
                    distance[current.x][current.y] +
                    cave[rows][cols]
                ) {

                    if (distance[rows][cols] != Int.MAX_VALUE) {
                        val adj = Cell(
                            rows, cols,
                            distance[rows][cols]
                        )
                        pq.remove(adj)
                    }

                    // Insert cell with updated distance
                    distance[rows][cols] = distance[current.x][current.y] +
                            cave[rows][cols]
                    pq.add(Cell(rows, cols, distance[rows][cols]))
                }
            }
        }
    }
    return distance[size- 1][size - 1]
}

/*fun compareDistance(a: Cell, b: Cell): Int {
    return if (a.distance < b.distance) {
        -1
    } else if (a.distance > b.distance) {
        1
    } else {
        0
    }
}*/

fun isInGrid(i: Int, j: Int, size: Int): Boolean {
    return i in 0 until size && j in 0 until size
}

data class Cell (val x: Int, val y: Int, val distance: Int)